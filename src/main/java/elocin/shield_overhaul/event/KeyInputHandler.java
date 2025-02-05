package elocin.shield_overhaul.event;

import elocin.shield_overhaul.ShieldOverhaul;
import elocin.shield_overhaul.config.server.ShieldConfig;
import elocin.shield_overhaul.networking.PacketRegistry;
import elocin.shield_overhaul.util.AnimUtils;
import elocin.shield_overhaul.util.ShieldUtils;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Hand;

public class KeyInputHandler {

    public static void registerKeyInputs() {
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if (client.player == null) return;
            ItemStack stack = client.player.getStackInHand(client.player.getActiveHand());
            if (!(stack.getItem() instanceof ShieldItem)) return;
            if (stack.getNbt() == null) return;

            if (client.player.isBlocking()) {
                if (client.options.attackKey.isPressed() && client.options.attackKey.wasPressed()) {
                    if (!client.player.getItemCooldownManager().isCoolingDown(stack.getItem())) {
                        AnimUtils.playAnimation(client.player, "bash_right", client.player.getActiveHand().equals(Hand.OFF_HAND));
                    }
                    ClientPlayNetworking.send(PacketRegistry.SHIELD_BASH, PacketByteBufs.empty());
                }
            }

            if (client.player.getItemUseTime() == 1 && !stack.getNbt().getBoolean("holdStarted")) {
                ClientPlayNetworking.send(PacketRegistry.HOLD_BEGIN, PacketByteBufs.empty());
            } else if (!client.options.useKey.isPressed() && stack.getNbt().getBoolean("holdStarted")) {
                ClientPlayNetworking.send(PacketRegistry.HOLD_END, PacketByteBufs.empty());
                if (!ShieldConfig.INSTANCE.enable_parrying || client.player.getItemUseTime() > 5 || ShieldUtils.isParrying(stack, client.player) || client.player.getItemCooldownManager().isCoolingDown(stack.getItem())) return;
                AnimUtils.playAnimation(client.player, "parry_right", client.player.getActiveHand().equals(Hand.OFF_HAND));
            }
        });
    }

    public static void initialize() {
        registerKeyInputs();
    }
}

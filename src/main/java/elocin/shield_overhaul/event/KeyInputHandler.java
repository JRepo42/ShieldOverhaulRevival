package elocin.shield_overhaul.event;

import elocin.shield_overhaul.ShieldOverhaul;
import elocin.shield_overhaul.networking.PacketRegistry;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;

public class KeyInputHandler {

    public static void registerKeyInputs() {
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if (client.player == null) return;
            ItemStack stack = client.player.getStackInHand(client.player.getActiveHand());
            if (!(stack.getItem() instanceof ShieldItem)) return;

            if (client.player.isBlocking()) {
                if (client.options.attackKey.isPressed() && client.options.attackKey.wasPressed()) {
                    ClientPlayNetworking.send(PacketRegistry.SHIELD_BASH, PacketByteBufs.empty());
                }
            }

            if (client.player.getItemUseTime() == 1 && !stack.getNbt().getBoolean("holdStarted")) {
                ClientPlayNetworking.send(PacketRegistry.HOLD_BEGIN, PacketByteBufs.empty());
            } else if (!client.options.useKey.isPressed() && stack.getNbt().getBoolean("holdStarted")) {
                ClientPlayNetworking.send(PacketRegistry.HOLD_END, PacketByteBufs.empty());
            }
        });
    }

    public static void initialize() {
        registerKeyInputs();
    }
}

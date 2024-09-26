package elocin.shield_overhaul.networking;

import com.zigythebird.playeranimatorapi.API.PlayerAnimAPI;
import elocin.shield_overhaul.ShieldOverhaul;
import elocin.shield_overhaul.registry.animation.AnimConstants;
import elocin.shield_overhaul.util.AnimUtils;
import elocin.shield_overhaul.util.ShieldUtils;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class HoldEndC2SPacket {
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) {

        server.execute(() -> {
            ItemStack stack = player.getStackInHand(player.getActiveHand());
            if (stack.getNbt() == null) stack.getOrCreateNbt();
            if (!(stack.getItem() instanceof ShieldItem item) || !stack.getNbt().getBoolean("holdStarted")) return;
            stack.getNbt().putBoolean("holdStarted", false);
            if (!ShieldOverhaul.CONFIG.enable_parrying || player.getItemUseTime() > 5 || ShieldUtils.isParrying(stack, player) || player.getItemCooldownManager().isCoolingDown(item)) return;
            ShieldUtils.setParryWindow(stack, player);
            player.getItemCooldownManager().set(item, ShieldUtils.getParryDuration() + ShieldUtils.getParryCooldown());
            AnimUtils.playParryAnim(player.getServerWorld(), player);
        });

    }
}

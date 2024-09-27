package elocin.shield_overhaul.networking;

import elocin.shield_overhaul.ShieldOverhaul;
import elocin.shield_overhaul.registry.entity.ShieldBashEntity;
import elocin.shield_overhaul.util.AnimUtils;
import elocin.shield_overhaul.util.ShieldUtils;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;

public class ShieldBashC2SPacket {
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) {

        server.execute(() -> {
            ItemStack stack = player.getStackInHand(player.getActiveHand());
            if (stack.getNbt() == null) stack.getOrCreateNbt();
            if (!(stack.getItem() instanceof ShieldItem item)) return;
            if (!ShieldOverhaul.CONFIG.enable_bashing || !player.isBlocking() || ShieldUtils.isParrying(stack, player) || player.getItemCooldownManager().isCoolingDown(item)) return;
            ShieldUtils.stunBash(player, item);
        });

    }
}

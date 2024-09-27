package elocin.shield_overhaul.networking.server;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class HoldBeginC2SPacket {
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) {
        server.execute(() -> {
            ItemStack stack = player.getStackInHand(player.getActiveHand());
            if (stack.getNbt() == null) stack.getOrCreateNbt();
            if (!(stack.getItem() instanceof ShieldItem) || stack.getNbt().getBoolean("holdStarted")) return;
            stack.getNbt().putBoolean("holdStarted", true);
        });
    }
}

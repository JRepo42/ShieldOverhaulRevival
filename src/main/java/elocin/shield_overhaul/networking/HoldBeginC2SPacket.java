package elocin.shield_overhaul.networking;

import elocin.shield_overhaul.ShieldOverhaul;
import elocin.shield_overhaul.util.ShieldUtils;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class HoldBeginC2SPacket {
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) {
        ItemStack stack = player.getEquippedStack(EquipmentSlot.MAINHAND);
        if (stack.getNbt() == null) stack.getOrCreateNbt();
        if (!(stack.getItem() instanceof ShieldItem item) || stack.getNbt().getBoolean("holdStarted")) return;
        stack.getNbt().putBoolean("holdStarted", true);
    }
}

package elocin.shield_overhaul.networking.client;

import elocin.shield_overhaul.registry.enchantment.EnchantmentEnums;
import elocin.shield_overhaul.util.AnimUtils;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;

import java.util.UUID;

public class AnimationPlayS2CPacket {
    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        UUID animatedPlayerUUID = buf.readUuid();
//        String animationName = buf.readString();

        PlayerEntity animatedPlayer = client.world.getPlayerByUuid(animatedPlayerUUID);

        if (animatedPlayer == null) return;

        AnimUtils.playAnimation(animatedPlayer, "bash_right");

        client.execute(() -> {

        });
    }
}

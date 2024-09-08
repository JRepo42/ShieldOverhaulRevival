package elocin.shield_overhaul.networking;

import elocin.shield_overhaul.ShieldOverhaul;
import elocin.shield_overhaul.registry.particle.ParticleRegistry;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketByteBuf;

public class StunParticleS2CPacket {
    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        Entity entity = client.world.getEntityById(buf.readInt());
        if (entity == null) return;

        client.world.addParticle(
                ParticleRegistry.STUN_STAR,
                entity.getX(),
                entity.getY() + entity.getBoundingBox().getYLength(),
                entity.getZ(),
                0,
                0,
                0);
    }
}

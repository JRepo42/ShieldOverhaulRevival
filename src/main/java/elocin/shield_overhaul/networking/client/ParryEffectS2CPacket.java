package elocin.shield_overhaul.networking.client;

import elocin.shield_overhaul.registry.enchantment.EnchantmentEnums;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;

public class ParryEffectS2CPacket {
    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        Entity entity = client.world.getEntityById(buf.readInt());
        Enum<EnchantmentEnums> enchantmentEnum = buf.readEnumConstant(EnchantmentEnums.class);

        if (entity == null) return;

        client.execute(() -> {
            // Todo: fix this line causing a LegacyRandomSource from multiple threads crash (I think resolved issue)
            DefaultParticleType particle1 = ParticleTypes.SNOWFLAKE;
            DefaultParticleType particle2 = ParticleTypes.ENCHANT;

            if (enchantmentEnum.equals(EnchantmentEnums.FLAMEBORN)) {
                particle1 = ParticleTypes.LAVA;
                particle2 = ParticleTypes.DRIPPING_LAVA;
            }

            for(int i = 0; i < 5; ++i) {
                double d = 1 * 0.02D;
                double e = 1 * 0.02D;
                double f = 1 * 0.02D;
                entity.getWorld().addParticle(particle1, entity.getParticleX(1.0D), entity.getBodyY(entity.getHeight()/4), entity.getParticleZ(1.0D), d, e, f);
            }

            for(int i = 0; i < 15; ++i) {
                double d = 1 * 0.02D;
                double e = 1 * 0.02D;
                double f = 1 * 0.02D;
                entity.getWorld().addParticle(particle2, entity.getParticleX(1.0D), entity.getY(), entity.getParticleZ(1.0D), d * 10, e * 10, f * 10);
            }

        });
    }
}

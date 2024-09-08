package elocin.shield_overhaul.registry;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.particle.FlameParticle;

public class ParticleClientRegistry {
    public static void initialize() {
        ParticleFactoryRegistry.getInstance().register(ParticleRegistry.STUN_STAR, FlameParticle.Factory::new);
    }
}

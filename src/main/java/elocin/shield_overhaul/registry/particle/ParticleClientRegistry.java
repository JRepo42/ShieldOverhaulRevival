package elocin.shield_overhaul.registry.particle;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.particle.CampfireSmokeParticle;

public class ParticleClientRegistry {
    public static void initialize() {
        ParticleFactoryRegistry.getInstance().register(ParticleRegistry.STUN_STAR, StunStarParticle.Factory::new);
    }
}

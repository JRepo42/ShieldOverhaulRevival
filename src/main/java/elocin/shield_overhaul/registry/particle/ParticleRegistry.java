package elocin.shield_overhaul.registry.particle;

import elocin.shield_overhaul.ShieldOverhaul;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ParticleRegistry {
    public static final DefaultParticleType STUN_STAR = FabricParticleTypes.simple();

    public static void initialize() {
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(ShieldOverhaul.MOD_ID, "stun_star"), STUN_STAR);
    }
}

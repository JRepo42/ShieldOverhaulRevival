package elocin.shield_overhaul.effect;

import elocin.shield_overhaul.ShieldOverhaul;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class EffectRegistry {
    public static final StatusEffect STUN = new StunEffect();

    public static void initialize() {
        Registry.register(Registries.STATUS_EFFECT, new Identifier(ShieldOverhaul.MOD_ID, "stun"), STUN);
    }
}

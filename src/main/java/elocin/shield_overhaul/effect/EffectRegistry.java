package elocin.shield_overhaul.effect;

import elocin.shield_overhaul.ShieldOverhaul;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class EffectRegistry {
    public static final StatusEffect STUN = new StunEffect().addAttributeModifier(
    EntityAttributes.GENERIC_MOVEMENT_SPEED, "be7f1afd-03fd-4d54-9f5d-9620603d3041", -1.0, EntityAttributeModifier.Operation.MULTIPLY_TOTAL
    ).addAttributeModifier(
    EntityAttributes.GENERIC_ATTACK_DAMAGE, "650f237b-c7b7-47dc-8dd4-7b2ae73dabb2", -1.0, EntityAttributeModifier.Operation.MULTIPLY_TOTAL
    );
    public static final StatusEffect STUN_IMMUNITY = new StunImmunityEffect();


    public static void initialize() {
        Registry.register(Registries.STATUS_EFFECT, new Identifier(ShieldOverhaul.MOD_ID, "stun"), STUN);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(ShieldOverhaul.MOD_ID, "stun_immunity"), STUN_IMMUNITY);
    }
}

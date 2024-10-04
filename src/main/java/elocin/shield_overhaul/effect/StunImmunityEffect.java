package elocin.shield_overhaul.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class StunImmunityEffect extends StatusEffect {
    protected StunImmunityEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    public StunImmunityEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0xC0C0C0);
    }
}

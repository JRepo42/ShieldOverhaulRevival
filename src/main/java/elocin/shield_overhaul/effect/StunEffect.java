package elocin.shield_overhaul.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class StunEffect extends StatusEffect {
    protected StunEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    public StunEffect() {
        super(StatusEffectCategory.HARMFUL, 0xFFFF00);
    }
}

package elocin.shield_overhaul.registry.animation;

import elocin.shield_overhaul.ShieldOverhaul;
import net.minecraft.util.Identifier;

public class AnimConstants {
    public static final Identifier PARRY_RIGHT = new Identifier(ShieldOverhaul.MOD_ID, "parry_right");
    public static final Identifier BASH_RIGHT = new Identifier(ShieldOverhaul.MOD_ID, "bash_right");

    // Modifiers
    public static final Identifier MIRROR =
            new Identifier("player-animator", "mirror");

    public static final Identifier SPEED =
            new Identifier("player-animator", "speed");
}

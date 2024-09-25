package elocin.shield_overhaul.util;

import elocin.shield_overhaul.ShieldOverhaul;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class ShieldUtils {
    private static final String PARRY_WINDOW = "parry_window";

    public static boolean isParrying(ItemStack stack, PlayerEntity player) {
        return getParryWindow(stack) > player.getWorld().getTime();
    }

    public static void setParryWindow(ItemStack stack, PlayerEntity player) {
        stack.getNbt().putLong(PARRY_WINDOW, player.getWorld().getTime() + getParryDuration());
    }

    public static int getParryDuration() {
        return (int) (ShieldOverhaul.CONFIG.parry_duration_secs * 20);
    }

    public static int getParryCooldown() {
        return (int) (ShieldOverhaul.CONFIG.parry_cooldown_secs * 20);
    }

    public static int getStunDuration() {
        return (int) (ShieldOverhaul.CONFIG.parry_stun_duration_secs * 20);
    }

    public static long getParryWindow(ItemStack stack) {
        if (stack.getNbt() == null) return 0;
        return stack.getNbt().getLong(PARRY_WINDOW);
    }
}

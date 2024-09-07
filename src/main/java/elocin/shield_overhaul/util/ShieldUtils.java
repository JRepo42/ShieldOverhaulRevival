package elocin.shield_overhaul.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class ShieldUtils {
    // Todo: config, cooldown on parry so that you can't just spam
    private static final String PARRY_WINDOW = "parry_window";

    public static boolean isParrying(ItemStack stack, PlayerEntity player) {
        return getParryWindow(stack) > player.getWorld().getTime();
    }

    public static void setParryWindow(ItemStack stack, PlayerEntity player) {
        stack.getNbt().putLong(PARRY_WINDOW, player.getWorld().getTime() + 20);
    }

    public static long getParryWindow(ItemStack stack) {
        if (stack.getNbt() == null) return 0;
        return stack.getNbt().getLong(PARRY_WINDOW);
    }
}

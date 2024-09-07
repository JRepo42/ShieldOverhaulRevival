package elocin.shield_overhaul.util;

import elocin.shield_overhaul.ShieldOverhaul;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;

public class ShieldUtils {
    private static final String PARRY_WINDOW = "parry_window";

    public static boolean isParrying(ItemStack stack, PlayerEntity player) {
        return getParryWindow(stack) > player.getWorld().getTime();
    }

    public static void setParryWindow(ItemStack stack, PlayerEntity player) {
        stack.getNbt().putLong(PARRY_WINDOW, player.getWorld().getTime() + 20);
    }

    public static long getParryWindow(ItemStack stack) {
        return stack.getNbt().getLong(PARRY_WINDOW);
    }
}

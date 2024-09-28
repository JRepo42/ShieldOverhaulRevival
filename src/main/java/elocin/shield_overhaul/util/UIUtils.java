package elocin.shield_overhaul.util;

import elocin.shield_overhaul.ShieldOverhaul;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

public class UIUtils {
    public static float getParryProgress(PlayerEntity player, ItemStack stack) {
        float parryWindow = ShieldUtils.getParryWindow(stack);
        float currentTick = player.getWorld().getTime();

        if (parryWindow - currentTick >= 0) {
            return MathHelper.clamp((parryWindow - currentTick) / 20, 0.0F, 1.0F);
        }
        return 0;
    }
}

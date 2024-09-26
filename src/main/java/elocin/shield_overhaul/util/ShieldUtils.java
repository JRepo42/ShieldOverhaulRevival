package elocin.shield_overhaul.util;

import elocin.shield_overhaul.ShieldOverhaul;
import elocin.shield_overhaul.effect.EffectRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;

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

    public static void removeParryCooldown(PlayerEntity player) {
        if (ShieldUtils.isParrying(player.getMainHandStack(), player)) {
            player.getItemCooldownManager().remove(player.getStackInHand(Hand.MAIN_HAND).getItem());
        } else {
            player.getItemCooldownManager().remove(player.getStackInHand(Hand.OFF_HAND).getItem());
        }
    }

    public static void stunParry(PlayerEntity player, LivingEntity attacker) {
        player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_ANVIL_LAND, player.getSoundCategory(), 1.0f, 2.0f);
        removeParryCooldown(player);

        if (attacker == null || attacker instanceof CreeperEntity) return;
        attacker.addStatusEffect(new StatusEffectInstance(EffectRegistry.STUN, ShieldUtils.getStunDuration(), 0, false, false));
    }
}

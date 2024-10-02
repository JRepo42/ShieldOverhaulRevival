package elocin.shield_overhaul.registry.enchantment;

import elocin.shield_overhaul.ShieldOverhaul;
import elocin.shield_overhaul.config.server.ShieldConfig;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;

public class VigorEnchantment extends Enchantment {

    public VigorEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.BREAKABLE, new EquipmentSlot[] { EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND });
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof ShieldItem;
    }

    @Override
    public int getMinPower(int level) {
        return 1;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }


    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(target instanceof LivingEntity livingEntity) {
            livingEntity.takeKnockback(level * ShieldConfig.INSTANCE.vigor_strength_multiplier_per_level, -user.getRotationVector().x, -user.getRotationVector().z);
        }

        super.onTargetDamaged(user, target, level);
    }
}

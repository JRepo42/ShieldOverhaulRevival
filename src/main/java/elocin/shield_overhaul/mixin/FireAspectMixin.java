package elocin.shield_overhaul.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerEntity.class)
public abstract class FireAspectMixin {

    @Shadow public abstract ItemStack getEquippedStack(EquipmentSlot slot);

    // Todo: fix this breaking fire aspect enchantments
    @ModifyExpressionValue(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentHelper;getFireAspect(Lnet/minecraft/entity/LivingEntity;)I"))
    private int shield_overhaul$attack(int original) {
        if (((PlayerEntity)(Object)this).getEquippedStack(EquipmentSlot.MAINHAND).getItem() instanceof ShieldItem) {
            return 0;
        }
        return original;
    }

}

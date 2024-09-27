package elocin.shield_overhaul.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.FireAspectEnchantment;
import net.minecraft.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Enchantment.class)
public class FireAspectMixin {
    @ModifyExpressionValue(
            method = "isAcceptableItem",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentTarget;isAcceptableItem(Lnet/minecraft/item/Item;)Z")
    )
    private boolean onlyFlyIfAllowed(boolean original) {
        return original || ((Enchantment)(Object)this instanceof FireAspectEnchantment);
    }
}

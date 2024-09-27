package elocin.shield_overhaul.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.FireAspectEnchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public class EnchantmentMixin {

    // Todo: not correct, gotta make it so that only shields can be enchanted with fire aspect
    @Inject(method = "isAcceptableItem", at = @At("HEAD"), cancellable = true)
    private void shield_overhaul$isAcceptable(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.getItem() instanceof ShieldItem && (Enchantment)(Object)this instanceof FireAspectEnchantment) {
            cir.setReturnValue(true);
        }
    }
}

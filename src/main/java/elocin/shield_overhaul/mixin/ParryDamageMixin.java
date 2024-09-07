package elocin.shield_overhaul.mixin;

import elocin.shield_overhaul.ShieldOverhaul;
import elocin.shield_overhaul.util.ShieldUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class ParryDamageMixin {
	@Inject(at = @At("HEAD"), method = "modifyAppliedDamage", cancellable = true)
	private void shield_overhaul$modifyAppliedDamage(DamageSource source, float amount, CallbackInfoReturnable<Float> cir) {
		if (!((LivingEntity)(Object) this instanceof PlayerEntity player)) return;
		if (source.isIn(DamageTypeTags.BYPASSES_EFFECTS)) return;

		if (ShieldUtils.isParrying(player.getMainHandStack(), player) || ShieldUtils.isParrying(player.getOffHandStack(), player)) {
			cir.setReturnValue(0f);
		}
	}
}
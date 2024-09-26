package elocin.shield_overhaul.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import elocin.shield_overhaul.ShieldOverhaul;
import elocin.shield_overhaul.effect.EffectRegistry;
import elocin.shield_overhaul.util.ShieldUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class ParryMechanicsMixin {

	@Inject(method = "isImmobile", at = @At(value = "HEAD"), cancellable = true)
	private void isStunned(CallbackInfoReturnable<Boolean> cir) {
		LivingEntity entity = (LivingEntity)(Object)this;
		if(entity.hasStatusEffect(EffectRegistry.STUN)) {
			cir.setReturnValue(true);;
		}
	}

	// Credits to https://github.com/Quplet/NoShieldDelay (MIT Licensed)
	@ModifyConstant(method = "isBlocking", constant = @Constant(intValue = 5))
	private int setShieldUseDelay(int constant) {
		return ShieldOverhaul.CONFIG.enable_instant_shield_use ? 1 : constant;
	}

	@ModifyExpressionValue(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;blockedByShield(Lnet/minecraft/entity/damage/DamageSource;)Z"))
	private boolean shield_overhaul$blockedByShield(boolean original) {
		if (!((LivingEntity)(Object) this instanceof PlayerEntity player)) return original;

		return original ||
		ShieldUtils.isParrying(player.getMainHandStack(), player)
		|| ShieldUtils.isParrying(player.getOffHandStack(), player);
	}

	@Inject(at = @At("TAIL"), method = "takeShieldHit")
	protected void shield_overhaul$takeShieldHit(LivingEntity attacker, CallbackInfo ci) {
		if (!((LivingEntity)(Object) this instanceof PlayerEntity player)) return;
		if (ShieldUtils.isParrying(player.getMainHandStack(), player)
		|| ShieldUtils.isParrying(player.getOffHandStack(), player)) {
			player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_ANVIL_LAND, player.getSoundCategory(), 1.0f, 2.0f);

			if (ShieldUtils.isParrying(player.getMainHandStack(), player)) {
				player.getItemCooldownManager().remove(player.getStackInHand(Hand.MAIN_HAND).getItem());
			} else {
				player.getItemCooldownManager().remove(player.getStackInHand(Hand.OFF_HAND).getItem());
			}

			if (attacker instanceof CreeperEntity) return;
			attacker.addStatusEffect(new StatusEffectInstance(EffectRegistry.STUN, ShieldUtils.getStunDuration(), 0, false, false));
		}
	}
}
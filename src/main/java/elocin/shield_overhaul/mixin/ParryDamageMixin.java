package elocin.shield_overhaul.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import elocin.shield_overhaul.ShieldOverhaul;
import elocin.shield_overhaul.effect.EffectRegistry;
import elocin.shield_overhaul.util.ShieldUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.MinecraftServer;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class ParryDamageMixin {
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
			attacker.addStatusEffect(new StatusEffectInstance(EffectRegistry.STUN, 20, 0, false, false));
		}
	}
}
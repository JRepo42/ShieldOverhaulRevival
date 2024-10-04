package elocin.shield_overhaul.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import elocin.shield_overhaul.ShieldOverhaul;
import elocin.shield_overhaul.config.server.ShieldConfig;
import elocin.shield_overhaul.util.ShieldUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PersistentProjectileEntity.class)
public abstract class ArrowDeflectionMixin {

    @WrapOperation(method = "onEntityHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/projectile/PersistentProjectileEntity;setVelocity(Lnet/minecraft/util/math/Vec3d;)V"))
    private void shield_overhaul$arrow_deflect(PersistentProjectileEntity instance, Vec3d vec3d, Operation<Void> original, EntityHitResult result) {
        if (result.getEntity() == null || !(result.getEntity() instanceof PlayerEntity player)) return;

        if (!ShieldConfig.INSTANCE.arrow_deflect_requires_parry && (player.isBlocking() || ShieldUtils.isParrying(ShieldUtils.getParryStack(player), player))
            || (ShieldConfig.INSTANCE.arrow_deflect_requires_parry && ShieldUtils.isParrying(ShieldUtils.getParryStack(player), player))) {
            original.call(instance, player.getRotationVector().multiply(ShieldConfig.INSTANCE.arrow_deflect_velocity_multiplier));
        }
    }
}

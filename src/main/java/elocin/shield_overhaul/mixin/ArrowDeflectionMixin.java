package elocin.shield_overhaul.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import elocin.shield_overhaul.ShieldOverhaul;
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
        ShieldOverhaul.LOGGER.info("Test");
        if (result.getEntity() == null || !(result.getEntity() instanceof PlayerEntity player)) return;
        // ShieldUtils.isParrying(ShieldUtils.getParryStack(player), player)
        ShieldOverhaul.LOGGER.info("Made it this far");
        if (player.isAlive()) {
            original.call(instance, player.getRotationVector().multiply(1));
            instance.velocityModified = true;
        }
    }
}

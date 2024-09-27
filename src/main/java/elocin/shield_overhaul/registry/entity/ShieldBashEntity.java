package elocin.shield_overhaul.registry.entity;

import elocin.shield_overhaul.ShieldOverhaul;
import elocin.shield_overhaul.effect.EffectRegistry;
import elocin.shield_overhaul.util.ShieldUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class ShieldBashEntity extends PersistentProjectileEntity {
    private int life = 0;

    public ShieldBashEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public ShieldBashEntity(LivingEntity owner, World world) {
        super(EntityRegistry.SHIELD_BASH_ENTITY, owner, world);
        this.setNoGravity(true);
        this.setDamage(ShieldOverhaul.CONFIG.bash_damage);
    }

    @Override
    public void tick() {
        super.tick();

        ++this.life;
        if (this.life >= 3) {
            this.discard();
        }
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (this.getWorld().isClient) return;

        if (entityHitResult.getEntity() instanceof LivingEntity entity) {
            if (entity.isBlocking()) return;

            entity.addStatusEffect(new StatusEffectInstance(EffectRegistry.STUN, ShieldUtils.getBashStunDuration(), 0, false, false, true));
            entity.playSound(SoundEvents.ITEM_SHIELD_BLOCK, 1, 0.8F);
            entity.damage(this.getDamageSources().generic(), ShieldOverhaul.CONFIG.bash_damage);
            this.setRemoved(RemovalReason.DISCARDED);
        } else if (entityHitResult.getEntity() instanceof ArrowEntity arrow) {
            arrow.setVelocity(arrow.getVelocity().multiply(-1));
            arrow.velocityModified = true;
        }
    }

    @Override
    protected ItemStack asItemStack() {
        return ItemStack.EMPTY;
    }

    @Override
    protected SoundEvent getHitSound() {
        return SoundEvents.ITEM_SHIELD_BLOCK;
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        this.setRemoved(RemovalReason.DISCARDED);
    }
}
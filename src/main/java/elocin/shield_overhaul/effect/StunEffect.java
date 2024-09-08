package elocin.shield_overhaul.effect;

import elocin.shield_overhaul.networking.PacketRegistry;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class StunEffect extends StatusEffect {
    protected StunEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        super.applyUpdateEffect(entity, amplifier);
        if (entity.age % 10 == 0) addStunParticle(entity);
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onApplied(entity, attributes, amplifier);
        addStunParticle(entity);
    }

    public StunEffect() {
        super(StatusEffectCategory.HARMFUL, 0xFFFF00);
    }

    private void addStunParticle(Entity entity) {
        if (entity.getWorld().isClient) return;
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeInt(entity.getId());

        for (ServerPlayerEntity player : ((ServerWorld) entity.getWorld()).getPlayers()) {
            ServerPlayNetworking.send(player, PacketRegistry.STUN_PARTICLE_PLAY, buf);
        }
    }

}

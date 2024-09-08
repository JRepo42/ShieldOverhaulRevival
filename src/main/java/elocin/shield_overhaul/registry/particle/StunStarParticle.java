package elocin.shield_overhaul.registry.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

public class StunStarParticle extends SpriteBillboardParticle {
    protected float speed = 0.5f;
    protected float radius = 0.5f;

    private float initialX = 0;
    private float initialY = 0;
    private float initialZ = 0;

    protected StunStarParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
        super(clientWorld, d, e, f, g, h, i);
    }

    @Override
    public void tick() {
        super.tick();
        this.setPos(initialX + Math.cos(this.age * speed) * radius, initialY, initialZ + Math.sin(this.age * speed) * radius);
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            StunStarParticle stunStarParticle = new StunStarParticle(clientWorld, d, e, f, g, h, i);
            stunStarParticle.setAlpha(1.0F);
            stunStarParticle.scale(2f);
            stunStarParticle.setMaxAge(200);
            stunStarParticle.setVelocity(0, 0 ,0);
            stunStarParticle.setSprite(this.spriteProvider);

            stunStarParticle.initialX = (float) d;
            stunStarParticle.initialY = (float) e;
            stunStarParticle.initialZ = (float) f;
            return stunStarParticle;
        }
    }
}

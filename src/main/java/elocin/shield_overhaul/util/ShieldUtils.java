package elocin.shield_overhaul.util;

import elocin.shield_overhaul.ShieldOverhaul;
import elocin.shield_overhaul.effect.EffectRegistry;
import elocin.shield_overhaul.networking.PacketRegistry;
import elocin.shield_overhaul.registry.enchantment.EnchantmentEnums;
import elocin.shield_overhaul.registry.enchantment.EnchantmentRegistry;
import elocin.shield_overhaul.registry.entity.ShieldBashEntity;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.WardenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;

public class ShieldUtils {
    private static final String PARRY_WINDOW = "parry_window";

    public static boolean isParrying(ItemStack stack, PlayerEntity player) {
        if (!ShieldOverhaul.CONFIG.enable_parrying) return false;
        return getParryWindow(stack) > player.getWorld().getTime();
    }

    public static void setParryWindow(ItemStack stack, PlayerEntity player) {
        if (!ShieldOverhaul.CONFIG.enable_parrying) return;
        stack.getNbt().putLong(PARRY_WINDOW, player.getWorld().getTime() + getParryDuration());
    }

    /** Parrying **/
    public static int getParryDuration() {
        return (int) (ShieldOverhaul.CONFIG.parry_duration_secs * 20);
    }
    public static int getParryCooldown() {
        return (int) (ShieldOverhaul.CONFIG.parry_cooldown_secs * 20);
    }
    public static int getParryStunDuration() { return (int) (ShieldOverhaul.CONFIG.parry_stun_duration_secs * 20); }


    /** Bashing **/
    public static int getBashCooldown() { return (int) (ShieldOverhaul.CONFIG.bash_cooldown_secs * 20); }
    public static int getBashStunDuration() { return (int) (ShieldOverhaul.CONFIG.bash_stun_duration_secs * 20); }


    public static long getParryWindow(ItemStack stack) {
        if (stack.getNbt() == null) return 0;
        return stack.getNbt().getLong(PARRY_WINDOW);
    }

    public static void removeParryCooldown(PlayerEntity player) {
        if (ShieldUtils.isParrying(player.getMainHandStack(), player)) {
            player.getItemCooldownManager().remove(player.getStackInHand(Hand.MAIN_HAND).getItem());
        } else {
            player.getItemCooldownManager().remove(player.getStackInHand(Hand.OFF_HAND).getItem());
        }
    }

    public static void stunParry(PlayerEntity player, LivingEntity attacker) {
        player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_ANVIL_LAND, player.getSoundCategory(), 1.0f, 2.0f);
        removeParryCooldown(player);
        ItemStack stack = getParryStack(player);

        EnchantmentEnums enchantmentEnum = EnchantmentEnums.DEFAULT;

        if (EnchantmentHelper.getLevel(Enchantments.FIRE_ASPECT, stack) > 0) {
            if (player.getRandom().nextBetween(0, 1) < ShieldOverhaul.CONFIG.flameborn_chance_decimal) {
                attacker.setOnFireFor((int) (ShieldOverhaul.CONFIG.flameborn_on_fire_time_secs));
                enchantmentEnum = EnchantmentEnums.FLAMEBORN;
            }
        }

        addParryParticles(player, enchantmentEnum);

        if (attacker == null || attacker instanceof CreeperEntity) return;
        if (ShieldOverhaul.CONFIG.bosses_immune_to_stun && (attacker instanceof WitherEntity || attacker instanceof WardenEntity || attacker instanceof EnderDragonEntity)) return;
        attacker.addStatusEffect(new StatusEffectInstance(EffectRegistry.STUN, ShieldUtils.getParryStunDuration(), 0, false, false));
    }

    public static void stunBash(PlayerEntity player, Item item) {
        if (player.getWorld().isClient || ShieldOverhaul.CONFIG.bash_only_on_ground && !player.isOnGround()) return;

        player.getItemCooldownManager().set(item, ShieldUtils.getBashCooldown());
        AnimUtils.playBashAnim((ServerWorld) player.getWorld(), player);

        ShieldBashEntity entity = new ShieldBashEntity(player, player.getWorld());
        entity.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, 2.0F, 0F);
        entity.setOwner(player);
        player.getWorld().spawnEntity(entity);

        Vec3d velocityVector = player.getRotationVector();
        velocityVector = velocityVector.multiply(ShieldOverhaul.CONFIG.bash_distance_multiplier);
        player.addVelocity(velocityVector.x, velocityVector.y, velocityVector.z);
        player.velocityModified = true;
    }

    public static ItemStack getParryStack(PlayerEntity entity) {
        ItemStack stack = entity.getMainHandStack();

        if (!ShieldUtils.isParrying(entity.getMainHandStack(), entity)) {
            stack = entity.getOffHandStack();
        }
        return stack;
    }

    public static void addParryParticles(PlayerEntity entity, EnchantmentEnums enumConstant) {
        if (entity.getWorld().isClient) return;
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeInt(entity.getId());

        buf.writeEnumConstant(enumConstant);

        for (ServerPlayerEntity player : ((ServerWorld) entity.getWorld()).getPlayers()) {
            ServerPlayNetworking.send(player, PacketRegistry.PARRY_EFFECT, buf);
        }
    }
}

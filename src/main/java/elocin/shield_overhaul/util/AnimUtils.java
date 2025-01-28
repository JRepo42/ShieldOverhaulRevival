package elocin.shield_overhaul.util;

import dev.kosmx.playerAnim.api.firstPerson.FirstPersonConfiguration;
import dev.kosmx.playerAnim.api.firstPerson.FirstPersonMode;
import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer;
import dev.kosmx.playerAnim.api.layered.modifier.AbstractFadeModifier;
import dev.kosmx.playerAnim.api.layered.modifier.SpeedModifier;
import dev.kosmx.playerAnim.core.data.KeyframeAnimation;
import dev.kosmx.playerAnim.core.util.Ease;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry;
import elocin.shield_overhaul.ShieldOverhaul;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public class AnimUtils {

    private static SpeedModifier SPEED = new SpeedModifier(0.8f);

    public static void playAnimation(PlayerEntity user, String animName) {
        var animationContainer = ((IAnimatedPlayer)user).shield_overhaul$getModAnimation();
        KeyframeAnimation anim = PlayerAnimationRegistry.getAnimation(new Identifier(ShieldOverhaul.MOD_ID, animName));
        var builder = anim.mutableCopy();
        anim = builder.build();
        animationContainer.addModifierLast(SPEED);
        animationContainer.replaceAnimationWithFade(AbstractFadeModifier.standardFadeIn(5, Ease.LINEAR), new KeyframeAnimationPlayer(anim).setFirstPersonMode(FirstPersonMode.THIRD_PERSON_MODEL).setFirstPersonConfiguration(new FirstPersonConfiguration().setShowRightArm(true)));
    }


    /*
    public static void playAnimation(ServerWorld world, PlayerEntity player, Identifier identifier) {
        PlayerAnimAPI.playPlayerAnim(world, player, identifier);
    }

    public static void playBashAnim(ServerWorld world, PlayerEntity player) {
        JsonObject json = new JsonObject();
        CommonModifier mirror = new CommonModifier(AnimConstants.MIRROR, null);
        List<CommonModifier> modifiers = new ArrayList<>();

        if (player.getOffHandStack().getItem() instanceof ShieldItem) {
            modifiers.add(mirror);
        }

        PlayerAnimAPI.playPlayerAnim(world, player, AnimConstants.BASH_RIGHT,
                PlayerParts.allEnabled, modifiers,
                0, 1, 1000, true);
    }

    public static void playParryAnim(ServerWorld world, PlayerEntity player) {
        JsonObject json = new JsonObject();
        json.addProperty("speed", 1 / ShieldConfig.INSTANCE.parry_duration_secs);
        CommonModifier mirror = new CommonModifier(AnimConstants.MIRROR, null);
        CommonModifier speed = new CommonModifier(AnimConstants.SPEED, json);
        List<CommonModifier> modifiers = new ArrayList<>();
        modifiers.add(speed);

        if (ShieldUtils.isParrying(player.getOffHandStack(), player)) {
            modifiers.add(mirror);
        }

        PlayerAnimAPI.playPlayerAnim(world, player, AnimConstants.PARRY_RIGHT,
                PlayerParts.allEnabled, modifiers,
                0, 1, 1000, false);
    }

     */
}

package elocin.shield_overhaul.util;

import com.google.gson.JsonObject;
import com.zigythebird.playeranimatorapi.API.PlayerAnimAPI;
import com.zigythebird.playeranimatorapi.data.PlayerParts;
import com.zigythebird.playeranimatorapi.modifier.CommonModifier;
import elocin.shield_overhaul.ShieldOverhaul;
import elocin.shield_overhaul.registry.animation.AnimConstants;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ShieldItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class AnimUtils {

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
        json.addProperty("speed", 1 / ShieldOverhaul.CONFIG.parry_duration_secs);
        CommonModifier mirror = new CommonModifier(AnimConstants.MIRROR, null);
        CommonModifier speed = new CommonModifier(AnimConstants.SPEED, json);
        List<CommonModifier> modifiers = new ArrayList<>();
        modifiers.add(speed);

        if (ShieldUtils.isParrying(player.getOffHandStack(), player)) {
            modifiers.add(mirror);
        }

        PlayerAnimAPI.playPlayerAnim(world, player, AnimConstants.PARRY_RIGHT,
                PlayerParts.allEnabled, modifiers,
                0, 1, 1000, true);
    }
}

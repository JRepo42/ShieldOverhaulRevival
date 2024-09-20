package elocin.shield_overhaul.util;

import com.zigythebird.playeranimatorapi.API.PlayerAnimAPI;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;

public class AnimUtils {
    public static void playAnimation(ServerWorld world, PlayerEntity player, Identifier identifier) {
        PlayerAnimAPI.playPlayerAnim(world, player, identifier);
    }

}

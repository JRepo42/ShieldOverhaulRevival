package elocin.shield_overhaul.networking;

import elocin.shield_overhaul.ShieldOverhaul;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class PacketRegistry {

    public static final Identifier HOLD_BEGIN = new Identifier(ShieldOverhaul.MOD_ID, "hold_begin_packet");
    public static final Identifier HOLD_END = new Identifier(ShieldOverhaul.MOD_ID, "hold_end_packet");
    public static final Identifier STUN_PARTICLE_PLAY = new Identifier(ShieldOverhaul.MOD_ID, "stun_particle_play");

    public static void registerC2S() {
        ServerPlayNetworking.registerGlobalReceiver(HOLD_BEGIN, HoldBeginC2SPacket::receive);
        ServerPlayNetworking.registerGlobalReceiver(HOLD_END, HoldEndC2SPacket::receive);
    }

    public static void registerS2C() {
        ClientPlayNetworking.registerGlobalReceiver(STUN_PARTICLE_PLAY, StunParticleS2CPacket::receive);
    }

}

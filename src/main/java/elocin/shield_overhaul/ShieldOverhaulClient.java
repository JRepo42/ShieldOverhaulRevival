package elocin.shield_overhaul;

import elocin.shield_overhaul.event.KeyInputHandler;
import elocin.shield_overhaul.networking.PacketRegistry;
import elocin.shield_overhaul.registry.entity.EntityRegistry;
import elocin.shield_overhaul.registry.particle.ParticleClientRegistry;
import net.fabricmc.api.ClientModInitializer;

public class ShieldOverhaulClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        PacketRegistry.registerS2C();
        KeyInputHandler.initialize();
        ParticleClientRegistry.initialize();
        EntityRegistry.initializeRender();
    }
}

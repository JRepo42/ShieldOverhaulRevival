package elocin.shield_overhaul;

import elocin.shield_overhaul.event.KeyInputHandler;
import elocin.shield_overhaul.registry.particle.ParticleClientRegistry;
import net.fabricmc.api.ClientModInitializer;

public class ShieldOverhaulClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyInputHandler.initialize();
        ParticleClientRegistry.initialize();
    }
}

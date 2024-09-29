package elocin.shield_overhaul;

import elocin.shield_overhaul.config.client.ClientConfigBuilder;
import elocin.shield_overhaul.config.client.ClientConfigEntries;
import elocin.shield_overhaul.config.server.ConfigBuilder;
import elocin.shield_overhaul.config.server.ConfigEntries;
import elocin.shield_overhaul.event.KeyInputHandler;
import elocin.shield_overhaul.networking.PacketRegistry;
import elocin.shield_overhaul.registry.entity.EntityRegistry;
import elocin.shield_overhaul.registry.particle.ParticleClientRegistry;
import net.fabricmc.api.ClientModInitializer;

public class ShieldOverhaulClient implements ClientModInitializer {

    public static ClientConfigEntries CLIENT_CONFIG;

    @Override
    public void onInitializeClient() {
        CLIENT_CONFIG = ClientConfigBuilder.loadClientConfig();

        PacketRegistry.registerS2C();
        KeyInputHandler.initialize();
        ParticleClientRegistry.initialize();
        EntityRegistry.initializeRender();
    }
}

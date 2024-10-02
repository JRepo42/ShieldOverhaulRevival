package elocin.shield_overhaul.config;

import elocin.shield_overhaul.ShieldOverhaul;
import elocin.shield_overhaul.config.client.ShieldClientConfig;
import elocin.shield_overhaul.config.server.ShieldConfig;
import elocindev.necronomicon.api.config.v1.NecConfigAPI;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

public class ConfigLoader {
    public static void register() {
        NecConfigAPI.registerConfig(ShieldConfig.class);
    }

    public static void initDatapack(boolean started) {

        ServerLifecycleEvents.END_DATA_PACK_RELOAD.register((server, resourceManager, success)
                ->  {
            if (started) {
                register();
            }
        });

        ShieldOverhaul.LOGGER.info("Shield Overhaul's Config Loaded!");
    }

    public static void initClient() {
        NecConfigAPI.registerConfig(ShieldClientConfig.class);

        ShieldOverhaul.LOGGER.info("Shield Overhaul's Client Config Loaded!");
    }
}

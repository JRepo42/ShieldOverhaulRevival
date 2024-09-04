package elocin.shield_overhaul.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class KeyInputHandler {
    public static void registerKeyInputs() {
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if (client.player == null) return;

        });
    }

    public static void initialize() {
        registerKeyInputs();
    };
}

package elocin.shield_overhaul;

import elocin.shield_overhaul.event.KeyInputHandler;
import net.fabricmc.api.ClientModInitializer;

public class ShieldOverhaulClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyInputHandler.initialize();
    }
}

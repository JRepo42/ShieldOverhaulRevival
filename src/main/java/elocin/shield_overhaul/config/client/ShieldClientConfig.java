package elocin.shield_overhaul.config.client;

import elocin.shield_overhaul.config.ConfigFolder;
import elocindev.necronomicon.config.NecConfig;

public class ShieldClientConfig {
    @NecConfig
    public static ShieldClientConfig INSTANCE;

    public static String getFile() {
        return ConfigFolder.getFile("shield_overhaul_client.json");
    }

    public float icon_scale = 0.7f;
    public int bash_icon_x = -24;
    public int bash_icon_y = -8;
    public int parry_icon_x = 10;
    public int parry_icon_y = -6;
}

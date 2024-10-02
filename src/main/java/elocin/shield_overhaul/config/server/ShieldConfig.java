package elocin.shield_overhaul.config.server;

import elocin.shield_overhaul.config.ConfigFolder;
import elocindev.necronomicon.config.NecConfig;

public class ShieldConfig {
    @NecConfig
    public static ShieldConfig INSTANCE;

    public static String getFile() {
        return ConfigFolder.getFile("shield_overhaul.json");
    }

    public boolean enable_instant_shield_use = true;
    public boolean enable_parrying = true;
    public boolean enable_bashing = true;
    public boolean bash_only_on_ground = true;
    public boolean bosses_immune_to_stun = true;

    public float parry_duration_secs = 0.5f;
    public float parry_cooldown_secs = 0.5f;
    public float parry_stun_duration_secs = 1.0f;
    public float bash_damage = 1.0f;
    public float bash_cooldown_secs = 2.0f;
    public float bash_stun_duration_secs = 1.0f;
    public float bash_distance_multiplier = 2f;
    public float vigor_strength_multiplier_per_level = 0.33f;
    public float flameborn_chance_decimal = 0.25f;
    public int flameborn_on_fire_time_secs = 3;
}

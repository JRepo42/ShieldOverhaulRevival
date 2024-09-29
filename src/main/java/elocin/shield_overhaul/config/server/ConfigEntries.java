package elocin.shield_overhaul.config.server;

import java.util.ArrayList;
import java.util.List;

public class ConfigEntries {
    public List<String> blacklisted_shields = new ArrayList<>();
    public boolean enable_instant_shield_use;
    public boolean add_stun_immunity;
    public boolean enable_parrying;
    public boolean enable_bashing;
    public boolean bash_only_on_ground;
    public boolean bosses_immune_to_stun;
    public float parry_duration_secs;
    public float parry_cooldown_secs;
    public float parry_stun_duration_secs;
    public float bash_damage;
    public float bash_cooldown_secs;
    public float bash_stun_duration_secs;
    public float bash_distance_multiplier;
    public float vigor_strength_multiplier_per_level;
    public float flameborn_chance_decimal;
    public float flameborn_on_fire_time_secs;

}
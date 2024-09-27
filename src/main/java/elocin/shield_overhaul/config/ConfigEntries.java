package elocin.shield_overhaul.config;

import java.util.ArrayList;
import java.util.List;

public class ConfigEntries {
    public List<String> blacklisted_shields = new ArrayList<>();
    public boolean enable_instant_shield_use = true;
    public boolean add_stun_immunity = true;
    public boolean enable_parrying = true;
    public boolean enable_bashing = true;
    public boolean bash_only_on_ground = true;
    public boolean bosses_immune_to_stun = true;
    public float parry_duration_secs = 1F;
    public float parry_cooldown_secs = 0.5F;
    public float parry_stun_duration_secs = 1F;
    public float bash_damage = 0.0F;
    public float bash_cooldown_secs = 3.0F;
    public float bash_stun_duration_secs = 1.0F;
    public float bash_distance_multiplier = 1.0F;
    public float vigor_strength_multiplier_per_level = 0.33F;
    public float flameborn_chance_decimal = 0.1f;
    public float flameborn_on_fire_time_secs = 3;

}
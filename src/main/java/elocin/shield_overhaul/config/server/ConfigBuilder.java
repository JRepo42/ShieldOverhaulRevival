package elocin.shield_overhaul.config.server;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import elocin.shield_overhaul.ShieldOverhaul;
import net.fabricmc.loader.api.FabricLoader;

public class ConfigBuilder {
    public static final Gson BUILDER = (new GsonBuilder()).setPrettyPrinting().create();

    public static final Path file = FabricLoader.getInstance().getConfigDir()
            .resolve(ShieldOverhaul.MOD_ID+".json");

    public static ConfigEntries loadConfig() {
        try {
            if (Files.notExists(file)) {
                ConfigEntries configEntries = new ConfigEntries();

                configEntries.blacklisted_shields.add("// Items here will not use Shield Overhaul's mechanics");
                configEntries.blacklisted_shields.add("examplemod:epic_shield");

                configEntries.enable_instant_shield_use = true;

                configEntries.bosses_immune_to_stun = true;
                configEntries.add_stun_immunity = true;

                configEntries.bash_only_on_ground = true;
                configEntries.bash_damage = 2.0F;
                configEntries.bash_cooldown_secs = 2;
                configEntries.bash_stun_duration_secs = 0.5f;
                configEntries.bash_distance_multiplier = 1.0F;
                configEntries.vigor_strength_multiplier_per_level = 0.33F;
                configEntries.flameborn_chance_decimal = 0.1f;
                configEntries.flameborn_on_fire_time_secs = 3;

                configEntries.enable_parrying = true;
                configEntries.enable_bashing = true;
                configEntries.parry_duration_secs = 1.0F;
                configEntries.parry_cooldown_secs = 0.5f;
                configEntries.parry_stun_duration_secs = 1.0f;

                String defaultJson = BUILDER.toJson(configEntries);
                Files.writeString(file, defaultJson);
            } else {
                String json = Files.readString(file);
                ConfigEntries configEntries = BUILDER.fromJson(json, ConfigEntries.class);

                configEntries.blacklisted_shields = (configEntries.blacklisted_shields == null) ? new ArrayList<>() : configEntries.blacklisted_shields;

                String updatedJson = BUILDER.toJson(configEntries);
                Files.writeString(file, updatedJson);
                return configEntries;
            }
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        return new ConfigEntries();
    }
}
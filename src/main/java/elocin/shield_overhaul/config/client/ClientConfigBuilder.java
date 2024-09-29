package elocin.shield_overhaul.config.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import elocin.shield_overhaul.ShieldOverhaul;
import elocin.shield_overhaul.config.server.ConfigEntries;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class ClientConfigBuilder {
    public static final Gson BUILDER = (new GsonBuilder()).setPrettyPrinting().create();

    public static final Path file = FabricLoader.getInstance().getConfigDir()
            .resolve(ShieldOverhaul.MOD_ID+"_client.json");

    public static ClientConfigEntries loadClientConfig() {
        try {
            if (Files.notExists(file)) {
                ClientConfigEntries clientConfigEntries = new ClientConfigEntries();
                clientConfigEntries.icon_scale = 0.8f;
                clientConfigEntries.bash_icon_x = -24;
                clientConfigEntries.bash_icon_y = -8;
                clientConfigEntries.parry_icon_x = 10;
                clientConfigEntries.parry_icon_y = -6;

                String defaultJson = BUILDER.toJson(clientConfigEntries);
                Files.writeString(file, defaultJson);
            } else {
                String json = Files.readString(file);
                ClientConfigEntries clientConfigEntries = BUILDER.fromJson(json, ClientConfigEntries.class);

                String updatedJson = BUILDER.toJson(clientConfigEntries);
                Files.writeString(file, updatedJson);
                return clientConfigEntries;
            }
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        return new ClientConfigEntries();
    }
}
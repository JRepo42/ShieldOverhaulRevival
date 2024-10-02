package elocin.shield_overhaul.config;

import java.nio.file.Path;
import net.fabricmc.loader.api.FabricLoader;

public class ConfigFolder {
    public static String getFile(String file) {
        Path folder = FabricLoader.getInstance().getConfigDir().resolve("shield_overhaul");

        if (!folder.toFile().exists())
            folder.toFile().mkdir();

        return FabricLoader.getInstance().getConfigDir().resolve("shield_overhaul").resolve(file).toString();
    }
}
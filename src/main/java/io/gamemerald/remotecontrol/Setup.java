package io.gamemerald.remotecontrol;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Setup {
    private static FileConfiguration cfg;

    public static void setupConfig(JavaPlugin plugin) throws IOException {
        plugin.saveConfig();
        plugin.getConfig();

        cfg = plugin.getConfig();
        cfg.addDefault("client.uri", "ws://localhost:8082");
        cfg.addDefault("dev.debug", false);
        cfg.addDefault("dev.plugins", false);
        cfg.addDefault("server.server", false);
        cfg.addDefault("server.port", 8082);
        cfg.addDefault("client.client", false);
        cfg.options().copyDefaults(true);
        plugin.saveConfig();
    }

    public static FileConfiguration getCfg(){
        return cfg;
    }

}

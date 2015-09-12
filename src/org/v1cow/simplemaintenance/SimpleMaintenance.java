package org.v1cow.simplemaintenance;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.v1cow.simplemaintenance.commands.Maintenance;
import org.v1cow.simplemaintenance.listeners.PluginListener;

public class SimpleMaintenance extends JavaPlugin {

    public static SimpleMaintenance instance;
    Config config = Config.getInstance();

    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new PluginListener(), this);
        instance = this;
        config.setup(instance);
        getConfig().options().copyDefaults(true);
        saveConfig();
        getCommand("maintenance").setExecutor(new Maintenance());
    }

    public void onDisable() {
        instance = null;
    }

    public static SimpleMaintenance getInstance() {
        return instance;
    }
}
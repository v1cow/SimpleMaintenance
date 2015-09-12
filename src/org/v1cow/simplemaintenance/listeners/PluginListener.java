package org.v1cow.simplemaintenance.listeners;

import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.util.CachedServerIcon;
import org.v1cow.simplemaintenance.Config;
import org.v1cow.simplemaintenance.SimpleMaintenance;

public class PluginListener implements Listener {
    Config settings = Config.getInstance();


    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (settings.getData().getBoolean("maintenance.status") == true) {
            if (!p.hasPermission("simplemaintenance.bypass")) {
                e.setJoinMessage(null);
                p.kickPlayer(ChatColor.translateAlternateColorCodes('&', SimpleMaintenance.getInstance().getConfig().getString("joinmsg").replace("%player%", p.getName())));
            }
        }
    }

}

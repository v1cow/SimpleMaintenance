package org.v1cow.simplemaintenance.commands;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.v1cow.simplemaintenance.Config;
import org.v1cow.simplemaintenance.SimpleMaintenance;

public class Maintenance implements CommandExecutor {

    Config settings = Config.getInstance();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("maintenance")) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Invalid arguments.");
                return true;
            }
            if (args[0].equalsIgnoreCase("info")) {
                sender.sendMessage(ChatColor.GOLD + "v1cow's " + ChatColor.GREEN + "SimpleMaintenance plugin!");
                sender.sendMessage(ChatColor.GOLD + "Version 1.0.0");
                return true;
            }
            if (args[0].equalsIgnoreCase("toggle")) {
                if (sender.hasPermission("simplemaintenance.toggle")) {
                    if (settings.getData().getBoolean("maintenance.status") == true) {
                        settings.getData().set("maintenance.status", false);
                        settings.saveData();
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', SimpleMaintenance.getInstance().getConfig().getString("maintenance_off").replace("%player%", sender.getName())));
                    } else {
                        settings.getData().set("maintenance.status", true);
                        settings.saveData();
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', SimpleMaintenance.getInstance().getConfig().getString("maintenance_on").replace("%player%", sender.getName())));
                        for (Player onlinep : Bukkit.getOnlinePlayers()) {
                            if (!onlinep.hasPermission("simplemaintenance.bypass")) {
                                String kickmessage = ChatColor.translateAlternateColorCodes('&', SimpleMaintenance.getInstance().getConfig().getString("kickmsg").replace("%player%", onlinep.getName()));
                                onlinep.kickPlayer(kickmessage);
                            }
                        }
                    }
                } else {
                    sender.sendMessage(ChatColor.DARK_RED + "You do not have permission to perform this commnad.");
                }
            }
            if (args[0].equalsIgnoreCase("check")) {
                if (sender.hasPermission("simplemaintenance.check")) {
                    if (settings.getData().getBoolean("maintenance.status") == true) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', SimpleMaintenance.getInstance().getConfig().getString("maintenance_enabled")));
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', SimpleMaintenance.getInstance().getConfig().getString("maintenance_disabled")));
                    }
                } else {
                    sender.sendMessage(ChatColor.DARK_RED + "You do not have permission to perform this command.");
                }
            }
        }
        return true;
    }
}

package com.emicb.lockbar.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class Lock implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration config = Bukkit.getPluginManager().getPlugin("lockbar").getConfig();

        boolean locked = !config.getBoolean("lock-all");
        config.set("lock-all", locked);

        Bukkit.broadcastMessage("Inventory lock: " + locked);
        return true;
    }
}

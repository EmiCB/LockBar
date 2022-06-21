package com.emicb.lockbar.commands;

import com.emicb.lockbar.Lockbar;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class LockAll implements CommandExecutor {
    private static final String LOCK_ALL_PERM = Lockbar.PERM_PREFIX + ".lock-all";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // exit if no sufficient permission
        if (!sender.hasPermission(LOCK_ALL_PERM)) {
            sender.sendMessage("You do not have permission to use this command!");
            return false;
        }

        // pull from config and toggle
        FileConfiguration config = Lockbar.getInstance().getConfig();

        boolean locked = !config.getBoolean("lock-all");
        config.set("lock-all", locked);

        sender.sendMessage("Global inventory lock: " + locked);
        return true;
    }
}

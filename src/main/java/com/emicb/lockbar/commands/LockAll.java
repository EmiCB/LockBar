package com.emicb.lockbar.commands;

import com.emicb.lockbar.Lockbar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * The command to lock or unlock the entire inventory for all players.
 * Command: "/lock-all"
 */
public class LockAll implements CommandExecutor {
    /** The permission level required to use this command. */
    private static final String LOCK_ALL_PERM = Lockbar.PERM_PREFIX + ".lock-all";

    /**
     * Toggles the global inventory lock.
     * <br><br> {@inheritDoc}
     */
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
        Lockbar.getInstance().saveConfig();

        sender.sendMessage("Global inventory lock: " + locked);
        return true;
    }
}

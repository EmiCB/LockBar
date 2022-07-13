package com.emicb.lockbar.commands;

import com.emicb.lockbar.Lockbar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * The command to lock or unlock the hotbar for all players.
 * Command: "/lock-bar"
 */
public class LockBar implements CommandExecutor {
    /** The permission level required to use this command. */
    private static final String LOCK_BAR_PERM = Lockbar.PERM_PREFIX + ".lock-bar";

    /**
     * Toggles the global hotbar lock.
     * <br><br> {@inheritDoc}
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // exit if no sufficient permission
        if (!sender.hasPermission(LOCK_BAR_PERM)) {
            sender.sendMessage("You do not have permission to use this command!");
            return false;
        }

        // pull from config and toggle
        FileConfiguration config = Lockbar.getInstance().getConfig();

        boolean locked = !config.getBoolean("lock-bar");
        config.set("lock-bar", locked);
        Lockbar.getInstance().saveConfig();

        sender.sendMessage("Global hotbar lock: " + locked);
        return true;
    }
}

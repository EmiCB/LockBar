package com.emicb.lockbar.commands;

import com.emicb.lockbar.Lockbar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class UnlockOps implements CommandExecutor {
    private static final String UNLOCK_OPS_PERM = Lockbar.PERM_PREFIX + ".unlock-ops";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // exit if no sufficient permissions
        if (!sender.hasPermission(UNLOCK_OPS_PERM)) {
            sender.sendMessage("YOu do not have permission to use this command!");
            return false;
        }

        // pull from config and toggle
        FileConfiguration config = Lockbar.getInstance().getConfig();

        boolean exemptOps = !config.getBoolean("exempt-ops");
        config.set("exempt-ops", exemptOps);
        Lockbar.getInstance().saveConfig();

        sender.sendMessage("Exempt OPs: " + exemptOps);
        return true;
    }
}

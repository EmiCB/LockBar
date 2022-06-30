package com.emicb.lockbar.commands;

import com.emicb.lockbar.Lockbar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class LockSlot implements CommandExecutor {
    private static final String LOCK_SLOT_PERM = Lockbar.PERM_PREFIX + ".lock-slot";
    private final int SLOT_MIN = 0;
    private final int SLOT_MAX = 8;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // exit if no sufficient permission
        if (!sender.hasPermission(LOCK_SLOT_PERM)) {
            sender.sendMessage("You do not have permission to use this command!");
            return false;
        }

        // check if arguments present
        if (args.length < 1 || args.length > 2) {
            sender.sendMessage("Invalid number of arguments provided!");
            return false;
        }

        // check if arguments valid integers
        int min, max;
        try {
            min = Integer.parseInt(args[0]);
            if (args.length == 1) max = min;
            else max = Integer.parseInt(args[1]);
        } catch (NumberFormatException exception) {
            sender.sendMessage("Arguments must be valid integers!");
            return false;
        }

        // check if arguments within valid range
        if (min < SLOT_MIN || max > SLOT_MAX || min > max) {
            sender.sendMessage("Invalid range!");
            return false;
        }

        // set slot range in config
        Lockbar.getInstance().reloadConfig();
        FileConfiguration config = Lockbar.getInstance().getConfig();

        List<Integer> lockedSlots = config.getIntegerList("locked-slots");
        for (int i = min; i <= max; i++) {
            if (lockedSlots.contains(i)) continue;
            lockedSlots.add(i);
        }

        config.set("locked-slots", lockedSlots);
        Lockbar.getInstance().saveConfig();
        sender.sendMessage("Currently locked slots: " + lockedSlots);
        return true;
    }
}

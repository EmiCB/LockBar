package com.emicb.lockbar.commands;

import com.emicb.lockbar.Lockbar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

/**
 * The command to unlock a singular slot or a specified range of slots for all players.
 * Command: "/unlock-slot"
 */
public class UnlockSlot implements CommandExecutor {
    /** The permission level required to use this command. */
    private static final String LOCK_SLOT_PERM = Lockbar.PERM_PREFIX + ".unlock-slot";
    /** The minimum slot index that can be used. */
    private final int SLOT_MIN = 0;
    /** The maximum slot index that can be used. */
    private final int SLOT_MAX = 8;

    /**
     * Unlocks the specified slot range if valid.
     * <br><br> {@inheritDoc}
     */
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
            int ind = lockedSlots.indexOf(i);
            if (ind != -1) lockedSlots.remove(ind);
        }

        config.set("locked-slots", lockedSlots);
        Lockbar.getInstance().saveConfig();
        sender.sendMessage("Currently locked slots: " + lockedSlots);
        return true;
    }
}

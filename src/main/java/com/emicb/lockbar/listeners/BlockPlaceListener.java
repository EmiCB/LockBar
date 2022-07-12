package com.emicb.lockbar.listeners;

import com.emicb.lockbar.Lockbar;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.List;

public class BlockPlaceListener implements Listener {
    @EventHandler
    public void OnBlockPlace(BlockPlaceEvent event) {
        FileConfiguration config = Lockbar.getInstance().getConfig();

        // check if op exemption enabled
        if (config.getBoolean("exempt-ops") && event.getPlayer().isOp()) return;

        // check if whole inventory or hotbar lock are enabled
        if (config.getBoolean("lock-all") || config.getBoolean("lock-bar")) {
            event.setCancelled(true);
            return;
        }

        // check if specific slot locks are enabled
        List<Integer> lockedSlots = config.getIntegerList("locked-slots");
        if (lockedSlots.isEmpty()) return;

        // TODO: stop block placement if in a locked slot
    }
}

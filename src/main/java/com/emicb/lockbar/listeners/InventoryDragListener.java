package com.emicb.lockbar.listeners;

import com.emicb.lockbar.Lockbar;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;

import java.util.ArrayList;

public class InventoryDragListener implements Listener {
    @EventHandler
    public void OnInventoryDrag(InventoryDragEvent event) {
        FileConfiguration config = Lockbar.getInstance().getConfig();
        int[] lockedSlots = {0, 1, 2, 3, 4, 5, 6, 7, 8};

        // check if locking enabled
        if (config.getBoolean("lock-bar")) {
            for (int slot : lockedSlots) {
                if (event.getInventorySlots().contains(slot)) {
                    event.setCancelled(true);
                }
            }
        }
    }
}

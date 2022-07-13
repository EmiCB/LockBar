package com.emicb.lockbar.listeners;

import com.emicb.lockbar.Lockbar;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;

import java.util.List;

// TODO: can be moved into a singular InventoryChange class with InventoryClick?

/**
 * Listens for events where an item is dragged in the inventory.
 */
public class InventoryDragListener implements Listener {
    /**
     * Cancels inventory drags into locked slots.
     * @param event The InventoryDragEvent.
     */
    @EventHandler
    public void OnInventoryDrag(InventoryDragEvent event) {
        FileConfiguration config = Lockbar.getInstance().getConfig();
        List<Integer> lockedSlots = config.getIntegerList("locked-slots");

        // check if op exemption enabled
        if (config.getBoolean("exempt-ops") && event.getWhoClicked().isOp()) return;

        // check if locking enabled
        if (config.getBoolean("lock-bar")) {
            lockedSlots.clear();
            for (int i = 0; i < 8; i++) {
                lockedSlots.add(i);
            }
        }

        // cancel event for all locked hotbar slots
        // TODO: can probably be made more efficient
        for (int slot : lockedSlots) {
            if (event.getInventorySlots().contains(slot)) {
                event.setCancelled(true);
            }
        }
    }
}

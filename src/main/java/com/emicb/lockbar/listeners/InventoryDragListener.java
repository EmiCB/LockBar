package com.emicb.lockbar.listeners;

import com.emicb.lockbar.Lockbar;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;

import java.util.List;

public class InventoryDragListener implements Listener {
    @EventHandler
    public void OnInventoryDrag(InventoryDragEvent event) {
        FileConfiguration config = Lockbar.getInstance().getConfig();
        List<Integer> lockedSlots = config.getIntegerList("locked-slots");

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

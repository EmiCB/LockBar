package com.emicb.lockbar.listeners;

import com.emicb.lockbar.Lockbar;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

/**
 * Listens for events where items could be dropped.
 */
public class ItemDropListener implements Listener {

    /**
     * Cancel any item drops.
     * @param event The ItemDropEvent.
     */
    @EventHandler
    public void OnItemDrop(PlayerDropItemEvent event) {
        FileConfiguration config = Lockbar.getInstance().getConfig();

        // check if global inventory lock is enabled
        if (config.getBoolean("lock-all")) {
            event.setCancelled(true);
        }
    }
}

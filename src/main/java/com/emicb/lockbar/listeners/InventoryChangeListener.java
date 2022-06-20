package com.emicb.lockbar.listeners;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Listens for events where the player's inventory could be changed.
 */
public class InventoryChangeListener implements Listener {

    /**
     * Cancel any InventoryClick event.
     * @param event The InventoryClickEvent.
     */
    @EventHandler
    public void OnInventoryClick(InventoryClickEvent event) {
        FileConfiguration config = Bukkit.getPluginManager().getPlugin("lockbar").getConfig();
        if (config.getBoolean("lock-all")) {
            event.setCancelled(true);
        }
    }
}

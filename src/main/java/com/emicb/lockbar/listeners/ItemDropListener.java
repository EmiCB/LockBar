package com.emicb.lockbar.listeners;

import org.bukkit.Bukkit;
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
        FileConfiguration config = Bukkit.getPluginManager().getPlugin("lockbar").getConfig();
        if (config.getBoolean("lock-all")) {
            event.setCancelled(true);
        }
    }
}

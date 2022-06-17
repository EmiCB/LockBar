package com.emicb.lockbar.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryChangeListener implements Listener {

    @EventHandler
    public void OnInventoryClick(InventoryClickEvent event) {
        event.setCancelled(true);
    }
}

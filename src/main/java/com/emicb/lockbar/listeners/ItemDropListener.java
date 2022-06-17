package com.emicb.lockbar.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class ItemDropListener implements Listener {

    @EventHandler
    public void OnItemDrop(PlayerDropItemEvent event) {
        event.setCancelled(true);
    }
}

package com.emicb.lockbar.listeners;

import com.emicb.lockbar.Lockbar;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

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
            return;
        }

        // check if global hotbar lock is enabled
        if (config.getBoolean("lock-bar")) {
            // check material types to determine if item is in hotbar
            Material dropType = event.getItemDrop().getItemStack().getType();
            for (int i = 0; i < 8; i++) {
                ItemStack slot = event.getPlayer().getInventory().getItem(i);
                if (slot != null && dropType.equals(slot.getType())) {
                    event.setCancelled(true);
                }
            }

        }
    }
}

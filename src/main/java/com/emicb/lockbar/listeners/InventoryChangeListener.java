package com.emicb.lockbar.listeners;

import com.emicb.lockbar.Lockbar;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

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
        FileConfiguration config = Lockbar.getInstance().getConfig();

        // exit if inventory not clicked
        if (event.getClickedInventory() == null) return;

        // check if global inventory lock is enabled
        if (config.getBoolean("lock-all")) {
            event.setCancelled(true);
        }

        // check if global hotbar lock is enabled
        if (config.getBoolean("lock-bar")) {
            if (event.getView().getBottomInventory().getType().equals(InventoryType.PLAYER)) {
                event.getWhoClicked().setItemOnCursor(new ItemStack(Material.AIR));
                event.setCancelled(true);
            }
        }
    }
}

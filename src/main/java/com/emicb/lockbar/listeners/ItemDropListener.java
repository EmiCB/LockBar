package com.emicb.lockbar.listeners;

import com.emicb.lockbar.Lockbar;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

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

        // check if op exemption enabled
        if (config.getBoolean("exempt-ops") && event.getPlayer().isOp()) return;

        // check if global inventory lock is enabled
        if (config.getBoolean("lock-all")) {
            event.setCancelled(true);
            return;
        }

        // get dropped item type
        Material dropType = event.getItemDrop().getItemStack().getType();

        // check if global hotbar lock is enabled
        if (config.getBoolean("lock-bar")) {
            // check material types to determine if item is in hotbar
            for (int slot = 0; slot < 8; slot++) {
                ItemStack slotItem = event.getPlayer().getInventory().getItem(slot);
                if (slotItem != null && dropType.equals(slotItem.getType())) {
                    event.setCancelled(true);
                    return;
                }
            }
        }

        // check if specific slot locks are enabled
        List<Integer> lockedSlots = config.getIntegerList("locked-slots");
        if (lockedSlots.isEmpty()) return;

        // check material type to determine if item is in locked slot(s)
        for (int slot : lockedSlots) {
            ItemStack slotItem = event.getPlayer().getInventory().getItem(slot);
            if (slotItem != null && dropType.equals(slotItem.getType())) {
                event.setCancelled(true);
                return;
            }
        }
    }
}

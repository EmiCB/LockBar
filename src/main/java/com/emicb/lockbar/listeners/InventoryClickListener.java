package com.emicb.lockbar.listeners;

import com.emicb.lockbar.Lockbar;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Listens for events where the player's inventory could be changed.
 */
public class InventoryClickListener implements Listener {
    /** The default item for an empty cursor. */
    private final ItemStack EMPTY = new ItemStack(Material.AIR);

    /**
     * Cancel all InventoryClick events in a locked slot.
     * @param event The InventoryClickEvent.
     */
    @EventHandler
    public void OnInventoryClick(InventoryClickEvent event) {
        FileConfiguration config = Lockbar.getInstance().getConfig();

        // exit if inventory not clicked or not player's inventory
        if (event.getClickedInventory() == null) return;
        if (event.getClickedInventory().getType() != InventoryType.PLAYER) return;

        // get player
        HumanEntity player = event.getWhoClicked();

        // check if op exemption enabled
        if (config.getBoolean("exempt-ops") && player.isOp()) return;

        // check if global inventory lock is enabled
        if (config.getBoolean("lock-all")) {
            player.setItemOnCursor(EMPTY);
            event.setCancelled(true);
            return;
        }

        // check if global hotbar lock is enabled
        if (config.getBoolean("lock-bar")) {
            if (event.getSlotType().equals(InventoryType.SlotType.QUICKBAR)) {
                // remove item on cursor when attempting to pick up from locked slot
                if (event.getCursor().getAmount() == 0) { player.setItemOnCursor(EMPTY); }
                event.setCancelled(true);
                return;
            }

        }

        // check if specific slot locks enabled
        List<Integer> lockedSlots = config.getIntegerList("locked-slots");
        if (lockedSlots.isEmpty()) return;

        if (lockedSlots.contains(event.getSlot())) {
            // remove item on cursor when attempting to pick up from locked slot
            if (event.getCursor().getAmount() == 0) { player.setItemOnCursor(EMPTY); }
            event.setCancelled(true);
        }
    }
}

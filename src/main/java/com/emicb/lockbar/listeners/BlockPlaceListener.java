package com.emicb.lockbar.listeners;

import com.emicb.lockbar.Lockbar;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {
    @EventHandler
    public void OnBlockPlace(BlockPlaceEvent event) {
        FileConfiguration config = Lockbar.getInstance().getConfig();

        if (config.getBoolean("lock-all") || config.getBoolean("lock-bar")) {
            event.setCancelled(true);
        }
    }
}

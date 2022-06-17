package com.emicb.lockbar;

import com.emicb.lockbar.listeners.InventoryChangeListener;
import com.emicb.lockbar.listeners.ItemDropListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * The main class for LockBar.
 */
public final class Lockbar extends JavaPlugin {
    @Override
    public void onEnable() {
        PluginManager pluginManager = Bukkit.getPluginManager();

        // register listeners
        pluginManager.registerEvents(new ItemDropListener(), this);
        pluginManager.registerEvents(new InventoryChangeListener(), this);
    }

    @Override
    public void onDisable() {

    }
}

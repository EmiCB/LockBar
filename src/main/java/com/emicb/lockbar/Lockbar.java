package com.emicb.lockbar;

import com.emicb.lockbar.commands.Lock;
import com.emicb.lockbar.listeners.InventoryChangeListener;
import com.emicb.lockbar.listeners.ItemDropListener;
import org.bukkit.Bukkit;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * The main class for LockBar.
 */
public final class Lockbar extends JavaPlugin {
    /** LockBar permission prefix. */
    public static final String PERM_PREFIX = "lockbar";

    @Override
    public void onEnable() {
        PluginManager pluginManager = Bukkit.getPluginManager();

        // register listeners
        pluginManager.registerEvents(new ItemDropListener(), this);
        pluginManager.registerEvents(new InventoryChangeListener(), this);

        // register commands
        getServer().getPluginCommand("lock").setExecutor(new Lock());

        // register permissions
        Permission parent = new Permission(PERM_PREFIX + ".*");
        pluginManager.addPermission(parent);

        // config
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {

    }
}

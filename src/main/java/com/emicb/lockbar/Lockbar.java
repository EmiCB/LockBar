package com.emicb.lockbar;

import com.emicb.lockbar.commands.*;
import com.emicb.lockbar.listeners.BlockPlaceListener;
import com.emicb.lockbar.listeners.InventoryClickListener;
import com.emicb.lockbar.listeners.InventoryDragListener;
import com.emicb.lockbar.listeners.ItemDropListener;
import org.bukkit.Bukkit;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * The main class for LockBar.
 */
public final class Lockbar extends JavaPlugin {
    /** The current instance of the plugin. */
    private static Lockbar plugin;
    /** LockBar permission prefix. */
    public static final String PERM_PREFIX = "lockbar";

    @Override
    public void onEnable() {
        plugin = this;
        PluginManager pluginManager = Bukkit.getPluginManager();
        saveDefaultConfig();

        // register listeners
        pluginManager.registerEvents(new ItemDropListener(), this);
        pluginManager.registerEvents(new InventoryClickListener(), this);
        pluginManager.registerEvents(new BlockPlaceListener(), this);
        pluginManager.registerEvents(new InventoryDragListener(), this);

        // register commands
        getCommand("lock-all").setExecutor(new LockAll());
        getCommand("lock-bar").setExecutor(new LockBar());
        getCommand("lock-slot").setExecutor(new LockSlot());
        getCommand("unlock-slot").setExecutor(new UnlockSlot());
        getCommand("unlock-ops").setExecutor(new UnlockOps());

        // register permissions
        Permission parent = new Permission(PERM_PREFIX + ".*");
        pluginManager.addPermission(parent);
    }

    @Override
    public void onDisable() {

    }

    /** @return The current instance of the plugin. */
    public static Lockbar getInstance() { return plugin; }
}

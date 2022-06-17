package com.emicb.lockbar;

import com.emicb.lockbar.listeners.ItemDropListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Lockbar extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getServer().getConsoleSender().sendMessage("LockBar loaded successfully!");

        // enable listeners
        Bukkit.getServer().getPluginManager().registerEvents(new ItemDropListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getServer().getConsoleSender().sendMessage("LockBar shut down successfully!");
    }
}

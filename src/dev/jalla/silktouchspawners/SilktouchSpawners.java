package dev.jalla.silktouchspawners;

import org.bukkit.plugin.java.JavaPlugin;

import dev.jalla.silktouchspawners.listeners.BlockBreakListener;
import dev.jalla.silktouchspawners.listeners.BlockPlaceListener;

public final class SilktouchSpawners extends JavaPlugin {

    @Override
    public void onEnable() {
    	getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
    	getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);
    	
    }
    // Fired when plugin is disabled
    @Override
    public void onDisable() {

    }
}

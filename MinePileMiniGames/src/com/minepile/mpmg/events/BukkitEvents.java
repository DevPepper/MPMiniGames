package com.minepile.mpmg.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.minepile.mpmg.MPMGMain;

public class BukkitEvents implements Listener {
	
	MPMGMain plugin;
	
	public BukkitEvents(MPMGMain plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {}
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {}

}

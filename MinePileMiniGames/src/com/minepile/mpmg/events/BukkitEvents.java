package com.minepile.mpmg.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.minepile.mpmg.MPMGMain;
import com.minepile.mpmg.util.ChatManager;
import com.minepile.mpmg.util.ConnectionManager;

public class BukkitEvents implements Listener {
	
	//Load objects
	MPMGMain plugin = MPMGMain.plugin;
	private ChatManager chatManager = new ChatManager();
	private PlayerJoinServerEvent playerJoinServerEvent = new PlayerJoinServerEvent();
	private PlayerRespawnServerEvent playerRespawnServerEvent = new PlayerRespawnServerEvent();
	private ConnectionManager connectionManager = new ConnectionManager();
	
	public BukkitEvents(MPMGMain plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	//PlayerJoinEvent fired when someone logs into server
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		String playerName = player.getName();
		int playerCount = connectionManager.getPlayerCount();
		
		//Show Personal welcome message
		player.sendMessage(chatManager.welcomeMessage(playerName));
		//Show server version
		player.sendMessage(chatManager.serverVersion());
		//Show message to all players.
		event.setJoinMessage(chatManager.playerJoinMessage(playerName, playerCount));
		
		//Toggle player join minigame event!
		playerJoinServerEvent.togglePlayerJoinServerEvent(player);
	}
	
	//PlayerJoinEvent fired when someone leaves/quits the server
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		String playerName = player.getName();
		int playerCount = connectionManager.getPlayerCount();

		//Show message to all players.
		event.setQuitMessage(chatManager.playerQuitMessage(playerName, playerCount));
	}
	
	//PlayerRespawnEvent fired when someone respawns after death
	@EventHandler
	public void onPlayerRespawn(final PlayerRespawnEvent event) {
		new BukkitRunnable() {
	          @Override
	          public void run() {
	        	  Player player = event.getPlayer();
	        	  //Toggle player join minigame event!
	        	  playerRespawnServerEvent.togglePlayerRespawnServerEvent(player);
	          }
		}.runTaskLater(this.plugin, 1); //run after 1 tick
		
	}
	
	//Temporary Anti-Grief
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (!(player.isOp())) { //Cancel event if not Operator
			if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
				event.setCancelled(true);
			}
	    }
	}
 
}

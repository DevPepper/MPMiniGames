package com.minepile.mpmg.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.minepile.mpmg.MPMGMain;
import com.minepile.mpmg.minigame.MiniGameTDM;
import com.minepile.mpmg.util.ChatManager;
import com.minepile.mpmg.util.ConnectionManager;
import com.minepile.mpmg.util.GameManager;
import com.minepile.mpmg.util.TeamManager;

public class BukkitEvents implements Listener {
	
	//Load objects
	MPMGMain plugin = MPMGMain.plugin;
	private ChatManager chatManager = new ChatManager();
	private PlayerJoinServerEvent playerJoinServerEvent = new PlayerJoinServerEvent();
	private PlayerRespawnServerEvent playerRespawnServerEvent = new PlayerRespawnServerEvent();
	private PlayerQuitServerEvent playerQuitServerEvent = new PlayerQuitServerEvent();
	private ConnectionManager connectionManager = new ConnectionManager();
	GameManager gameManager;
	
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
		//Show message to all player.
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
		//Show message to all player.
		event.setQuitMessage(chatManager.playerQuitMessage(playerName, playerCount));
		playerQuitServerEvent.togglePlayerQuitServerEvent();
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
	
	//If the arena count down is active, dont let player move
	@EventHandler
	public void freezePlayer(PlayerMoveEvent event) {
		if(GameManager.isCountDownOver() == false && GameManager.isGameActive() == true) {
			if (!event.getFrom().toVector().equals(event.getTo().toVector())) {
			    event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onDeath(EntityDeathEvent e){
	  if(e.getEntity() instanceof Player){
	     Player player = (Player) e.getEntity();
	     player.setHealth(20.0);
	     player.setFoodLevel(20);
	     
	     //update score
	     if (player.getKiller() instanceof Player) {
	    	 MiniGameTDM.updateScore(player);
	     }
	     
	     //we will do basic teleporting for now.
	     if (GameManager.isGameActive() == true) {
				WorldCreator worldCreator = new WorldCreator("TDM");
				worldCreator.createWorld();
				World world = Bukkit.getWorld("TDM");
				world.setPVP(true);
				world.setStorm(false);
				world.setMonsterSpawnLimit(0);
				world.setAnimalSpawnLimit(0);
				world.setSpawnFlags(false, false);
				//Get all player online in an array, and teleport them all and play a sound
				
					//chatManager.debugMessage(TeamManager.getTeamHashMap().toString());
					if(TeamManager.getPlayerTeam(player) == "red"){
						//redteam spawn
						Location teleportloc = new Location(world, 0.5, 80, 29.5);
						player.teleport(teleportloc); //Teleport player
						player.setPlayerTime(6000, false); //Set world time
					} else if (TeamManager.getPlayerTeam(player) == "blue") {
						//blue team spawn
						Location teleportloc = new Location(world, 0.5, 80, -28.5);
						player.teleport(teleportloc); //Teleport player
						player.setPlayerTime(6000, false); //Set world time
					} else {
						Location teleportloc = new Location(world, 0.5, 80, 0.5);
						player.teleport(teleportloc); //Teleport player
						player.setPlayerTime(6000, false); //Set world time
					}
					player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 10); //play a sound
				
	     }
	  }
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		event.setFormat(ChatColor.YELLOW + "%s" + ": " + ChatColor.WHITE + "%s");
	}
}

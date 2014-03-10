package com.minepile.mpmg;

import org.bukkit.plugin.java.JavaPlugin;

import com.minepile.mpmg.events.BukkitEvents;
import com.minepile.mpmg.util.GameManager;
import com.minepile.mpmg.util.ScheduleManager;

public class MPMGMain extends JavaPlugin {
	
	//Global Server Variables
	private static String pluginVersion = "Pre-Alpha 0.1.3";
	private static boolean debugMessages = true;
	private static int minPlayers = 8;
	private static int maxPlayers = 16;
	
	//Load objects
	public static MPMGMain plugin;
	private GameManager gameManager = new GameManager();
	
	//Plugin is being enabled, lets do something.
	public void onEnable() {
		plugin = this;
		
		//Register BukkitEvent listener class
		new BukkitEvents(this);
		new ScheduleManager(this);
		
		//Start Core logic of MiniGame
		gameManager.startGameManager();
	}
	
	public void onDisable() {}
	
	//Getters used for global settings
	public static String getPluginVersion() {
		return pluginVersion;
	}
	public static boolean isDebugMessages() {
		return debugMessages;
	}
	public static int getMinPlayers() {
		return minPlayers;
	}
	public static int getMaxPlayers() {
		return maxPlayers;
	}

	
}

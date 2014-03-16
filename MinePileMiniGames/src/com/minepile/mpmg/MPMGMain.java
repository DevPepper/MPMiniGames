package com.minepile.mpmg;

import org.bukkit.plugin.java.JavaPlugin;

import com.minepile.mpmg.events.BukkitEvents;
import com.minepile.mpmg.util.GameManager;

public class MPMGMain extends JavaPlugin {
	
	//Global Server Variables
	private static String pluginVersion = "Pre-Alpha 0.1.5";
	private static boolean debugMessages = true;
	private static int minPlayers = 2;
	private static int maxPlayers = 16;
	
	//Load objects
	public static MPMGMain plugin;
	private GameManager gameManager = new GameManager(null);
	
	//Plugin is being enabled, lets do something.
	public void onEnable() {
		plugin = this;
		
		//Register BukkitEvent listener class
		new BukkitEvents(this);
		new GameManager(this);
		
		//Start Core logic of MiniGame
		gameManager.gameManagerTask();
	}

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

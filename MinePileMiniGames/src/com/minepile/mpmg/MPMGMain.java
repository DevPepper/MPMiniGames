package com.minepile.mpmg;

import org.bukkit.plugin.java.JavaPlugin;

import com.minepile.mpmg.minigame.MiniGame;
import com.minepile.mpmg.minigame.MiniGameFFA;

public class MPMGMain extends JavaPlugin {
	
	//Server Variables
	private static String pluginVersion = "Pre-Alpha 0.1.3";
	private static boolean debugMessages = true;
	
	//Imports
	private MiniGame miniGameFFA = new MiniGameFFA();
	
	//Plugin is being enabled, lets do something.
	public void onEnable() {
		startMiniGameLoop();
	}
	
	public void onDisable() {}
	
	//Start Game Loop!
	private void startMiniGameLoop() {
		//Load MiniGame
		miniGameFFA.loadMiniGame();
		
	}
	
	//Used in Chat Manager
	public static String getPluginVersion() {
		return pluginVersion;
	}
	public static boolean isDebugMessages() {
		return debugMessages;
	}
	
}

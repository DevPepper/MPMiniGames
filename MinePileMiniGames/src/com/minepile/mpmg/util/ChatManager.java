package com.minepile.mpmg.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import com.minepile.mpmg.MPMGMain;

public class ChatManager {
	
	//Displays a welcome message to the user
	public String welcomeMessage(String playerName) {
		return ChatColor.GOLD + "" + ChatColor.BOLD + "Welcome " + playerName + "!";
	}
	
	//Public message to display when player disconnects
	public String disconnectMessage(String playerName) {
		return ChatColor.RED + "" + ChatColor.BOLD + playerName + " disconnected!";
	}
	
	//Load Tips to be displayed to the user about the
	//game mode they are playing.
	public void loadTips() {}
	
	//Displays a random tip about the current miniGame
	public void randomTip(String randomTip) {
		Bukkit.broadcastMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + " TIP >> " + randomTip);
	}
	
	//Show Debug Message
	public void debugMessage(String debugMsg) {
		if(MPMGMain.isDebugMessages() == true) {
			Bukkit.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + "  Debug>>  " + debugMsg);
		}
	}
	
	//Shows the plugin Version (static)
	public String serverVersion() {
		return ChatColor.YELLOW + "" + ChatColor.BOLD + " >> " + ChatColor.WHITE + "" + 
				ChatColor.BOLD + "MinePile MiniGames version: " + MPMGMain.getPluginVersion();
	}
	
}

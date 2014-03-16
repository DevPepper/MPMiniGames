package com.minepile.mpmg.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

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
	
	//Displays a random tip about the current miniGame
	public void randomTip(String randomTip) {
		for(Player player : Bukkit.getOnlinePlayers()) {
			player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + " TIP >> " + randomTip);
		}
	}
	
	//Show Debug Message
	public void debugMessage(String debugMsg) {
		if(MPMGMain.isDebugMessages() == true) {
			Bukkit.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + "  <<Debug>>  " + debugMsg);
		}
	}
	
	//Shows the plugin Version (static)
	public String serverVersion() {
		return ChatColor.YELLOW + "" + ChatColor.BOLD + " >> " + ChatColor.WHITE + "" + 
				ChatColor.BOLD + "MinePile MiniGames version: " + MPMGMain.getPluginVersion();
	}
	
	//Shows users as they login
	public String playerJoinMessage(String playerName, int playerCount) {
		return ChatColor.GREEN + " + " + ChatColor.GRAY + playerName + 
				" joined the game. (" + playerCount + "/" + MPMGMain.getMaxPlayers() +  ")";
	}
	
	//Shows users as they logout
	public String playerQuitMessage(String playerName, int playerCount) {
		return ChatColor.RED + " - " + ChatColor.GRAY + playerName + 
				" left the game. (" + (playerCount - 1) + "/" + MPMGMain.getMaxPlayers() +  ")";
	}
	
	
	//Color Count down
	public void colorCountDown (int timeCount) {
		for(Player player : Bukkit.getOnlinePlayers()) {
			String timeCountString = Integer.toString(timeCount);		
			//Show countdown at 60, 45, 30, 20, and 15 seconds.
			if (timeCount == 30 || timeCount == 15) {
				player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Countdown: " + timeCountString + " seconds left!");
			} else if (timeCount <= 10 && timeCount >= 3) { //Show bold green countdown for ever second between 10 and 3 seconds.
				player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Countdown: " + timeCountString + " seconds left!");
				//play a sound
			    player.playSound(player.getLocation(), Sound.NOTE_PLING, 1, 7);
			} else if (timeCount == 2) { //Show bold yellow countdown for 2 seconds left.
				player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "Countdown: " + timeCountString + " seconds left!");
				//play a sound
			    player.playSound(player.getLocation(), Sound.NOTE_PLING, 1, 7);
			} else if (timeCount == 1) { //Show bold red countDown for 1 second left.
				player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Countdown: " + timeCountString + " second left!");
				//play a sound
			    player.playSound(player.getLocation(), Sound.NOTE_PIANO, 1, 7);
			} else {
				//Want to see all the numbers? Uncomment the line below
				//debugMessage(timeCountString);
			}
		}
	}
}

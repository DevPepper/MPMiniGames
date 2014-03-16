package com.minepile.mpmg.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ConnectionManager {

	//Remove disconnected player from []playerList
	public void removePlayer() {
		//remove player from the playerList
	}
		
	//Get the number of players online
	public int getPlayerCount() {
		return Bukkit.getOnlinePlayers().length;
	}
	
	//Get players online
	public String getPlayersOnline() {
		StringBuilder stringBuilder = new StringBuilder();
		
		for(Player allPlayers : Bukkit.getServer().getOnlinePlayers()) {
			stringBuilder.append(allPlayers.getName() + ", ");
			if (allPlayers != null) {
				return stringBuilder.toString();
			}
		}
		return "No players online.";
	}
	
	//Get players online
	public Player[] getOnlinePlayers() {
		return Bukkit.getServer().getOnlinePlayers();
	}
	
}

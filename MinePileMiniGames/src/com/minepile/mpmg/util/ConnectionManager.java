package com.minepile.mpmg.util;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ConnectionManager {
	
	private ArrayList<String> playerList = null;
	
	//Get playerList
	public ArrayList<String> getPlayerList() {
		return this.playerList;
	}
	//Updates (sets) the ArrayList
	public void updatePlayerList() {
		//Get array of players online
		for(Player allPlayers : Bukkit.getServer().getOnlinePlayers()) {
			playerList.add(allPlayers.getName());
		}
	}
	
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
	
}

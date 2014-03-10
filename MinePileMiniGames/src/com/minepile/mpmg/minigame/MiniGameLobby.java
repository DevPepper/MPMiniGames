package com.minepile.mpmg.minigame;

import org.bukkit.Bukkit;
import org.bukkit.World;

import com.minepile.mpmg.util.CountDownManager;

public class MiniGameLobby extends MiniGame {
	//Variables that Make a MiniGame
	private String[] kits = {"Kit 1"};
	private String[] teams = {"Player"};
	private World world = Bukkit.getWorld("world");
	
	//Variables to give user info
	private String miniGameName = "Lobby";
	private String miniGameDescription = "";
	private String worldAuthor = "";
	
	//Define constructor instructions
	public MiniGameLobby() {}
	
	public void loadMiniGame() {
		//Setup FFA MiniGame
		setKits(kits);
		setTeams(teams);
		setWorld(world);
		setMiniGameName(miniGameName);
		setMiniGameDescription(miniGameDescription);
		setWorldAuthor(worldAuthor);
	}
}
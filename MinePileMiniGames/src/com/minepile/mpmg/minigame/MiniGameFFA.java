package com.minepile.mpmg.minigame;

import org.bukkit.Bukkit;
import org.bukkit.World;

public class MiniGameFFA extends MiniGame {

	//Variables that Make a MiniGame
	private String[] kits = {"Kit 1"};
	private String[] teams = {"Red", "Blue"};
	private World world = Bukkit.getWorld("Map01");
	
	//Variables to give user info
	private String miniGameName = "Free For All";
	private String miniGameDescription = "Last player alive wins!";
	private String worldAuthor = "unenergizer";
	
	//Define constructor instructions
	public MiniGameFFA() {}
	
	public void loadMiniGame() {
		//Debug Message
		chatManager.debugMessage("The loadMiniGame() method invoked");
		
		//Setup FFA MiniGame
		setKits(kits);
		setTeams(teams);
		setWorld(world);
		setMiniGameName(miniGameName);
		setMiniGameDescription(miniGameDescription);
		setWorldAuthor(worldAuthor);
	}
}

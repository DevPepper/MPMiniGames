package com.minepile.mpmg.minigame;

import org.bukkit.World;

import com.minepile.mpmg.util.ChatManager;
import com.minepile.mpmg.util.InfoManager;

public abstract class MiniGame {

	//Load objects
	ChatManager chatManager = new ChatManager();
	InfoManager infoManager = new InfoManager();

	//Variables that Make a MiniGame
	private String[] kits = null;
	private String[] teams = null;
	private World world = null;
	
	//Variables to give user info
	private String miniGameName = null;
	private String miniGameDescription = null;
	private String worldAuthor = null;
	
	//Variables for keeping track of the game state
	private boolean isMapLoaded = false;
	
	//LoadMiniGame
	public abstract void loadMiniGame();
	
	public abstract void startGamelogic();
	
	//Getters and Setters
	public String[] getKits() {
		return kits;
	}
	public void setKits(String[] kits) {
		this.kits = kits;
	}
	public String[] getTeams() {
		return teams;
	}
	public void setTeams(String[] teams) {
		this.teams = teams;
	}
	public World getWorld() {
		return world;
	}
	public void setWorld(World world) {
		this.world = world;
	}
	public String getMiniGameName() {
		return miniGameName;
	}
	public void setMiniGameName(String miniGameName) {
		this.miniGameName = miniGameName;
	}
	public String getMiniGameDescription() {
		return miniGameDescription;
	}
	public void setMiniGameDescription(String miniGameDescription) {
		this.miniGameDescription = miniGameDescription;
	}
	public String getWorldAuthor() {
		return worldAuthor;
	}
	public void setWorldAuthor(String worldAuthor) {
		this.worldAuthor = worldAuthor;
	}

	public boolean isMapLoaded() {
		return isMapLoaded;
	}

	public void setMapLoaded(boolean isMapLoaded) {
		this.isMapLoaded = isMapLoaded;
	}

}

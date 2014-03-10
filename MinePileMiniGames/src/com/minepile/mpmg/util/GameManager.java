package com.minepile.mpmg.util;

import org.bukkit.Bukkit;
import org.bukkit.World;

public class GameManager {
	
	//Setup game variables
	private int gameCount = 0; //Count number of games played since last server restart.
	private boolean gameActive = false; //Set false to start server in lobby.
	private World world = Bukkit.getWorld("world"); //set default word. (world = lobby)
	
	
	//Load Objects
	private ChatManager chatManager = new ChatManager();
	private ScheduleManager scheduleManager = new ScheduleManager(null);
	private ConnectionManager connectionManager = new ConnectionManager();
	
	//Lets start the core logic of our game
	public void startGameManager() {
		if (gameActive == false) {
			startLobby();
		} else {
			startGame();
		}
	}
	
	public void startLobby() {
		chatManager.debugMessage("Entered Lobby");
		int playerCount = connectionManager.getPlayerCount();
		chatManager.debugMessage("playercount: " + playerCount);
		
		//Load lobby
		//Load lobby world
		//wait for players
		//start count down if enough players join

		scheduleManager.startCountDown(45);
		//set gameActive True
	}
	
	public void startGame() {
		chatManager.debugMessage("Entered Game");
		//Load game
		//load game world
		//spawn players
		//game ends
		//set gameActive false
		gameCount++; //Increment the amount of games played.

	}
	
	//Getters and Setters
	public int getGameCount() {
		return gameCount;
	}
	public void setGameCount(int gameCount) {
		this.gameCount = gameCount;
	}
	public boolean isGameActive() {
		return gameActive;
	}
	public void setGameActive(boolean gameActive) {
		this.gameActive = gameActive;
	}
	public World getWorld() {
		return world;
	}
	public void setWorld(World world) {
		this.world = world;
	}
	
}

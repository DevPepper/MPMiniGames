package com.minepile.mpmg.util;

import com.minepile.mpmg.MPMGMain;

public class LobbyManager {
	
	private ChatManager chatManager = new ChatManager();
	private ConnectionManager connectionManager = new ConnectionManager();
	
	public void loadLobby() {
		
		//Get player count.  We need it later to get minimal players before games starts.
		int playerCount = connectionManager.getPlayerCount();
		
		//If player count is greater than or equal to minimal game players, then start count down.
		if (playerCount >= MPMGMain.getMinPlayers()) {
			//toggle count down message
			chatManager.colorCountDown(GameManager.getLobbyCountDownCount());
			GameManager.setLobbyCountDownCount(GameManager.getLobbyCountDownCount() - 1); //Subtract 1 from this time variable.
			//If count down is over, shut it off
			if (GameManager.getLobbyCountDownCount() < 0) {
				
				//Lets set gameActive to true, to enter the game.
				GameManager.setGameActive(true);
				//Reset the LobbyCountDownCount for next lobby session.
				GameManager.setLobbyCountDownCount(60);
				//Cancel this Repeating Task.
				//Bukkit.getScheduler().cancelTask(taskID);

			}
		}
		//If gameActive == true then lets go to the game arena.
	}
}

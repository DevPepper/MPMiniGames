package com.minepile.mpmg.events;

import com.minepile.mpmg.MPMGMain;
import com.minepile.mpmg.util.ConnectionManager;
import com.minepile.mpmg.util.GameManager;

public class PlayerQuitServerEvent {
	
	private ConnectionManager connectionManager = new ConnectionManager();
	
	//Ran when someone quits the server
	public void togglePlayerQuitServerEvent() {
		
		//If player quits during the lobby, lets handle it here.
		int playerCount = connectionManager.getPlayerCount();
		int minPlayers =  MPMGMain.getMinPlayers();
		//If player quits and not enough players for game, reset time to 20 seconds
		//This should give the next player a fair chance to select kit and team.
		if (playerCount == minPlayers && GameManager.getLobbyCountDownCount() < 20) {
			GameManager.setLobbyCountDownCount(20);
		}
	}
}

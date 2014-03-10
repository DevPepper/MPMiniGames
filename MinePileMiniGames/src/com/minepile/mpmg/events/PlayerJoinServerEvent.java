package com.minepile.mpmg.events;

import org.bukkit.entity.Player;

import com.minepile.mpmg.util.ChatManager;
import com.minepile.mpmg.util.PlayerManager;

public class PlayerJoinServerEvent {
	
	//Load objects
	private ChatManager chatManager = new ChatManager();
	private PlayerManager playerManager = new PlayerManager();
	
	public void togglePlayerJoinServerEvent(Player player) {
		//Add player to the PlayerManager
		playerManager.MiniGamePlayer(player);
		
		//Get world that is being played on (lobby or game world)
		/*
		//Send Welcome Message
		String playerName = player.getName();
		chatManager.debugMessage(playerName + " joined");
		playerManager.sendMessage(chatManager.welcomeMessage(playerName));
		playerManager.sendMessage(chatManager.serverVersion());
		*/
	}
	
}

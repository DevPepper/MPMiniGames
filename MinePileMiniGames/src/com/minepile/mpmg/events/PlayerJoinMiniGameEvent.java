package com.minepile.mpmg.events;

import org.bukkit.entity.Player;

import com.minepile.mpmg.util.ChatManager;
import com.minepile.mpmg.util.PlayerManager;

public class PlayerJoinMiniGameEvent {
	
	//Load objects
	private ChatManager chatManager = new ChatManager();
	PlayerManager playerManager = new PlayerManager();
	
	public void togglePlayerJoinMiniGameEvent(Player player) {
		//Debug Message
		chatManager.debugMessage("togglePlayerJoinMiniGameEvent() invoked");
		
		//Add player to the PlayerManager
		playerManager.MiniGamePlayer(player);
		
		//Send Welcome Message
		String playerName = player.getName();
		chatManager.debugMessage(playerName + " joined");
		playerManager.sendMessage(chatManager.welcomeMessage(playerName));
		playerManager.sendMessage(chatManager.serverVersion());
		
		//Debug Message
		chatManager.debugMessage("End togglePlayerJoinMiniGameEvent()");
	}
	
}

package com.minepile.mpmg.events;

import org.bukkit.entity.Player;

import com.minepile.mpmg.util.ChatManager;

public class PlayerJoinMiniGameEvent {
	
	//Import utilities
	private ChatManager chatManager = new ChatManager();
	
	public void togglePlayerJoinMiniGameEvent(Player player) {
		//Debug Message
		chatManager.debugMessage("togglePlayerJoinMiniGameEvent() invoked");
		
		//Send Welcome Message
		String playerName = player.getName();
		chatManager.debugMessage(playerName + " joined");
		player.sendMessage(chatManager.welcomeMessage(playerName));
		player.sendMessage(chatManager.serverVersion());
		
		//Debug Message
		chatManager.debugMessage("End.");
	}
	
}

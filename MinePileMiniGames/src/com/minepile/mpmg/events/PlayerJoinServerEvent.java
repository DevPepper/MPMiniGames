package com.minepile.mpmg.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.minepile.mpmg.util.PlayerManager;

public class PlayerJoinServerEvent {
	
	//Load objects
	private PlayerManager playerManager = new PlayerManager();
	
	public void togglePlayerJoinServerEvent(Player player) {
		//Add player to the PlayerManager
		playerManager.MiniGamePlayer(player);
		
		//Get world that is being played on (lobby or game world)
		World world = Bukkit.getWorld("world");
		player.teleport(new Location(world , 0.5, 71, 0.5));
		
		//play a sound
	    org.bukkit.Location location = player.getLocation();
		player.playSound(location, Sound.LEVEL_UP, 1, 10);
	}
	
}

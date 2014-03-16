package com.minepile.mpmg.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

import com.minepile.mpmg.util.GameManager;

public class PlayerRespawnServerEvent {
	
	public void togglePlayerRespawnServerEvent(Player player) {		
		if (GameManager.isGameActive() == false) {
			//Get world that is being played on (lobby or game world)
			WorldCreator c = new WorldCreator("lobby");
			c.createWorld();
		    World world = Bukkit.getWorld("lobby");
		    player.teleport(new Location(world , 0.5, 71, 0.5));
			
		    //play a sound
		    org.bukkit.Location location = player.getLocation();
		    player.playSound(location, Sound.LEVEL_UP, 1, 10);
		} else {
			//Player must be in a game.. right?
			//Get world that is being played on (lobby or game world)
			WorldCreator c = new WorldCreator("gameMap01");
			c.createWorld();
		    World world = Bukkit.getWorld("gameMap01");
		    player.teleport(new Location(world , 0.5, 71, 0.5));
			
		    //play a sound
		    org.bukkit.Location location = player.getLocation();
		    player.playSound(location, Sound.LEVEL_UP, 1, 10);
		}
	}
	
}

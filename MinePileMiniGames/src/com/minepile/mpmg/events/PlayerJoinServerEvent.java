package com.minepile.mpmg.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import com.minepile.mpmg.util.PlayerManager;
import com.minepile.mpmg.util.GameManager;

public class PlayerJoinServerEvent {
	
	//Load objects
	private PlayerManager playerManager = new PlayerManager();
	
	//Player spawn even
	public void togglePlayerJoinServerEvent(Player player) {
		//Add player to the PlayerManager
		playerManager.MiniGamePlayer(player);
		org.bukkit.Location location = player.getLocation();
		//GameManager.setupScoreboard(player);
		
		
		if (GameManager.isGameActive() == false) {	
			//Get world that is being played on (lobby or game world)
			WorldCreator c = new WorldCreator("lobby");
			c.createWorld();
		    World world = Bukkit.getWorld("lobby");
		    player.teleport(new Location(world , 0.5, 71, 0.5));
		    player.setPlayerTime(6000, false); //Set world time
		    //play a sound
		    player.playSound(location, Sound.SUCCESSFUL_HIT, 1, 10);
		} else {
			//Player must be in a game.. right?
			//Get world that is being played on (lobby or game world)
			WorldCreator c = new WorldCreator("gameMap01");
			c.createWorld();
		    World world = Bukkit.getWorld("gameMap01");
		    player.teleport(new Location(world , 0.5, 71, 0.5));
		    player.setPlayerTime(6000, false); //Set world time
		    //play a sound
		    player.playSound(location, Sound.LEVEL_UP, 1, 10);
		}
	}

}

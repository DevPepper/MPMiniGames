package com.minepile.mpmg.minigame;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

public class MiniGameFFA extends MiniGame {

	//Variables that Make a MiniGame.
	//private String[] kits = {"Kit 1"};
	//private String[] teams = {"Red", "Blue"};
	
	//Variables to give user info.
	//private String miniGameName = "Free For All";
	//private String miniGameDescription = "Last player alive wins!";
	//private String worldAuthor = "unenergizer";
	
	//Game specific variables.
	
	//Define constructor instructions
	public MiniGameFFA() {}
	
	public void loadMiniGame() {
		if (isMapLoaded() == false) {
			setMapLoaded(true);
			
			//Variables
			//Load next world

			WorldCreator worldCreator = new WorldCreator("FFA");
			chatManager.debugMessage("Loading Game world: FFA");
			worldCreator.createWorld();
			World world = Bukkit.getWorld("FFA");
			world.setPVP(false);
			world.setStorm(false);
			//Next world spawn coordinates
			//Location teleportloc = new Location(world, 0.5, 71, 0.5);
			Location teleportloc = new Location(world, 0.5, 71, 0.5);
			//Get all players online in an array, and teleport them all and play a sound
			for(Player players : Bukkit.getServer().getOnlinePlayers()) {
				players.teleport(teleportloc); //Teleport player
				players.setPlayerTime(6000, false); //Set world time
				players.playSound(teleportloc, Sound.LEVEL_UP, 1, 10); //play a sound
			}
		}
		startGamelogic();
	}

	public void startGamelogic() {
		chatManager.debugMessage("startGamelogic() invoked");
	}
}

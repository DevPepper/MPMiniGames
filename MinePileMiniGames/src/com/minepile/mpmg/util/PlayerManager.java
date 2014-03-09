package com.minepile.mpmg.util;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

public class PlayerManager {
	
	//Imports
	private Player player;
	private Scoreboard scoreboard = null;
	
	//Player statistics
	private int kills = 0;				//Total player kills
	private int deaths = 0;				//Total player deaths
	private int respawns = 0;			//Total player respawns
	private int score = 0;				//Total player score
	
	//Player conditions
	private boolean isPlaying = false;	//Is player in game or in the lobby?
	private boolean isAlive = false;	//Is the player alive or dead?
	private boolean canPVP = false;		//Can the player PVP?
	
	
	public void sendMessage(String message) {
		player.sendMessage(message);
	}
	
	//Getters and setters
	public void MiniGamePlayer(Player player) {
		this.player = player;
	}
	public Player getPlayer() {
		return player;
	}
	public String getName() {
		return player.getName();
	}
	public int getKills() {
		return kills;
	}
	public void addKill() {
		kills++;
	}
	public int getDeaths() {
		return deaths;
	}
	public void addDeath() {
		deaths++;
	}
	public int getRespawns() {
		return respawns;
	}
	public void addRespawns() {
		respawns++;
	}
	public int getScore() {
		return score;
	}
	public void addScore() {
		score++;
	}
	
	//Statistics Reset
	public void resetStats() {
		kills = 0;
		deaths = 0;
		respawns = 0;
		score = 0;
		setPlaying(false);
		setAlive(false);
		setCanPVP(false);
	}
	
	//Player conditions getters and setters
	public boolean isPlaying() {
		return isPlaying;
	}
	public void setPlaying(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}
	public boolean isAlive() {
		return isAlive;
	}
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	public boolean isCanPVP() {
		return canPVP;
	}
	public void setCanPVP(boolean canPVP) {
		this.canPVP = canPVP;
	}
	
}

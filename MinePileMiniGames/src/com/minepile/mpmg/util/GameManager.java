package com.minepile.mpmg.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import com.minepile.mpmg.MPMGMain;
import com.minepile.mpmg.minigame.MiniGame;
import com.minepile.mpmg.minigame.MiniGameCTF;
import com.minepile.mpmg.minigame.MiniGameFFA;
import com.minepile.mpmg.minigame.MiniGameLMS;
import com.minepile.mpmg.minigame.MiniGameTDM;

public class GameManager {
	
	//Load objects
	private static MPMGMain plugin = MPMGMain.plugin;
	private ChatManager chatManager = new ChatManager();
	private static Scoreboard scoreboardLobby;
	private static Scoreboard scoreboardGame;
	private static Objective objective;
	
	//Timing/Task Variables
	private int ticksPerSecond = 20; //(20 ticks = 1 second)
	@SuppressWarnings("unused")
	private int taskID = -1; //ID of the bukkit scheduler
	
	//Set Defaults
	private int globalTicks = 60;
	private static int lobbyCountDown = 60; //Wait time in lobby after minimal players reached.
	private int gameStartCountDown = 20; 	//Time before the game starts, and player is unfrozen.		
	private int gameStatsDelay = 7;			//Time after game ends to show statistics, before returning to lobby.
	
	//Variable counters for tracking loops.
	private int globalTickCount = globalTicks;
	private static int lobbyCountDownCount = lobbyCountDown;
	private static boolean gameActive = false;
	private static boolean gameIsSetup = false;
	private static boolean countDownOver = false;
	private boolean varReset = false;
	private int gameStartCountDownCount = gameStartCountDown;
	@SuppressWarnings("unused")
	private int gameStatsDelayCount = gameStatsDelay;
	private int currentGame = 3; 
	private int lastGame = 0;
	
	//Load the constructor.
	@SuppressWarnings("static-access")
	public GameManager(MPMGMain plugin) {
		this.plugin = plugin;
	}
	
	
	//Run Game Manager Tasks
	public void gameManagerTask() {
		//Lets start a repeating task
		taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				
				//resetTicks when needed
				//this has not been used yet.
				//may want to consider removing.
				globalTickCount--;
				if (globalTickCount <= 0) {
					globalTickCount = globalTicks;
				}

				
				/*
				 *  Lobby Loop
				 *    
				 *	If gameActive == false then lets go to the lobby.  
				*/
				if (gameActive == false) {
					LobbyManager lobbyManager = new LobbyManager();
					lobbyManager.loadLobby();
					updateLobbyScoreboard();
					updateTeams();
					if (globalTickCount == 56 || globalTickCount == 41 || globalTickCount == 26 
							|| globalTickCount == 11 && lobbyCountDownCount > 10) {
						chatManager.randomTip("Random tip goes here! " + globalTickCount);
					}
				}
				
				
				/*
				 *  Game Loop
				 *    
				 *	If gameActive == true then lets go to the game.  
				*/
				if (gameActive == true) {
					//updateGameScoreboard(); //causes errors
					//Load Game 0
					if(currentGame == 0 && currentGame != lastGame && gameIsSetup == false) {
						gameIsSetup = true;
						MiniGame minigame = new MiniGameFFA();
						minigame.loadMiniGame();
					}
					//Load Game 1
					if(currentGame == 1 && currentGame != lastGame && gameIsSetup == false) {
						gameIsSetup = true;
						MiniGame minigame = new MiniGameCTF();
						minigame.loadMiniGame();
					}
					//Load Game 2
					if(currentGame == 2 && currentGame != lastGame && gameIsSetup == false) {
						gameIsSetup = true;
						MiniGame minigame = new MiniGameLMS();
						minigame.loadMiniGame();
					}
					//Load Game 3
					if(currentGame == 3 && currentGame != lastGame && gameIsSetup == false) {
						gameIsSetup = true;
						MiniGame minigame = new MiniGameTDM();
						minigame.loadMiniGame();
					}
					//Start countdown.
					if (countDownOver == false) {	
						chatManager.colorCountDown(gameStartCountDownCount);
						gameStartCountDownCount--;
						if (gameStartCountDownCount <= 0) {
							countDownOver = true;
							gameStartCountDownCount = gameStartCountDown;
							
						}
					}
					//Manage Score
					if (countDownOver == true && gameIsSetup == true) {
						if(MiniGameTDM.getRedTeamKills() == 4) {
							chatManager.debugMessage("GameOver: Red team won.");
							gameActive = false;
							varReset();
						}
						if(MiniGameTDM.getBlueTeamKills() == 4) {
							chatManager.debugMessage("GameOver: Blue team won.");
							gameActive = false;
							varReset();
						}
					}
				}
				
				
				/*
				 *  Reset Variables
				 *    
				 *	If varReset == true then reset tracking variables.  
				*/
			} //END Run method.
		}, 0, ticksPerSecond); //(20 ticks = 1 second)
	} //END gameManagerTask method.
	
	//Sets players team
	private void updateTeams() {
		for(Player player : Bukkit.getOnlinePlayers()) {
			
			int redTeam = TeamManager.getRedTeamCount();
			int blueTeam = TeamManager.getBlueTeamCount();
			if (TeamManager.getPlayerTeam(player) == null) {
				if (redTeam > blueTeam) {
					TeamManager.addToTeam(player, "blue");
				} else if (blueTeam > redTeam) {
					TeamManager.addToTeam(player, "red");
				} else {
					TeamManager.addToTeam(player, "blue");
				}
			}
		}
	}
	//Setup Lobby scoreboardLobby
	public static void setupScoreboardLobby(Player player) {
		scoreboardLobby = Bukkit.getScoreboardManager().getNewScoreboard();
		
		objective = scoreboardLobby.registerNewObjective("lobby", "dummy");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "MinePile MiniGames");
	}
	/*
	 *  scoreboardLobby Setup and updates!
	 *    
	 *	This will update the scoreboardLobby for all players.  
	*/
	private void updateLobbyScoreboard() {
		for(Player players : Bukkit.getServer().getOnlinePlayers()) {
			if (players.getScoreboard().getObjective("lobby") == null) {
				//chatManager.debugMessage("scoreboardLobby null for " + players.getDisplayName());
				setupScoreboardLobby(players);
				players.setScoreboard(scoreboardLobby);
			}
			if (players.getScoreboard().getObjective("lobby") != null){
				scoreboardLobby.resetScores(players);
				scoreboardLobby.resetScores(Bukkit.getOfflinePlayer(ChatColor.GREEN + Integer.toString(lobbyCountDownCount + 2)));
				scoreboardLobby.resetScores(Bukkit.getOfflinePlayer(ChatColor.GREEN + Integer.toString(lobbyCountDownCount)));
				//chatManager.debugMessage("scoreboardLobby not(?) null for " + players.getDisplayName());
				players.setScoreboard(scoreboardLobby);//Get scoreboardLobby
				scoreboardLobby.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(ChatColor.BOLD + 
						" ")).setScore(15);
				scoreboardLobby.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(ChatColor.WHITE + "" + 
						ChatColor.BOLD + "Time Left:")).setScore(14);
				scoreboardLobby.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + 
						Integer.toString(lobbyCountDownCount + 1))).setScore(13);
				scoreboardLobby.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(ChatColor.BOLD + 
						"  ")).setScore(12);
				scoreboardLobby.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(ChatColor.WHITE + "" + 
						ChatColor.BOLD + "Min Players:")).setScore(11);
				scoreboardLobby.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + 
						Integer.toString(MPMGMain.getMinPlayers()))).setScore(10);
				scoreboardLobby.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(ChatColor.BOLD + 
						"   ")).setScore(9);
				scoreboardLobby.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(ChatColor.WHITE + "" +
						ChatColor.BOLD + "Max Players:")).setScore(8);
				scoreboardLobby.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + 
						Integer.toString(MPMGMain.getMaxPlayers()))).setScore(7);
				scoreboardLobby.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(ChatColor.BOLD + 
						"    ")).setScore(6);
			}
		}
	}
	
	public static Scoreboard getScoreboardLobby() {
		return scoreboardLobby;
	}
	
	//Setup Lobby scoreboardGame
	public static void setupGameScoreboard(Player player) {
		scoreboardGame = Bukkit.getScoreboardManager().getNewScoreboard();
		
		objective = scoreboardGame.registerNewObjective("game", "dummy");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "MinePile MiniGames");
	}
	/*
	 *  scoreboardGame Setup and updates!
	 *    
	 *	This will update the scoreboardGame for all players.  
	*/
	private void updateGameScoreboard() {
		for(Player players : Bukkit.getServer().getOnlinePlayers()) {
			if (players.getScoreboard().getObjective("Game") == null) {
				//chatManager.debugMessage("scoreboardGame null for " + players.getDisplayName());
				setupScoreboardGame(players);
				players.setScoreboard(scoreboardGame);
			}
			if (players.getScoreboard().getObjective("game") != null){
				scoreboardGame.resetScores(players);
				scoreboardGame.resetScores(Bukkit.getOfflinePlayer(ChatColor.RED + "" + ChatColor.BOLD + "Red Team"));
				scoreboardGame.resetScores(Bukkit.getOfflinePlayer(ChatColor.RED + "" + ChatColor.BOLD + "Blue Team"));
				//chatManager.debugMessage("scoreboardGame not(?) null for " + players.getDisplayName());
				players.setScoreboard(scoreboardGame);//Get scoreboardGame
				scoreboardGame.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(ChatColor.RED + "" 
				+ ChatColor.BOLD + "Red Team")).setScore(MiniGameTDM.getRedTeamKills());
				scoreboardGame.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(ChatColor.BLUE + "" 
						+ ChatColor.BOLD + "Blue Team")).setScore(MiniGameTDM.getBlueTeamKills());
			}
		}
	}
	
	private void setupScoreboardGame(Player players) {
		// TODO Auto-generated method stub
		
	}


	//Lobby CountDown count getters and setters
	public static int getLobbyCountDownCount() {
		return lobbyCountDownCount;
	}
	public static void setLobbyCountDownCount(int count) {
		lobbyCountDownCount = count; 
	}
	//Get if in game or not (for late player join spawns or death respawns.
	public static boolean isGameActive() {
		return gameActive;
	}
	public static void setGameActive(Boolean setGameActive) {
		gameActive = setGameActive;
	}
	public static boolean isCountDownOver() {
		return countDownOver;
	}

	//Reset loop tracking variables.
	public void varReset() {
		gameIsSetup = false;
		varReset = false;
		countDownOver = false;
		gameStartCountDownCount = gameStartCountDown;
		gameStatsDelayCount = gameStatsDelay;
		TeamManager.clearTeams(); //clears teams
		globalTickCount = globalTicks;
		lobbyCountDownCount = lobbyCountDown;
		
		MiniGameTDM.setBlueTeamKills(0);
		MiniGameTDM.setRedTeamKills(0);
		
		//Load next world
		WorldCreator c = new WorldCreator("lobby");
		c.createWorld();
		World world = Bukkit.getWorld("lobby");
		world.setPVP(false);
		world.setStorm(false);
		world.setMonsterSpawnLimit(0);
		world.setAnimalSpawnLimit(0);
		world.setSpawnFlags(false, false);//spawn animals, spawn monsters
		//Next world spawn coordinates
		Location teleportloc = new Location(world, 0.5, 72, 0.5);
		//Get all players online in an array, and teleport them all and play a sound
		for(Player players : Bukkit.getServer().getOnlinePlayers()) {
			players.setHealth(20);
			players.setFoodLevel(20);
			players.teleport(teleportloc); //Teleport player
			players.setPlayerTime(6000, false); //Set world time
			players.playSound(teleportloc, Sound.LEVEL_UP, 1, 7); //play a sound
		}
	}
}
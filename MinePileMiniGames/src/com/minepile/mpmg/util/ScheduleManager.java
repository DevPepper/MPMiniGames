package com.minepile.mpmg.util;

import org.bukkit.Bukkit;

import com.minepile.mpmg.MPMGMain;

public class ScheduleManager {
	
	//Load objects
	private static MPMGMain plugin = MPMGMain.plugin;
	private ChatManager chatManager = new ChatManager();
	private ConnectionManager connectionManager = new ConnectionManager();
	private GameManager gameManager;
	
	//Create Variables
	private int ticksPerSecond = 20; //(20 ticks = 1 second)
	private int taskID = -1;
	private int timeCountDownCount = 0;
	
	@SuppressWarnings("static-access")
	public ScheduleManager(MPMGMain plugin) {
		this.plugin = plugin;
	}

	//Run Game Manager Tasks
	public void gameManagerTask() {
		//Lets start a repeating task
		taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				//Show player count
				int playerCount = connectionManager.getPlayerCount();
				
				if (playerCount >= MPMGMain.getMinPlayers()) {
					//trigger countdown
					gameManager.startLobbyCountDown(); //This causes bugs
				}
			}
		}, 0, ticksPerSecond); //(20 ticks = 1 second)
	}
	
	//Count down
	public void startCountDown(int time) {
		setTimeCount(time);
		//Lets start a repeating task
		taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				chatManager.colorCountDown(timeCountDownCount);
				timeCountDownCount--;
				if (timeCountDownCount <= 0) {
					
					//Cancel this Repeating Task.
					Bukkit.getScheduler().cancelTask(taskID);
				}
			}
		}, 0, ticksPerSecond); //(20 ticks = 1 second)
	}
	
	public void setTimeCount(int timeCount) {
		this.timeCountDownCount = timeCount;
	}

}

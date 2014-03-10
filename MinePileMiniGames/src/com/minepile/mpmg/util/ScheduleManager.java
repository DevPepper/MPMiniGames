package com.minepile.mpmg.util;

import org.bukkit.Bukkit;

import com.minepile.mpmg.MPMGMain;

public class ScheduleManager {
	
	//Load objects
	private static MPMGMain plugin = MPMGMain.plugin;
	private ChatManager chatManager = new ChatManager();
	
	//Create Variables
	private int ticksPerSecond = 20;
	private int taskID = -1;
	private int timeCount = 0;
	
	public ScheduleManager(MPMGMain plugin) {
		this.plugin = plugin;
	}
	
	//Count down
	public void startCountDown(int time) {
		setTimeCount(time);
		//Lets start a repeating task
		taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				chatManager.colorCountDown(timeCount);
				timeCount--;
				if (timeCount <= 0) {
					
					//Cancel this Repeating Task.
					Bukkit.getScheduler().cancelTask(taskID);
				}
			}
		}, 0, ticksPerSecond); //(20 ticks = 1 second)
	}

	public void setTimeCount(int timeCount) {
		this.timeCount = timeCount;
	}

}

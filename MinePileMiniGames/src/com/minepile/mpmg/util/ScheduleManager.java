package com.minepile.mpmg.util;

import org.bukkit.Bukkit;

import com.minepile.mpmg.MPMGMain;

public class ScheduleManager {
	
	//Load objects
	private static MPMGMain plugin = MPMGMain.plugin;
	private ConnectionManager connectionManager = new ConnectionManager();
	
	//Countdown
	public void repeatingTask() {		
		//Lets start a repeating task
		Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
			@Override
			public void run() {
				
				//Task to do!
				
			}
			
		}, 0, 20); //(20 ticks = 1 second)
	}
}

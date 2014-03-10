package com.minepile.mpmg.util;

import org.bukkit.Bukkit;

import com.minepile.mpmg.MPMGMain;

public class CountDownManager {
	
	//Load objects
	private static MPMGMain plugin = MPMGMain.plugin;
	private ChatManager chatManager = new ChatManager();
	
	//Create Variables
	private int timeCount = 60 ; //(20 ticks = 1 second)
	
	public CountDownManager(MPMGMain plugin) {
		this.plugin = plugin;
	}
	
	//Countdown
	public void startCountDown() {
		//If timeCount is not 60 when this method is called,
		//lets reset it, so it is. This is a 60 second countdown.
		if(timeCount != 60) {
			timeCount = 60;
		}
		
		//Lets start a repeating task
		Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
			@Override
			public void run() {
				chatManager.colorCountDown(timeCount);
				timeCount--;
				if (timeCount < 0) {
					timeCount = 60;
					
				}
			}
			
		}, 0, 20); //(20 ticks = 1 second)
	}

	//Reset the timeCount variable
	public void resetTimeCount() {
		this.timeCount = 60;
	}

}

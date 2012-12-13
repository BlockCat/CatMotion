package me.blockcat.catmotion;

import org.bukkit.Bukkit;

public class Ticker implements Runnable{

	public void run() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(CatMotion.plugin, new Runnable() {

			public void run() {
				//System.out.println("Ticker tick");
				CatMotion.plugin.getAnimationCollection().tick();
			}			
		}, 0L, 1L);
	}

}

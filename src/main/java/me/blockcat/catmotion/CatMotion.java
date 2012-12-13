package me.blockcat.catmotion;

import me.blockcat.catmotion.collections.AnimationCollection;
import me.blockcat.catmotion.commands.CCommandHandler;
import me.blockcat.catmotion.listener.PlayerListener;

import org.bukkit.plugin.java.JavaPlugin;

public class CatMotion extends JavaPlugin {
	
	public static CatMotion plugin;
	private Thread ticker;
	private AnimationCollection animations;
	
	public void onEnable() 
	{
		plugin = this;
		//TODO initiate things.
		/** Initiate Animation collection*/
		animations = new AnimationCollection(this);
		animations.loadAnimations();
		
		/** Initiate ticker. */
		ticker = new Thread(new Ticker());
		ticker.start();
		
		/** Initiate listeners. */
		this.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
		
		/** Initiate commands. */
		CCommandHandler vch = new CCommandHandler();
		this.getCommand("cm").setExecutor(vch);
		this.getCommand("cmotion").setExecutor(vch);		
	}
	
	public void onDisable() 
	{
		/** Save animations */
		animations.saveAnimations();
	}
	
	public AnimationCollection getAnimationCollection() {
		return animations;
	}

}

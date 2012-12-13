package me.blockcat.catmotion.collections;

import java.util.HashMap;

import me.blockcat.catmotion.CatMotion;
import me.blockcat.catmotion.collections.animation.Animation;

import org.bukkit.Location;
import org.bukkit.block.Block;

public class PlayerCollection {
	
	private static HashMap<String, PlayerData> playerData = new HashMap<String, PlayerData>();
	
	private static PlayerData getPlayerData(String name) {
		if (playerData.containsKey(name)) {
			return playerData.get(name);
		} else {
			PlayerData data = new PlayerData();
			playerData.put(name, data);
			return data;
		}
	}
	
	/** Gets the selection type for player. */
	public static int getSelection(String name) {
		return getPlayerData(name).selection;
	}
	
	/** Sets the selection type for player. */
	public static void setSelection(String name, int type) {
		getPlayerData(name).selection = type;
	}
	
	/** Gets the first block. */
	public static Block getBlock1(String name) {
		return getPlayerData(name).block1;
	}
	
	/** Sets the first block. */
	public static void setBlock1(String name, Block block) {
		getPlayerData(name).block1 = block;
	}
	
	/** Gets the second block. */
	public static Block getBlock2(String name) {
		return getPlayerData(name).block2;
	}
	
	/** Sets the second block. */
	public static void setBlock2(String name, Block block) {
		getPlayerData(name).block2 = block;
	}
	
	public static Animation getAnimation(String name) {
		return CatMotion.plugin.getAnimationCollection().getAnimation(getPlayerData(name).selected);
	}
	
	public static void setAnimation(String name, Animation animation) {
		getPlayerData(name).selected = animation.getLocation();
	}
	
	protected static class PlayerData {
		public int selection = 0;
		public Block block1 = null;
		public Block block2 = null;
		public Location selected = null;
	}

}

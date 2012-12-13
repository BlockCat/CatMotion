package me.blockcat.catmotion.collections;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import me.blockcat.catmotion.CatMotion;
import me.blockcat.catmotion.collections.animation.Animation;

import org.bukkit.Location;

public class AnimationCollection {

	private List<Animation> animations = new ArrayList<Animation>();
	private List<Animation> remove = new ArrayList<Animation>();
	private CatMotion plugin;

	public AnimationCollection(CatMotion plugin) {
		this.plugin = plugin;
	}

	public Animation newAnimation(Location location) {
		Animation animation = new Animation(location);
		animations.add(animation);
		return animation;
	}

	public void removeAnimation(Animation animation) {
		for (File f : animation.file.listFiles()) {
			f.delete();
		}
		animation.file.delete();
		remove.add(animation);
	}

	public List<Animation> getAnimations() {
		return animations;
	}

	public Animation getAnimation(Location location) {
		for (Animation animation : animations) {
			if (animation.getLocation().equals(location)) {
				return animation;
			}
		}
		return null;
	}

	public boolean loadAnimations() {
		File file = new File(plugin.getDataFolder(), "CommandBlocks.vmc");

		if (!file.exists()) return false;		

		try {
			DataInputStream in = new DataInputStream(new FileInputStream(file));

			try {
				while(true) {
				animations.add(new Animation(in));
				}
			} catch (EOFException exc) {
				in.close();
			}

		} catch (Exception e) {
			return false;
		}	
		return true;
	}

	/** Save animations. */
	public boolean saveAnimations() {
		File file = new File(plugin.getDataFolder(), "CommandBlocks.vmc");

		if (!file.exists()) {
			file.getParentFile().mkdirs();
			try {
				file.createNewFile();
			} catch (Exception e) {
			}
		}

		try {
			DataOutputStream out = new DataOutputStream(new FileOutputStream(file));

			for (Animation animation : animations) {
				try {
					animation.saveAnimation(out);
				} catch(Exception e) {
					continue;
				}
			}
			out.close();

		} catch (Exception e) {
			return false;
		}
		return true;
	}


	public void tick() {
		animations.removeAll(remove);
		remove.clear();
		for (Animation animation : animations) {
			animation.tick();
		}
	}



}

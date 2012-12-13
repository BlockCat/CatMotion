package me.blockcat.catmotion.collections.animation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.blockcat.catmotion.collections.Frame;

import org.bukkit.World;

public class AnimationData {

	public List<Frame> frames = new ArrayList<Frame>();
	private World world;
	private int count = 0;
	private Animation animation;

	public AnimationData(Animation animation, World world) {
		this.animation = animation;
		this.world = world;
	}

	public void loadFrames(File file) {
		for (File frame : file.listFiles()) {
			try {
				int index = Integer.parseInt(frame.getName().replace(".frame",""));
				frames.add(index, new Frame(frame));
			} catch (Exception e) {
				continue;
			}
		}
	}

	public void saveFrames(File dir) {
		for (int i = 0; i < frames.size(); i++) {
			saveFrame(dir, i);
		}
	}

	private void saveFrame(File dir, int i) {
		File file = new File(dir, i + ".frame");
		if (frames.get(i) != null) {
			frames.get(i).saveFrame(file);
		}
	}

	public int addFrame(int index, String playerName) {
		Frame frame = new Frame(world, playerName);
		frames.add(index, frame);
		return index;
	}

	public int addFrame(String name) {
		frames.add(new Frame(world, name));
		return frames.size();
	}

	public boolean place(int frame, int delay) {
		if (count >= delay) {
			count = 0;
			frames.get(frame).place(world);
			return true;
		} else {
			count++;
			return false;
		}

	}

}

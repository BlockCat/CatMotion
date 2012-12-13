package me.blockcat.catmotion.collections.animation;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

import me.blockcat.catmotion.CatMotion;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

public class Animation {

	public File file;
	public int frame = 0;
	private int delay = 5;
	private Block motionBlock;
	private Location location;
	private Chunk chunk;
	private AnimationData data;

	public Animation(DataInputStream in) throws IOException  {
		loadAnimation(in);
	}

	public Animation(Location location) {
		this.location = location;
		this.motionBlock = location.getBlock();
		this.chunk = location.getChunk();
		this.data = new AnimationData(this, location.getWorld());
		this.file = new File(CatMotion.plugin.getDataFolder(), location.getWorld().getName() + "/"  + location.getBlockX() + "-" + location.getBlockY() + "-" + location.getBlockZ() + "/");
	}

	public void tick() {
		if (!motionBlock.isBlockPowered()) return;
		
		if (!data.place(frame, delay)) {
			return;
		}
		
		if (motionBlock.getTypeId() == Material.STONE.getId()) {			
			this.next(true);
		} else if (motionBlock.getTypeId() == Material.COBBLESTONE.getId()) {
			this.next(false);
		} else if (motionBlock.getTypeId() == Material.SANDSTONE.getId()) {
			this.previous(true);
		} else if (motionBlock.getTypeId() == Material.SAND.getId()) {
			this.previous(false);
		}
		
		
	}
	
	public void next (boolean loop) {
		frame++;
		if (frame >= data.frames.size()) {
			if (loop) {
				frame = 0;
			} else {
				frame = data.frames.size() - 1;
			}
		}
	}
	
	public void previous(boolean loop) {
		frame--;
		if (frame < 0) {
			if (loop) {
				frame = data.frames.size() - 1;
			} else {
				frame = 0;
			}
		}
	}
	
	public int setFrame(int frame) {
		frame = Math.abs(frame);
		if (frame >= data.frames.size())
		{
			frame = data.frames.size() - 1;
		}
		this.frame = frame;
		data.place(frame, 0);
		return frame;
		
	}

	public void insertFrame(int index, String playerName) {
		frame = data.addFrame(index, playerName);
	}
	public void addFrame(String name) {
		frame = data.addFrame(name);		
	}

	/** Load animation. */
	private void loadAnimation(DataInputStream in) throws IOException {
		int delay = in.readInt();
		int frame = in.readInt();
		int x = in.readInt();
		int y = in.readInt();
		int z = in.readInt();
		String w = in.readUTF();
		World world = Bukkit.getWorld(w);
		if (world != null) {
			this.data = new AnimationData(this, world);
			this.delay = delay;
			this.frame = frame;
			location = new Location(world, x, y, z);	
			motionBlock = location.getBlock();
			chunk = location.getChunk();
			file = new File(CatMotion.plugin.getDataFolder(), w + "/" + x + "-" + y + "-" + z);
			if (!file.exists()) {
				try {
					file.mkdirs();
				} catch(Exception e) {

				}
			} else {
				loadFrames();
			}
		}
	}
	
	private void loadFrames() {
		data.loadFrames(file);
	}

	/** Save animation. */
	public void saveAnimation(DataOutputStream out) throws IOException {
		out.writeInt(delay);
		out.writeInt(frame);
		out.writeInt(location.getBlockX());
		out.writeInt(location.getBlockY());
		out.writeInt(location.getBlockZ());
		out.writeUTF(location.getWorld().getName());
		System.out.println("Saved animation!");
		try {
			File dir = new File(CatMotion.plugin.getDataFolder(), location.getWorld().getName() + "/"  + location.getBlockX() + "-" + location.getBlockY() + "-" + location.getBlockZ() + "/");
			if (!dir.exists()) {
				dir.mkdirs();
			}
			saveFrames();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void saveFrames() {
		data.saveFrames(file);
	}
	
	public int getDelay() {
		return delay;
	}
	
	public void setDelay(int delay) {
		this.delay = delay;
	}
	

	public Location getLocation() {
		return location;
	}

	



}

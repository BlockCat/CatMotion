package me.blockcat.catmotion.collections.pixel;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

public class PixelBlock {
	
	protected int id;
	protected byte d;
	protected Vector location;

	public PixelBlock(Block block) {
		this.id = block.getTypeId();
		location = block.getLocation().toVector();
	}

	public PixelBlock(int id, Vector location, DataInputStream in) throws IOException {
		this.id = id;
		this.d = in.readByte();
		this.location = location;
	}

	public void saveData(DataOutputStream out) throws IOException {
		out.writeInt(id);
		out.writeByte(d);
	}

	public void place(World world) {
		world.getBlockAt(location.toLocation(world)).setTypeIdAndData(id, d, true);
	}
}

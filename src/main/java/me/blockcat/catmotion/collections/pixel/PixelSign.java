package me.blockcat.catmotion.collections.pixel;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.util.Vector;

public class PixelSign extends PixelBlock{
	
	String[] lines = new String[4];

	public PixelSign(Block block) {
		super(block);
		lines = ((Sign) block.getState()).getLines();
	}

	public PixelSign(int id, Vector location, DataInputStream in) throws IOException {
		super(id, location, in);	
		for (int i = 0; i < 4; i++) {
			lines[i] = in.readUTF();
		}
	}
	
	@Override
	public void saveData(DataOutputStream out) throws IOException {
		super.saveData(out);
		for (int i = 0; i < 4; i++) {
			out.writeUTF(lines[i] != null ? lines[i] : "");
		}
	}
	
	@Override
	public void place(World world) {
		Block block = world.getBlockAt(location.toLocation(world));
		block.setTypeIdAndData(id, d, true);
		Sign sign = (Sign) block.getState();
		for (int i = 0; i < 4; i++) {
			sign.setLine(i, lines[i]);					
		}
		sign.update(true);
	}
}

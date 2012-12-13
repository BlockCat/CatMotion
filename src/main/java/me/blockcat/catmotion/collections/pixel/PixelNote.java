package me.blockcat.catmotion.collections.pixel;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.NoteBlock;
import org.bukkit.util.Vector;

public class PixelNote extends PixelBlock{
	
	private byte note;

	public PixelNote(Block block) {
		super(block);
		note = ((NoteBlock)block.getState()).getRawNote();
	}

	public PixelNote(int id, Vector location, DataInputStream in) throws IOException {
		super(id, location, in);
		note = in.readByte();
	}

	@Override
	public void saveData(DataOutputStream out) throws IOException {
		super.saveData(out);
		out.writeByte(note);
	}
	
	@Override
	public void place(World world) {
		Block block = world.getBlockAt(location.toLocation(world));
		block.setTypeIdAndData(id, d, true);
		NoteBlock noteBlock = (NoteBlock) block.getState();
		noteBlock.setRawNote(note);
		noteBlock.update();
	}
}

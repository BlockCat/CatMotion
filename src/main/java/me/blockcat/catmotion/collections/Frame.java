package me.blockcat.catmotion.collections;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import me.blockcat.catmotion.collections.pixel.PixelBlock;
import me.blockcat.catmotion.collections.pixel.PixelNote;
import me.blockcat.catmotion.collections.pixel.PixelSign;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

public class Frame {

	private List<PixelBlock	> blocks = new ArrayList<PixelBlock>();
	private Vector dimension;
	private Vector startBlock;

	public Frame(File frame) {
		loadFrame(frame);
	}

	public Frame(World world, String playerName) {
		Vector v1 = PlayerCollection.getBlock1(playerName).getLocation().toVector();
		Vector v2 = PlayerCollection.getBlock2(playerName).getLocation().toVector();

		Vector lowV = Vector.getMinimum(v1, v2);
		Vector highV = Vector.getMaximum(v1, v2);

		startBlock = lowV;
		dimension = highV.clone().subtract(lowV).add(new Vector(1,1,1));

		for (int x = lowV.getBlockX(); x <= highV.getBlockX(); x++) {
			for (int y = lowV.getBlockY(); y <= highV.getBlockY(); y++) {
				for (int z = lowV.getBlockZ(); z <= highV.getBlockZ(); z++) {
					Block block = world.getBlockAt(x, y, z);

					if (block.getType() == Material.SIGN || block.getType() == Material.SIGN_POST) {
						blocks.add(new PixelSign(block));
					} else if (block.getType() == Material.NOTE_BLOCK) {
						blocks.add(new PixelNote(block));
					} else {
						blocks.add(new PixelBlock(block));
					}
				}
			}
		}
	}

	private void loadFrame(File frame) {
		try {
			GZIPInputStream zin = new GZIPInputStream(new FileInputStream(frame));
			DataInputStream in = new DataInputStream(zin);
			dimension = new Vector(in.readInt(), in.readInt(), in.readInt());
			startBlock = new Vector(in.readInt(), in.readInt(), in.readInt());
			for (int x = 0; x < dimension.getX(); x++) {
				for (int y = 0; y < dimension.getY(); y++) {
					for (int z = 0; z < dimension.getZ(); z++) {
						Vector v = new Vector(x + startBlock.getX(), y + startBlock.getY(), z + startBlock.getZ());
						int id = in.readInt();
						if (id == Material.SIGN.getId() || id == Material.SIGN_POST.getId()) {
							blocks.add(new PixelSign(id, v, in));
						} else if (id == Material.NOTE_BLOCK.getId()) {
							blocks.add(new PixelNote(id, v, in));
						} else {
							blocks.add(new PixelBlock(id, v, in));
						}
					}
				}
			}
			//int size = in.readInt();
			/*for (int i = 0; i < size; i++) {
					int tag = in.readInt();
					switch(tag) {
					case 0:
						blocks.add(new ClipBlockAir(in));
						break;
					case 1:
						blocks.add(new ClipBlock(in));
						break;
					case 2:
						blocks.add(new ClipBlockSign(in));
						break;
					case 3:		
						blocks.add(new ClipBlockNote(in));
						break;
					}
				}*/

			zin.close();
			in.close();
		} catch(Exception e) {
			return;
		}		
	}

	public void saveFrame(File frame) {
		try {
			GZIPOutputStream zout = new GZIPOutputStream(new FileOutputStream(frame));
			DataOutputStream out = new DataOutputStream(zout);
			/** Save dimensions */
			out.writeInt((int) dimension.getX());
			out.writeInt((int) dimension.getY());
			out.writeInt((int) dimension.getZ());
			/** Save start block */
			out.writeInt((int) startBlock.getX());
			out.writeInt((int) startBlock.getY());
			out.writeInt((int) startBlock.getZ());

			for (int x = 0; x < blocks.size(); x++) {
				blocks.get(x).saveData(out);
			}

			zout.close();
			out.close();
		} catch(Exception e) {
		}
	}

	public void place(World world) {
		for(PixelBlock pixelBlock : blocks) {
			pixelBlock.place(world);
		}		
	}

}

package com.gmail.teampagnus.exposed_ores;

import java.util.ArrayList;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;

class DesiredBlock {
	public Block block;
	public Boolean isDesired;
	
	public DesiredBlock(Block block, Boolean isDesired) {
		this.block = block;
		this.isDesired = isDesired;
	}
}

public class OreRemover {
	public static void getOresInChunk(ArrayList<DesiredBlock> desiredBlockList, Chunk chunk, ArrayList<Material> materialList) {
		int maxHeight = chunk.getWorld().getMaxHeight();
		int minHeight = chunk.getWorld().getMinHeight();
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = minHeight; y < maxHeight; y++) {
					Block block = chunk.getBlock(x, y, z);
					if (materialList.contains(block.getType()));
						desiredBlockList.add(new DesiredBlock(block, false));
				}
			}
		}
	}

	public static void replace(Chunk chunk) {
		for (int y = 1; y * 16 < 256; y++) {
			chunk.getBlock(0, y * 16, 0).setType(Material.OBSIDIAN, false);
		}
	}
}
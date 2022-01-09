package com.gmail.teampagnus.exposed_ores;

import java.util.ArrayList;

import org.bukkit.Chunk;
import org.bukkit.Location;
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
	private void getOresInChunk(ArrayList<DesiredBlock> desiredBlockList, Chunk chunk,
			ArrayList<Material> materialList) {
		int maxHeight = chunk.getWorld().getMaxHeight();
		int minHeight = chunk.getWorld().getMinHeight();
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = minHeight; y < maxHeight; y++) {
					Block block = chunk.getBlock(x, y, z);
					if (materialList.contains(block.getType()))
						;
					desiredBlockList.add(new DesiredBlock(block, false));
				}
			}
		}
	}

	private Boolean oreHasExposedNeighbor(DesiredBlock block, Chunk chunk, ArrayList<DesiredBlock> desiredBlockList,
			ArrayList<Material> materialList) {
		int a[] = new int[2];
		a[0] = -1;
		a[1] = 1;
		for (int side : a) {
			Location location = block.block.getLocation();
			Block block2 = chunk.getBlock((int) (location.getX() + side), (int) (location.getY() + side),
					(int) (location.getZ() + side));
			if (block2.getType().equals(Material.AIR))
				return true;
			if (materialList.contains(block2.getType())) {
				for (DesiredBlock ds : desiredBlockList) {
					if (ds.block.equals(chunk.getBlock((int) (location.getX() + side), (int) (location.getY() + side),
							(int) (location.getZ() + side))) && ds.isDesired)
						return true;
				}
			}
		}
		return false;
	}

	public static void replace(Chunk chunk) {
		for (int y = 1; y * 16 < 256; y++) {
			chunk.getBlock(0, y * 16, 0).setType(Material.OBSIDIAN, false);
		}
	}
}
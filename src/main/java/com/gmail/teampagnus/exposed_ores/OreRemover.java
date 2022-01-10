package com.gmail.teampagnus.exposed_ores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

public class OreRemover {
	private static ArrayList<Material> materialList = new ArrayList<Material>(
		Arrays.asList(
			Material.COAL_ORE,
			Material.COPPER_ORE,
			Material.LAPIS_ORE,
			Material.IRON_ORE,
			Material.REDSTONE_ORE,
			Material.DIAMOND_ORE,
			Material.GOLD_ORE,
			Material.EMERALD_ORE,
			Material.DEEPSLATE_COAL_ORE,
			Material.DEEPSLATE_COPPER_ORE,
			Material.DEEPSLATE_LAPIS_ORE,
			Material.DEEPSLATE_IRON_ORE,
			Material.DEEPSLATE_REDSTONE_ORE,
			Material.DEEPSLATE_DIAMOND_ORE,
			Material.DEEPSLATE_GOLD_ORE,
			Material.DEEPSLATE_EMERALD_ORE
		)
	);
	
	private static ArrayList<Material> validatorList = new ArrayList<Material>(
		Arrays.asList(
			Material.AIR,
			Material.WATER
		)
	);

	private static void validate(int coords[], Chunk chunk, ArrayList<Block> validBlockList,
			ArrayList<Material> materialList, int minHeight, int maxHeight) {
		if (coords[0] < 16 - 1) {
			int neighborCoords[] = coords.clone();
			neighborCoords[0]++;
			Block neighborBlock = chunk.getBlock(neighborCoords[0], neighborCoords[1], neighborCoords[2]);
			if (!validBlockList.contains(neighborBlock)) {
				if (materialList.contains(neighborBlock.getType())) {
					validBlockList.add(neighborBlock);
					validate(neighborCoords, chunk, validBlockList, materialList, minHeight, maxHeight);
				}
			}
		}
		if (coords[0] > 0) {
			int neighborCoords[] = coords.clone();
			neighborCoords[0]--;
			Block neighborBlock = chunk.getBlock(neighborCoords[0], neighborCoords[1], neighborCoords[2]);
			if (!validBlockList.contains(neighborBlock)) {
				if (materialList.contains(neighborBlock.getType())) {
					validBlockList.add(neighborBlock);
					validate(neighborCoords, chunk, validBlockList, materialList, minHeight, maxHeight);
				}
			}
		}
		if (coords[1] < maxHeight - 1) {
			int neighborCoords[] = coords.clone();
			neighborCoords[1]++;
			Block neighborBlock = chunk.getBlock(neighborCoords[0], neighborCoords[1], neighborCoords[2]);
			if (!validBlockList.contains(neighborBlock)) {
				if (materialList.contains(neighborBlock.getType())) {
					validBlockList.add(neighborBlock);
					validate(neighborCoords, chunk, validBlockList, materialList, minHeight, maxHeight);
				}
			}
		}
		if (coords[1] > minHeight) {
			int neighborCoords[] = coords.clone();
			neighborCoords[1]--;
			Block neighborBlock = chunk.getBlock(neighborCoords[0], neighborCoords[1], neighborCoords[2]);
			if (!validBlockList.contains(neighborBlock)) {
				if (materialList.contains(neighborBlock.getType())) {
					validBlockList.add(neighborBlock);
					validate(neighborCoords, chunk, validBlockList, materialList, minHeight, maxHeight);
				}
			}
		}
		if (coords[2] < 16 - 1) {
			int neighborCoords[] = coords.clone();
			neighborCoords[2]++;
			Block neighborBlock = chunk.getBlock(neighborCoords[0], neighborCoords[1], neighborCoords[2]);
			if (!validBlockList.contains(neighborBlock)) {
				if (materialList.contains(neighborBlock.getType())) {
					validBlockList.add(neighborBlock);
					validate(neighborCoords, chunk, validBlockList, materialList, minHeight, maxHeight);
				}
			}
		}
		if (coords[2] > 0) {
			int neighborCoords[] = coords.clone();
			neighborCoords[2]--;
			Block neighborBlock = chunk.getBlock(neighborCoords[0], neighborCoords[1], neighborCoords[2]);
			if (!validBlockList.contains(neighborBlock)) {
				if (materialList.contains(neighborBlock.getType())) {
					validBlockList.add(neighborBlock);
					validate(neighborCoords, chunk, validBlockList, materialList, minHeight, maxHeight);
				}
			}
		}
	}

	public static void main(Chunk chunk) {
		int maxHeight = chunk.getWorld().getMaxHeight();
		int minHeight = chunk.getWorld().getMinHeight();
		ArrayList<Block> blockList = new ArrayList<Block>();
		ArrayList<Block> validBlockList = new ArrayList<Block>();
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = minHeight; y < maxHeight; y++) {
					Block block = chunk.getBlock(x, y, z);
					if (materialList.contains(block.getType())) {
						blockList.add(block);
					}
					else if (validatorList.contains(block.getType())) {
						int coords[] = new int[3];
						coords[0] = x;
						coords[1] = y;
						coords[2] = z;
						OreRemover.validate(coords, chunk, validBlockList, materialList, minHeight, maxHeight);
					}
				}
			}
		}
		int blocksChanged = 0;
		for (Block block : blockList) {
			if (!validBlockList.contains(block)) {
				block.setType(Material.DIAMOND_BLOCK, false);
				blocksChanged++;
			}
		}
		Bukkit.broadcastMessage("" + blocksChanged + " blocks replaced.");
	}
}

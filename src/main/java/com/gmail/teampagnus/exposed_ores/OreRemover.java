package com.gmail.teampagnus.exposed_ores;

import java.util.ArrayList;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;

class ValidOre {
	static Block block;
	static Boolean isValid;
}

public class OreRemover {
	public static ArrayList<ValidOre> getOresInChunk(Chunk chunk) {
		return new ArrayList<ValidOre>();
	}
	
	public static void replace(Chunk chunk) {
		for (int y = 1; y * 16 < 256; y++) {
			chunk.getBlock(0, y * 16, 0).setType(Material.OBSIDIAN, false);
		}
	}
}

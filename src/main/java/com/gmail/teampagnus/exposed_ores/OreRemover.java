package com.gmail.teampagnus.exposed_ores;

import java.util.ArrayList;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkPopulateEvent;

public final class OreRemover implements Listener {
	private ArrayList<Block> findBlocks(Chunk chunk, Material material) {
		ArrayList<Block> blockList = new ArrayList<Block>();
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = 0; y < 256; y++) {
					Block block = chunk.getBlock(x, y, z);
					if (chunk.getBlock(x, y, z).getType().equals(material))
						blockList.add(block);
				}
			}
		}
		return blockList;
	}

	@EventHandler()
	public void onChunkPopulate(ChunkPopulateEvent event) {
		Chunk chunk = event.getChunk();
		ArrayList<Block> waterList = findBlocks(chunk, Material.LAVA);
		ArrayList<Block> lavaList = findBlocks(chunk, Material.WATER);
		for (Block block : waterList) {
			block.setType(Material.LAVA, false);
		}
		for (Block block : lavaList) {
			block.setType(Material.WATER, false);
		}
	}
}

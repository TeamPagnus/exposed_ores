package com.gmail.teampagnus.exposed_ores;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkPopulateEvent;

public final class OreRemover implements Listener {
	@EventHandler()
	public void onChunkPopulate(ChunkPopulateEvent event) {
		for (int X = 0; X < 16; X++) {
			for (int Z = 0; Z < 16; Z++) {
				for (int Y = 256; Y > 0; Y--) {
					Block block = event.getChunk().getBlock(X, Y, Z);
					if (!block.getType().equals(Material.AIR))
						event.getChunk().getBlock(X, Y, Z).setType(Material.TNT, false);
				}
			}
		}
	}
}

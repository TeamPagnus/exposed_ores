package com.gmail.teampagnus.exposed_ores;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkPopulateEvent;

public class ChunkPopulateListener implements Listener {
	@EventHandler()
	public void onChunkPopulate(ChunkPopulateEvent event) {
		OreRemover.replace(event.getChunk());
	}
}

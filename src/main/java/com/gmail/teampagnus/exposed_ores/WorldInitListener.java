package com.gmail.teampagnus.exposed_ores;

import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;

public class WorldInitListener implements Listener {
	@EventHandler()
	public void onWorldInit(WorldInitEvent event) {
		World world = event.getWorld();
		world.getPopulators().add(new CustomPopulator());
		Chunk[] firstChunks = world.getLoadedChunks();
		for (Chunk chunk : firstChunks) {
			OreRemover.main(chunk);
		}
	}
}

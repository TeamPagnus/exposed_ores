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

		// this is best that any precompilator flag:
		// world.getPopulators().add(new CustomPopulator());
		world.getPopulators().add(new CustomMoonPopulator());

		Chunk[] firstChunks = world.getLoadedChunks();
		for (Chunk chunk : firstChunks) {
			OreRemover.main(chunk);
		}
	}
}

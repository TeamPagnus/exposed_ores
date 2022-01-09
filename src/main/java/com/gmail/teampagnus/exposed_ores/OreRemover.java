package com.gmail.teampagnus.exposed_ores;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkPopulateEvent;

public final class OreRemover implements Listener {
    @EventHandler()
    public void onChunkPopulate(ChunkPopulateEvent event) {
    	event.getChunk().getBlock(0, 64, 0).setType(Material.DIAMOND_BLOCK, false);
    }
}

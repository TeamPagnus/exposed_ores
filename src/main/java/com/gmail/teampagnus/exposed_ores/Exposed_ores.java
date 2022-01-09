package com.gmail.teampagnus.exposed_ores;

import org.bukkit.plugin.java.JavaPlugin;

public final class Exposed_ores extends JavaPlugin {
	@Override
	public void onEnable() {
        getServer().getPluginManager().registerEvents(new ChunkPopulateListener(), this);
	}
}

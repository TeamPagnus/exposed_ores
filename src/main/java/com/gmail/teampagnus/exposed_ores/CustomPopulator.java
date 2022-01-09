package com.gmail.teampagnus.exposed_ores;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

public class CustomPopulator extends BlockPopulator {
	@Override
	public void populate(World world, Random random, Chunk chunk) {
		OreRemover.main(chunk);
	}
}

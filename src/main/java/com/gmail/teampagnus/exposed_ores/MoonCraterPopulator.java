package com.gmail.teampagnus.exposed_ores;

import java.util.Random;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

public class MoonCraterPopulator extends BlockPopulator {
    public void populate(World world, Random random, Chunk source) {
        OreRemover.mainOnlyOres(world, random, source);
    }
}

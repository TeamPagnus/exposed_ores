package com.gmail.teampagnus.exposed_ores;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.bukkit.Location;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.util.noise.NoiseGenerator;
import org.bukkit.util.noise.SimplexNoiseGenerator;

public class MoonChunkGenerator extends ChunkGenerator {

    @Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
        world.getPopulators().add((BlockPopulator)new MoonCraterPopulator());
        return world.getPopulators();
    }
}

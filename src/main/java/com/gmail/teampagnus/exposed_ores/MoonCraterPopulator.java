package com.gmail.teampagnus.exposed_ores;

import java.util.Random;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

public class MoonCraterPopulator extends BlockPopulator {
    public void populate(World world, Random random, Chunk source) {
        for (int x = 0; x < 16; x++)
        {
            for (int y = 0; y < 200; y++)
            {
                for (int z = 0; z < 16; z++)
                {
                    Block b = source.getBlock(x, y, z);
                    if (b.getType()  != Material.AIR)
                    {
                        b.setType(Material.TNT, false);
                    }
                }
            }
        }
    }
}

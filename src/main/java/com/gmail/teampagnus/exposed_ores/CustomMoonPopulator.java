package com.gmail.teampagnus.exposed_ores;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

public class CustomMoonPopulator extends BlockPopulator {
    private int maxHeight = 0;
    private int minHeight = 0;

    private int getIndx(int x, int y, int z) {
        x = fixedMod(x);
        z = fixedMod(z);
        // y | x | z
        return ((y + 64) << 8) + (x << 4) + z;
    }

    private int fixedMod(int arg) {
        int result = arg % 16;
        if (result < 0) {
            return result + 16;
        } else {
            return result;
        }
    }

    private Boolean hasExposedNeightbor(int xn, int yn, int zn, int x, int y, int z, Chunk chunk,Boolean[] materials) {
        // that means that the neightbor it's an exposed ore
        if (materials[getIndx(xn, yn, zn)] != null) {
            // if the neightbor it's exposed, the actual should be marked exposed too
            materials[getIndx(x, y, z)] = true;
            return true;
            // that means that the neightbor it's an air block
        } else if (chunk.getBlock(fixedMod(xn), yn, fixedMod(zn)).getType() == Material.AIR) {
            // so the actual should be exposed
            materials[getIndx(x, y, z)] = true;
            return true;
        }

        return false;
    }

    public void populate(World world, Random random, Chunk source) {
        if (world.getEnvironment() != Environment.NORMAL) {
            return;
        }
        ArrayList<Block> ores = new ArrayList<Block>();
        maxHeight = source.getWorld().getMaxHeight();
        minHeight = source.getWorld().getMinHeight();

        for (int x = 0; x < 16; x++)
            for (int z = 0; z < 16; z++)
                for (int y = minHeight; y < maxHeight; y++) {
                    Block b = source.getBlock(x, y, z);
                    if (b.getType().name().endsWith("_ORE")) {
                        ores.add(b);
                    }
                    if (fixedMod(b.getX()) != x) {
                        Bukkit.getLogger().info("esto no deberia pasar " + fixedMod(b.getX()) + " " + x);
                    }
                    if (fixedMod(b.getZ()) != z) {
                        Bukkit.getLogger().info("esto no deberia pasar " + fixedMod(b.getZ()) + " " + z);
                    }
                }

        Boolean[] materials = new Boolean[98304];

        for (int repetitions = 0; repetitions < 1000; repetitions++)
        {
            Boolean hasOneNeightbor = false;
            for (int i = 0; i < ores.size(); i++)
            {
                int x = ores.get(i).getX();
                int y = ores.get(i).getY();
                int z = ores.get(i).getZ();
                
                if (materials[getIndx(x, y, z)] != null){
                    continue;
                }

                if (fixedMod(x) < 15)
                hasOneNeightbor = hasOneNeightbor || hasExposedNeightbor(x + 1, y, z, x, y,z, source, materials);

                if (fixedMod(x) > 0)
                hasOneNeightbor = hasOneNeightbor || hasExposedNeightbor(x - 1, y, z, x, y,z, source, materials);

                if (fixedMod(z) < 15)
                hasOneNeightbor = hasOneNeightbor || hasExposedNeightbor(x, y, z + 1, x, y,z, source, materials);

                if (fixedMod(z) > 0)
                hasOneNeightbor = hasOneNeightbor || hasExposedNeightbor(x, y, z - 1, x, y,z, source, materials);

                if (y < maxHeight -1)
                hasOneNeightbor = hasOneNeightbor || hasExposedNeightbor(x, y + 1, z, x, y,z, source, materials);

                if (y > minHeight)
                hasOneNeightbor = hasOneNeightbor || hasExposedNeightbor(x, y - 1, z, x, y,z, source, materials);
            }
            if (!hasOneNeightbor) {
                Bukkit.getLogger().info("repetitions: " + repetitions);
                break;
            }
            if (repetitions > 990){
                Bukkit.getLogger().info("LOL: " + repetitions);
            }
        }

        int diamond_count = 0;
        int iron_count = 0;

        for (int i = 0; i < ores.size(); i++) {
            int x = ores.get(i).getX();
            int y = ores.get(i).getY();
            int z = ores.get(i).getZ();

            int index = getIndx(x, y, z);

            if (materials[index] == null) {
                ores.get(i).setType(Material.IRON_BLOCK, false);
                iron_count++;
            } else {
                ores.get(i).setType(Material.DIAMOND_BLOCK, false);
                diamond_count++;
                Bukkit.getLogger().info(ores.get(i).getX() + " " + ores.get(i).getY() + " " + ores.get(i).getZ());
            }
        }
        Bukkit.getLogger().info("ores: " + ores.size());
        Bukkit.getLogger().info("diamonds: " + diamond_count);
        Bukkit.getLogger().info("irons: " + iron_count);

    }
}

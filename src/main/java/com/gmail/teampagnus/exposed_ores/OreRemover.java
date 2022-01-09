package com.gmail.teampagnus.exposed_ores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

class DesiredBlock {
	public Block block;
	public Boolean isDesired;
	
	public DesiredBlock(Block block, Boolean isDesired) {
		this.block = block;
		this.isDesired = isDesired;
	}
}

public class OreRemover {
	public static void getOresInChunk(ArrayList<DesiredBlock> desiredBlockList, Chunk chunk, ArrayList<Material> materialList) {
		int maxHeight = chunk.getWorld().getMaxHeight();
		int minHeight = chunk.getWorld().getMinHeight();
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = minHeight; y < maxHeight; y++) {
					Block block = chunk.getBlock(x, y, z);
					if (materialList.contains(block.getType()));
						desiredBlockList.add(new DesiredBlock(block, false));
				}
			}
		}
	}

	public static Boolean oreHasExposedNeighbor(DesiredBlock db, Chunk ch){
		return true;
	}

	public static Boolean updateExposedOres(ArrayList<DesiredBlock> desiredBlockList, Chunk chunk){
		Boolean someChange = false;
		for (int i = 0; i < desiredBlockList.size(); i++){
			DesiredBlock db = desiredBlockList.get(i);
			someChange = someChange || oreHasExposedNeighbor(db, chunk);
		}
		return someChange;
	}

	public static void oreRemoverReplacement(ArrayList<DesiredBlock> desiredBlockList, Material dest){
		for (int i = 0; i < desiredBlockList.size(); i++){
			DesiredBlock db = desiredBlockList.get(i);
			if (!db.isDesired){
				db.block.setType(dest, false);
			}
		}
	}

	private static int MAX_UPDATES = 100;
	private static ArrayList<Material> materialList = new ArrayList<Material>(Arrays.asList(Material.REDSTONE_ORE, Material.COAL_ORE, Material.GOLD_ORE, Material.IRON_ORE, Material.DIAMOND_ORE));

	public static void main(World world, Random random, Chunk source){
		ArrayList<DesiredBlock> desiredBlockList = new ArrayList<DesiredBlock>();
		OreRemover.getOresInChunk(desiredBlockList, source, materialList);
		for (int i = 0; i < MAX_UPDATES; i++){
			if (OreRemover.updateExposedOres(desiredBlockList, source)){
				i = MAX_UPDATES;
			}
		}
		OreRemover.oreRemoverReplacement(desiredBlockList, Material.STONE);
	}

	public static void mainOnlyOres(World world, Random random, Chunk source){
		int maxHeight = source.getWorld().getMaxHeight();
		int minHeight = source.getWorld().getMinHeight();

        for (int x = 0; x < 16; x++) {
            for (int y = minHeight; y < maxHeight; y++) {
                for (int z = 0; z < 16; z++) {
                    Block b = source.getBlock(x, y, z);
                    if (!b.getType().name().endsWith("_ORE"))
                    {
                        b.setType(Material.AIR, false);
                    }
                }
            }
        }
	}

	public static void replace(Chunk chunk) {
		for (int y = 1; y * 16 < 256; y++) {
			chunk.getBlock(0, y * 16, 0).setType(Material.OBSIDIAN, false);
		}
	}
}
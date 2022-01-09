package com.gmail.teampagnus.exposed_ores;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkPopulateEvent;
import org.bukkit.Chunk;
import org.bukkit.block.Block;
import org.bukkit.Material;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class Exposed_ores extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getCommand("sayhello").setExecutor((sender, command, label, args) -> SayHelloCommand(sender, command, label, args));
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }

    private boolean SayHelloCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("Oh - Hallo aoeusnth \"" + sender.getName() + "\"");
        return true;
    }

    @EventHandler
    public void onChunkLoad(ChunkLoadEvent evt) {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (int y = 1; y < 132; y++) {
                    for (int x = 0; x < 16; x++) {
                        for (int z = 0; z < 16; z++) {
                            Block block = evt.getChunk().getBlock(x, y, z);
                            if (block.getType().name().contains("GRASS")) {
                                Bukkit.getLogger().info("replaced");
                                block.setType(Material.DIAMOND_BLOCK);
                                return;
                            }
                        }
                    }
                }
            }
        }.runTask(this);
    }

}
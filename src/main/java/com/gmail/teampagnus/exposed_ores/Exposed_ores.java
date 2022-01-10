package com.gmail.teampagnus.exposed_ores;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.World;


// public final class Exposed_ores extends JavaPlugin implements Listener {
// 	// @Override
// 	// public void onEnable() {
//     //     getServer().getPluginManager().registerEvents(new ChunkPopulateListener(), this);
// 	// }

//     @Override
//     public void onEnable() {
//         getCommand("sayhello").setExecutor((sender, command, label, args) -> SayHelloCommand(sender, command, label, args));
//         Bukkit.getServer().getPluginManager().registerEvents(this, this);
//     }

//     private boolean SayHelloCommand(CommandSender sender, Command command, String label, String[] args) {
//         sender.sendMessage("Oh - Hallo aoeusnth \"" + sender.getName() + "\"");
//         return true;
//     }

//     @EventHandler
//     public void onWorldInit(WorldInitEvent e){
//         getLogger().info("world init triggered");
//         World world = e.getWorld();
//         world.getPopulators().add(new MoonCraterPopulator());

public final class Exposed_ores extends JavaPlugin {
    @Override
    public void onEnable() {
         getServer().getPluginManager().registerEvents(new WorldInitListener(), this);
    }
}


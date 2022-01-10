package com.gmail.teampagnus.exposed_ores;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.World;

public final class Exposed_ores extends JavaPlugin {
    @Override
    public void onEnable() {
         getServer().getPluginManager().registerEvents(new WorldInitListener(), this);
    }
}


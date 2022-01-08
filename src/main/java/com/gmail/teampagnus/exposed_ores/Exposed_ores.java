package com.gmail.teampagnus.exposed_ores;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class Exposed_ores extends JavaPlugin {
    @Override
    public void onEnable() {
        getCommand("sayhello").setExecutor((sender, command, label, args) -> SayHelloCommand(sender, command, label, args));
    }

    private boolean SayHelloCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("Oh - Hallo aoeusnth \"" + sender.getName() + "\"");
        return true;
    }
}
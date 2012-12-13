package me.blockcat.catmotion.commands;

import org.bukkit.command.CommandSender;

public abstract class CCommand {
	
	public abstract void execute(CommandSender sender, String command, String[] args);

}

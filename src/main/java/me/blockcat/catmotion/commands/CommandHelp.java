package me.blockcat.catmotion.commands;

import me.blockcat.catmotion.commands.CCommandHandler.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CommandHelp extends CCommand {

	@Override
	public void execute(CommandSender sender, String command, String[] args) 
	{
		sender.sendMessage(ChatColor.GOLD + "CatMotion help:");
		sender.sendMessage(ChatColor.AQUA + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		for (Commands commands : Commands.values()) {			
				sender.sendMessage(ChatColor.GREEN + "/cmotion " + commands.name().toLowerCase() + " - " + ChatColor.GOLD + commands.description_);
		}
		
		sender.sendMessage(ChatColor.AQUA + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}

}

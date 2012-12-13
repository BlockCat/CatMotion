package me.blockcat.catmotion.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandRemove extends CCommand{

	@Override
	public void execute(CommandSender sender, String command, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can use this command.");
			return;
		}
	}

}

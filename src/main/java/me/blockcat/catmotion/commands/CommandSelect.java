package me.blockcat.catmotion.commands;

import me.blockcat.catmotion.collections.PlayerCollection;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSelect extends CCommand{

	@Override
	public void execute(CommandSender sender, String command, String[] args) {
		if (sender instanceof Player) {
			sender.sendMessage(ChatColor.GREEN + "Select motion block.");
			PlayerCollection.setSelection(sender.getName(), 4);
			return;
		}
		sender.sendMessage(ChatColor.RED + "Only players can use this command.");
		return;
	}

}

package me.blockcat.catmotion.commands;

import me.blockcat.catmotion.collections.PlayerCollection;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandPointSelect extends CCommand {

	@Override
	public void execute(CommandSender sender, String command, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can use this command.");
			return;
		}
		Player player = (Player) sender;
		if (command.equalsIgnoreCase("p1") || command.equalsIgnoreCase("b1")) 
		{
			PlayerCollection.setSelection(player.getName(), 1);
			player.sendMessage(ChatColor.GREEN + "Left-click first block.");
		} 
		else if (command.equalsIgnoreCase("p2") || command.equalsIgnoreCase("b2")) 
		{
			PlayerCollection.setSelection(player.getName(), 2);
			player.sendMessage(ChatColor.GREEN + "Left-click second block.");
		}
		else if (command.equalsIgnoreCase("create") || command.equalsIgnoreCase("c")) 
		{
			PlayerCollection.setSelection(player.getName(), 3);
			player.sendMessage(ChatColor.GREEN + "Left-click command block.");
		}
	}

}

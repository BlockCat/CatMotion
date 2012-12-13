package me.blockcat.catmotion.commands;

import me.blockcat.catmotion.collections.PlayerCollection;
import me.blockcat.catmotion.collections.animation.Animation;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandDelay extends CCommand{

	@Override
	public void execute(CommandSender sender, String command, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can use this command.");
			return;
		}
		
		if (PlayerCollection.getAnimation(sender.getName()) == null) {
			sender.sendMessage(ChatColor.RED + "Please select a motion block first.");
		}
		if (args.length == 0) {
			Animation a = PlayerCollection.getAnimation(sender.getName());
			sender.sendMessage(ChatColor.GREEN + "The delay of this animation is: " + ChatColor.GOLD + a.getDelay());
		}
		
		try {
			int delay = Integer.parseInt(args[0]);
			delay = Math.abs(delay);
			
			Animation a = PlayerCollection.getAnimation(sender.getName());
			a.setDelay(delay);
			
			sender.sendMessage(ChatColor.GREEN + "The delay of this animation is now: " + ChatColor.GOLD + delay);
			
		} catch (Exception e) {
			sender.sendMessage(ChatColor.RED + "Not a valid number.");
		}
	}

}

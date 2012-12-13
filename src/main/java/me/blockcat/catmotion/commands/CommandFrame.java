package me.blockcat.catmotion.commands;

import me.blockcat.catmotion.collections.PlayerCollection;
import me.blockcat.catmotion.collections.animation.Animation;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandFrame extends CCommand {

	@Override
	public void execute(CommandSender sender, String command, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can use this command.");
			return;
		}

		Animation animation = PlayerCollection.getAnimation(sender.getName());

		if (animation == null) {
			sender.sendMessage(ChatColor.RED + "Please select motion block first.");
			return;
		}

		if (args.length == 0) {
			sender.sendMessage(ChatColor.GREEN + "Frame displaying: " + ChatColor.GOLD + animation.frame);
			return;
		}

		try {
			int i = animation.setFrame(Integer.parseInt(args[0]));
			sender.sendMessage(ChatColor.GREEN + "Set frame to: " + ChatColor.AQUA + i);
		} catch (Exception e) {
			sender.sendMessage(ChatColor.RED + "Invalid number.");
		}

	}

}

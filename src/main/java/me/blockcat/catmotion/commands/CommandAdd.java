package me.blockcat.catmotion.commands;

import me.blockcat.catmotion.collections.PlayerCollection;
import me.blockcat.catmotion.collections.animation.Animation;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandAdd extends CCommand{

	@Override
	public void execute(CommandSender sender, String command, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can use this command.");
			return;
		}
		
		Player player = (Player) sender;
		
		Animation animation = PlayerCollection.getAnimation(player.getName());
		
		if (animation == null) {
			player.sendMessage(ChatColor.RED + "Please select a Motion block first.");
			return;
		}
		
		player.sendMessage(ChatColor.GREEN + "Added new frame.");
		animation.addFrame(player.getName());
	}

}

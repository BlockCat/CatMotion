package me.blockcat.catmotion.commands;

import me.blockcat.catmotion.CatMotion;
import me.blockcat.catmotion.collections.AnimationCollection;
import me.blockcat.catmotion.collections.PlayerCollection;
import me.blockcat.catmotion.collections.animation.Animation;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandDelete extends CCommand {

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
		AnimationCollection animationCollection = CatMotion.plugin.getAnimationCollection();
		animationCollection.removeAnimation(animation);
	}

}

package me.blockcat.catmotion.listener;

import java.util.List;

import me.blockcat.catmotion.CatMotion;
import me.blockcat.catmotion.collections.PlayerCollection;
import me.blockcat.catmotion.collections.animation.Animation;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerListener implements Listener {

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.getAction() == Action.LEFT_CLICK_BLOCK
				&& PlayerCollection.getSelection(event.getPlayer().getName()) != 0) {

			/** Cancel event. */
			event.setCancelled(true);

			Player player = event.getPlayer();
			Block block = event.getClickedBlock();

			/** Select first block. */
			if (PlayerCollection.getSelection(player.getName()) == 1) {
				// sets block.
				PlayerCollection.setBlock1(player.getName(), block);
				// resets selection.
				PlayerCollection.setSelection(player.getName(), 0);
				player.sendMessage(ChatColor.GREEN + "First block set.");
				
			/** Select second block. */
			} else if (PlayerCollection.getSelection(player.getName()) == 2) {
				// sets block.
				PlayerCollection.setBlock2(player.getName(), block);
				// resets selection.
				PlayerCollection.setSelection(player.getName(), 0);
				player.sendMessage(ChatColor.GREEN + "Second block set.");
				
			/** Create motion block. */
			} else if (PlayerCollection.getSelection(player.getName()) == 3) {
				List<Animation> animations = CatMotion.plugin.getAnimationCollection().getAnimations();
				for (Animation animation : animations) {
					if (animation.getLocation().equals(block.getLocation())) {
						player.sendMessage(ChatColor.RED + "There already is a motion block here.");
						PlayerCollection.setSelection(player.getName(), 0);
						return;
					}					
				}
				Animation animation = CatMotion.plugin.getAnimationCollection().newAnimation(block.getLocation());
				player.sendMessage(ChatColor.GREEN + "Created new motion block.");
				PlayerCollection.setAnimation(player.getName(), animation);
				PlayerCollection.setSelection(player.getName(), 0);
				
			/** Select motion block */
			} else if (PlayerCollection.getSelection(player.getName()) == 4) {
				List<Animation> animations = CatMotion.plugin.getAnimationCollection().getAnimations();
				for (Animation animation : animations) {
					if (animation.getLocation().equals(block.getLocation())) {
						player.sendMessage(ChatColor.GREEN + "Motion block selected.");
						PlayerCollection.setAnimation(player.getName(), animation);
						PlayerCollection.setSelection(player.getName(), 0);
						return;
					}					
				}
				player.sendMessage(ChatColor.RED + "No motion block found.");
				PlayerCollection.setSelection(player.getName(), 0);
			}
		}
	}
}

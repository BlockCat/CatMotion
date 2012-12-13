package me.blockcat.catmotion.commands;

import org.apache.commons.lang.ArrayUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CCommandHandler implements CommandExecutor {



	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		if (args.length == 0) {
			sender.sendMessage(ChatColor.GREEN + "Use /cmotion help to view the help page.");
			return true;
		}
		
		String command = args[0];
		args = (String[]) ArrayUtils.remove(args, 0);
		
		CCommand executor = Commands.getExecutor(command);		
		if (executor == null) {
			
			return true;
		} else {
			executor.execute(sender, command, args);
		}


		return true;
	}

	public enum Commands {
		HELP("?", "Shows this.", CommandHelp.class),
		P1("b1", "Selects first point of animation area.", CommandPointSelect.class),
		P2("b2", "Selects second point of animation area.", CommandPointSelect.class),
		CREATE("c", "Creates new animation.", CommandPointSelect.class),
		ADD("a","Add frame to animation.", CommandAdd.class),
		DELAY("d", "Sets the delay of animation", CommandDelay.class),
		FRAME("f", "Select frame of animation.", CommandFrame.class),
		SELECT("s", "Selects motion block.", CommandSelect.class),
		DELETE("del", "Deletes selected animation", CommandDelete.class),
		REMOVE("r", "Remove frame of animation.", CommandRemove.class);

		public String command_;
		public String description_;
		private Class<? extends CCommand> executor_;

		Commands(String command_, String description_, Class<? extends CCommand> executor_) {
			this.command_ = command_;
			this.description_ = description_;
			this.executor_ = executor_;
		}
		
		public String toString() {
			return command_;
		}
		
		public String getDescription() {
			return this.description_;
		}

		public static CCommand getExecutor(String command) 
		{
			try {
			return Commands.valueOf(command.toUpperCase()).executor_.newInstance();
			} catch (Exception e) {				
				
				for (Commands commands : Commands.values()) {					
					if (commands.toString().equalsIgnoreCase(command)) {
						try {
						return commands.executor_.newInstance();
						} catch (Exception e1) {
							return null;
						}
					}
				}
				return null;
			}
		}
	}



}

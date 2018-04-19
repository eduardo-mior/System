package rush.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

import rush.utils.ConfigManager;

public class ComandoOnline implements Listener, CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandlabel, String[] args) {
	      if (cmd.getName().equalsIgnoreCase("online")) {
                  sender.sendMessage(ConfigManager.getConfig("mensagens").getString("Players-Online").replace("&", "§").replace("%online%", String.valueOf(Bukkit.getOnlinePlayers().size())));
	      }
	return false;
	}
}
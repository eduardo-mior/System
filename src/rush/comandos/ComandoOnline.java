package rush.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

import rush.utils.ConfigManager;

public class ComandoOnline implements Listener, CommandExecutor {
	
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("online")) {
			s.sendMessage(ConfigManager.getConfig("mensagens").getString("Players-Online").replace("&", "§").replace("%online%", String.valueOf(Bukkit.getOnlinePlayers().size())));
		}
		return false;
	}
}
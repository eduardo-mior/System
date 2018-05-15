package rush.comandos;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

import rush.utils.ConfigManager;

public class ComandoClearChat implements Listener, CommandExecutor {
	
	String chat = " §c \n §c ";
	String limparchat = StringUtils.repeat(chat, 100);

	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("clearchat")) {	
			
			Bukkit.broadcastMessage(limparchat);
			if (ConfigManager.getConfig("mensagens").getBoolean("Avisar-Que-O-Chat-Foi-Limpo")) {
				Bukkit.broadcastMessage(ConfigManager.getConfig("mensagens").getString("Aviso-Que-O-Chat-Limpo-Global").replace("&", "§").replace("%player%", s.getName()));
			}
		}
		return false;
	}
}

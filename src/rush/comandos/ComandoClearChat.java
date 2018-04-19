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

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandlabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("clearchat")) {
	         if (!sender.hasPermission("system.clearchat")) {
    			 sender.sendMessage(ConfigManager.getConfig("mensagens").getString("Sem-Permissao"));
	         } else {
	        	 Bukkit.broadcastMessage(limparchat);
	        	 if (ConfigManager.getConfig("mensagens").getBoolean("Avisar-Que-O-Chat-Foi-Limpo")) {
	    			 Bukkit.broadcastMessage(ConfigManager.getConfig("mensagens").getString("Aviso-Que-O-Chat-Limpo-Global").replaceAll("&", "§").replaceAll("%player%", sender.getName()));
	    		 }
	        }
		}
		return false;
	}
}

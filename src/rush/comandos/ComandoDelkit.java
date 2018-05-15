package rush.comandos;

import java.io.File;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

import rush.utils.ConfigManager;
import rush.utils.DataManager;

public class ComandoDelkit implements Listener, CommandExecutor {
	
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("delkit")) {
			 
			if (args.length != 1) {
				s.sendMessage(ConfigManager.getConfig("mensagens").getString("DelKit-Comando-Incorreto").replace("&", "§"));
				return false;
			} 
				     
			String kit = args[0].toLowerCase();
			File file = DataManager.getFile(kit, "kits");
			if (!file.exists()) {
				s.sendMessage(ConfigManager.getConfig("mensagens").getString("Kit-Nao-Existe").replace("&", "§").replace("%kit%", kit));
				ComandoKits.ListKits(s);
				return false;
			}
			 
			DataManager.deleteFile(file);
			s.sendMessage(ConfigManager.getConfig("mensagens").getString("Kit-Deletado").replace("&", "§").replace("%kit%", kit));
		}
		return false;
	}
}

package rush.comandos;

import java.io.File;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

import rush.utils.ConfigManager;
import rush.utils.DataManager;

public class ComandoDelkit implements Listener, CommandExecutor {
	
	@Override
	 public boolean onCommand(final CommandSender s, Command cmd, String lbl, String[] args) {
		 if (cmd.getName().equalsIgnoreCase("delkit")) {
			 if (s.hasPermission("system.delkit")) {
			     if (args.length > 1 || args.length < 1) {
			          s.sendMessage(ConfigManager.getConfig("mensagens").getString("DelKit-Comando-Incorreto").replaceAll("&", "§"));
			          return false;
			     } 
				     
			     String kit = args[0].toLowerCase();
			     File file = DataManager.getFile(kit, "kits");
			     if (file.exists()) {
			    	 DataManager.deleteFile(file);
			    	 s.sendMessage(ConfigManager.getConfig("mensagens").getString("Kit-Deletado").replace("&", "§").replace("%kit%", kit));
			    	 return false;
			     } 
				       
			     else {
			         s.sendMessage(ConfigManager.getConfig("mensagens").getString("Kit-Nao-Existe").replaceAll("&", "§").replace("%kit%", kit));
				     ComandoKits.ListKits(s);
				     return false;
			     }
			 }
			 s.sendMessage(ConfigManager.getConfig("mensagens").getString("Sem-Permissao").replace("&", "§"));
			 return false;
		 }
		return false;
	}
}

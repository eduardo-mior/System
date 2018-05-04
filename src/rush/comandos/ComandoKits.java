package rush.comandos;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

import rush.utils.ConfigManager;
import rush.utils.DataManager;

public class ComandoKits implements Listener, CommandExecutor {
	
	public boolean onCommand(CommandSender s, Command cmd, String commandlabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("kits")) {
			ListKits(s);
		}
	    return false;
	}
	
	public static void ListKits(CommandSender s) {
		File folder = DataManager.getFolder("kits");
		File[] file = folder.listFiles();
  	  	List<String> kits = new ArrayList<String>();
  	  	String separador = ConfigManager.getConfig("mensagens").getString("Separador-De-Listas").replace("&", "§");
  	  	int cont = 0;
  	  	if (file.length == 0) {
  	  		s.sendMessage(ConfigManager.getConfig("mensagens").getString("Nenhum-Kit-Criado").replace("&", "§"));
  	  		return;
  	  	} 
  	  	else {
  	  		for (int i=0; i < file.length; i++) {
  	  			if (file[i].isFile()) {
  	  				String permissao = DataManager.getConfiguration(file[i]).getString("Permissao");
  				  
  	  				if(s.hasPermission(permissao)) {
  	  					kits.add(file[i].getName().replace(".yml", ""));
  					  	cont++;
  	  				}
  	  			}
  	  		}
  	  		if (cont == 0) {
  	  			s.sendMessage(ConfigManager.getConfig("mensagens").getString("Nenhum-Kit-Criado").replace("&", "§"));
  	  			return;
  	  		} 
  	  		else {
  	  			String kitslist = kits.toString();
  	  			s.sendMessage(ConfigManager.getConfig("mensagens").getString("Kits-Lista").replace("&", "§").replace("%kits%", kitslist.substring(1,kitslist.length() -1)).replace("%n%", String.valueOf(cont)).replace(",", separador));
  	  		}
  	  	}
	}
}
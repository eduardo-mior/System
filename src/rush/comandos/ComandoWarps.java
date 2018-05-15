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

public class ComandoWarps implements Listener, CommandExecutor {
	
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("warps")) {
			ListWarps(s);
		}
		return false;
	}
	
	public static void ListWarps(CommandSender s) {
		File folder = DataManager.getFolder("warps");
		File[] file = folder.listFiles();
  	  	List<String> warps = new ArrayList<String>();
  	  	if (file.length == 0) {
  	  		s.sendMessage(ConfigManager.getConfig("mensagens").getString("Nenhuma-Warp-Definida").replace("&", "§"));
  	  		return;
  	  	} 
  	  	
  	  	int cont = 0;
  	  	for (int i=0; i < file.length; i++) {
  	  		if (file[i].isFile()) {
  	  			String permissao = DataManager.getConfiguration(file[i]).getString("Permissao");
  	  			if(s.hasPermission(permissao)) {
  	  				warps.add(file[i].getName().replace(".yml", ""));
  	  				cont++;
  	  			}
  	  		}
  	  	}
  	  	if (cont == 0) {
  	  		s.sendMessage(ConfigManager.getConfig("mensagens").getString("Nenhuma-Warp-Definida").replace("&", "§"));
  	  		return;
  	  	} 
  	  	
  	  	String separador = ConfigManager.getConfig("mensagens").getString("Separador-De-Listas").replace("&", "§");
  	  	String warplist = warps.toString();
  	  	s.sendMessage(ConfigManager.getConfig("mensagens").getString("Warps-Lista").replace("&", "§").replace("%warps%", warplist.substring(1,warplist.length() -1)).replace("%n%", String.valueOf(cont)).replace(",", separador));
	}
}
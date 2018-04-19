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
	
	public boolean onCommand(CommandSender s, Command cmd, String commandlabel, String[] args) {
	      if (cmd.getName().equalsIgnoreCase("warps")) {
	    	  File folder = DataManager.getFolder("Warps");
	    	  File[] file = folder.listFiles();
	    	  List<String> warps = new ArrayList<String>();
	    	  String separador = ConfigManager.getConfig("mensagens").getString("Separador-De-Warps").replace("&", "§");
	    	  int cont = 0;
	    	  if (file.length == 0) {
	    		  s.sendMessage(ConfigManager.getConfig("mensagens").getString("Nenhuma-Warp-Definida").replace("&", "§"));
	    		  return false;
	    	  } 
	    	  else {
	    		  for (int i=0; i < file.length; i++) {
	    			  if (file[i].isFile()) {
	    				  String permissao = DataManager.getConfiguration(file[i]).getString("Permissao");
	    				  
	    				  if(s.hasPermission(permissao))
	    					  warps.add(file[i].getName().replace(".yml", ""));
	    					  cont++;
	    			  }
	    		  }
	    		  if (cont == 0) {
		    		  s.sendMessage(ConfigManager.getConfig("mensagens").getString("Nenhuma-Warp-Definida").replace("&", "§"));
		    		  return false;
	    		  } 
	    		  else {
	    			  String warplist = warps.toString();
		    		  s.sendMessage(ConfigManager.getConfig("mensagens").getString("Warps-Lista").replace("&", "§").replace("%warps%", warplist.substring(1,warplist.length() -1)).replace("%n%", String.valueOf(cont)).replace(",", separador));
	    		  }
	    	  }
	      }
	return false;
	}
}
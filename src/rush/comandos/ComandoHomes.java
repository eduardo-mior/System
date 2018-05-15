package rush.comandos;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;

import rush.utils.ConfigManager;
import rush.utils.DataManager;

public class ComandoHomes implements Listener, CommandExecutor {
	
	@Override
	 public boolean onCommand(final CommandSender s, Command cmd, String lbl, String[] args) {
		 if (cmd.getName().equalsIgnoreCase("homes")) {
		     
			 if (args.length > 1) {
		         s.sendMessage(ConfigManager.getConfig("mensagens").getString("Homes-Comando-Incorreto").replace("&", "§"));
		         return false;
		     }
			 
			 String player;
			 if (args.length == 1) {
		    	 player = args[0].replace(":", "");
			 } else {
				 player = s.getName();
			 }
			 ListHomes(s, player);
		 }
		 return false;
	}
	
	public static void ListHomes(CommandSender s, String player) {
	     File file = DataManager.getFile(player.toLowerCase(), "playerdata");
	     if (!file.exists()) {
	       	 s.sendMessage(ConfigManager.getConfig("mensagens").getString("Player-Nao-Existe").replace("&", "§").replace("%player%", player));
	       	 return;
	     }
	     
	  	 String separador = ConfigManager.getConfig("mensagens").getString("Separador-De-Listas").replace("&", "§");
	     FileConfiguration config = DataManager.getConfiguration(file);
	     List<String> homesParticulares = new ArrayList<String>();
	     List<String> homesPublicas = new ArrayList<String>();
	     Set<String> KEYS = config.getConfigurationSection("Homes").getKeys(false);
	     int n = KEYS.size();
	     for (int i=0; n > i; i++) {
	    	 String home = KEYS.iterator().next();
	    	 KEYS.remove(home);
	    	 if (config.getBoolean("Homes." + home + ".Publica") == false) {
	    		 homesParticulares.add(home);
	    	 } else {
	    		 homesPublicas.add(home);
	    	 }
	     }
	     
 		 String particulares = homesParticulares.toString().length() < 3 ? "[§8§oNenhuma]" : homesParticulares.toString();
 		 String publicas = homesPublicas.toString().length() < 3 ? "[§8§oNenhuma]" : homesPublicas.toString();
	     s.sendMessage(ConfigManager.getConfig("mensagens").getString("Homes-Publicas").replace("%homes%", publicas.substring(1, publicas.length() -1).replace(",", separador)).replace("&", "§"));
	     if (player.equals(s.getName()) || s.hasPermission("system.home.admin")) {
		     s.sendMessage(ConfigManager.getConfig("mensagens").getString("Homes-Particulares").replace("%homes%", particulares.substring(1, particulares.length() -1).replace(",", separador)).replace("&", "§"));
	     }
	}
}
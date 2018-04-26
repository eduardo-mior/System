package rush.comandos;

import java.io.File;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import rush.Main;
import rush.utils.ConfigManager;
import rush.utils.DataManager;

public class ComandoHome implements Listener, CommandExecutor {
	
	@Override
	public boolean onCommand(final CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("home")) {
			 
			 if (!(s instanceof Player)) {
				 s.sendMessage(ConfigManager.getConfig("mensagens").getString("Console-Nao-Pode").replace("&", "§"));
				 return false;
			 }
			 
		     if (args.length == 0) {
		          s.sendMessage(ConfigManager.getConfig("mensagens").getString("Home-Comando-Incorreto").replaceAll("&", "§"));
		          return false;
		     } 
		     
		     if (args.length > 1) {
		          s.sendMessage(ConfigManager.getConfig("mensagens").getString("Home-Comando-Incorreto").replaceAll("&", "§"));
		          return false;
		     }
		     
		    int delay = ConfigManager.getConfig("settings").getInt("Delay-Para-Teleportar-Comandos");
		    String home = args[0];
		   	if (home.contains(":")) {
			    Player p = (Player)s;
		   		String[] homeSplit = home.split(":");
		        
		        if (homeSplit.length < 1 || homeSplit.length > 3) {
		        	s.sendMessage(ConfigManager.getConfig("mensagens").getString("Home-Outro-Comando-Incorreto").replaceAll("&", "§"));
		        	return false;
		        }
		        
		   		String player = homeSplit[0];
		        File file = DataManager.getFile(player.toLowerCase(), "playerdata");
		        
		        if (!file.exists()) {
		        	s.sendMessage(ConfigManager.getConfig("mensagens").getString("Player-Nao-Existe").replaceAll("&", "§").replace("%player%", homeSplit[0]));
		        	return false;
		        }
		        
		        if (homeSplit.length == 1) {
		   			ComandoHomes.ListHomes(s, player);
		        	return false;
		        }
		        
		        FileConfiguration config = DataManager.getConfiguration(file);
		        Set<String> KEYS = config.getConfigurationSection("Homes").getKeys(false);
		        
		        if (!KEYS.contains(homeSplit[1])) {
		        	s.sendMessage(ConfigManager.getConfig("mensagens").getString("Player-Home-Nao-Existe").replaceAll("&", "§").replace("%player%", homeSplit[0]).replace("%home%", homeSplit[1]));
		   			ComandoHomes.ListHomes(s, player);
		        	return false;
		       	}
		       	
		        if (config.getBoolean("Homes." + homeSplit[1] + ".Publica") == false) {
		        	if (!s.hasPermission("system.home.admin")) {
		        	s.sendMessage(ConfigManager.getConfig("mensagens").getString("Player-Home-Nao-Publica").replaceAll("&", "§").replace("%player%", homeSplit[0]).replace("%home%", homeSplit[1]));
		        	return false;
		        	}
		       	}
		        
				String[] locationSplitted = config.getString("Homes." + homeSplit[1] + ".Localizacao").split(",");
			    Location location = new Location(
			    		Bukkit.getWorld(locationSplitted[0]),
			    		Double.parseDouble(locationSplitted[1]),
			    		Double.parseDouble(locationSplitted[2]),
			    		Double.parseDouble(locationSplitted[3]),
			    		Float.parseFloat(locationSplitted[4]),
			    		Float.parseFloat(locationSplitted[5]));
				    
		    	if (!s.hasPermission("system.semdelay")) {
		        	s.sendMessage(ConfigManager.getConfig("mensagens").getString("Home-Publica-Iniciando-Teleporte").replaceAll("&", "§").replace("%home%", homeSplit[1]).replace("%tempo%", String.valueOf(delay)).replace("%player%", player));
		    		new BukkitRunnable() {
		    			@Override
		    			public void run() {
				        	s.sendMessage(ConfigManager.getConfig("mensagens").getString("Home-Publica-Teleportado-Sucesso").replaceAll("&", "§").replace("%home%", homeSplit[1]).replace("%player%", player));
		    				p.teleport(location);
		    			}
		    		}.runTaskLater(Main.aqui, 20 * delay);
		    		return false;
		    	}
			    	
		    	else {
		        	s.sendMessage(ConfigManager.getConfig("mensagens").getString("Home-Publica-Teleportado-Sucesso").replaceAll("&", "§").replace("%home%", homeSplit[1]).replace("%player%", player));
			 		p.teleport(location);
			 		return false;
		    	}
		   	}
		   	
		   	else {
			    Player p = (Player)s;
		   		String player = s.getName();
		        File file = DataManager.getFile(player.toLowerCase(), "playerdata");
		        FileConfiguration config = DataManager.getConfiguration(file);
		        Set<String> KEYS = config.getConfigurationSection("Homes").getKeys(false);
		   		
		        if (!KEYS.contains(home)) {
		        	s.sendMessage(ConfigManager.getConfig("mensagens").getString("Home-Nao-Existe").replaceAll("&", "§").replace("%home%", home));
		   			ComandoHomes.ListHomes(s, player);
		        	return false;
		   		}
		   		
		   		else {
				    String[] locationSplitted = config.getString("Homes." + home + ".Localizacao").split(",");
				    Location location = new Location(
				    		Bukkit.getWorld(locationSplitted[0]),
				    		Double.parseDouble(locationSplitted[1]),
				    		Double.parseDouble(locationSplitted[2]),
				    		Double.parseDouble(locationSplitted[3]),
				    		Float.parseFloat(locationSplitted[4]),
				    		Float.parseFloat(locationSplitted[5]));
			    	if (!s.hasPermission("system.semdelay")) {
			        	s.sendMessage(ConfigManager.getConfig("mensagens").getString("Home-Privada-Iniciando-Teleporte").replaceAll("&", "§").replace("%home%", home).replace("%tempo%", String.valueOf(delay)));
			    		new BukkitRunnable() {
			    			@Override
			    			public void run() {
					        	s.sendMessage(ConfigManager.getConfig("mensagens").getString("Home-Privada-Teleportado-Sucesso").replaceAll("&", "§").replace("%home%", home));
			    				p.teleport(location);
			    			}
			    		}.runTaskLater(Main.aqui, 20 * delay);
			    		return false;
			    	}
			    	
			    	else {
			        	s.sendMessage(ConfigManager.getConfig("mensagens").getString("Home-Privada-Teleportado-Sucesso").replaceAll("&", "§").replace("%home%", home));
				 		p.teleport(location);
			    	}
		   		}
		   	}
		 return false;
		 }
	return false;
	}
}
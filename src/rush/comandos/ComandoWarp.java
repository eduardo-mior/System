package rush.comandos;

import java.io.File;

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

public class ComandoWarp implements Listener, CommandExecutor {
	
	@SuppressWarnings("deprecation")
	@Override
	 public boolean onCommand(final CommandSender s, Command cmd, String lbl, String[] args) {
		 if (cmd.getName().equalsIgnoreCase("warp")) {
			 if (s instanceof Player) {
				Player p = (Player) s;
					 
				if (args.length == 0) {
					s.sendMessage(ConfigManager.getConfig("mensagens").getString("Warp-Comando-Incorreto").replaceAll("&", "§"));
				    return false;
			    }
				     
				if (args.length > 1) {
				     s.sendMessage(ConfigManager.getConfig("mensagens").getString("Warp-Comando-Incorreto").replaceAll("&", "§"));
				     return false;
				}
				     
				else {
				   	String warp = args[0];
				    File file = DataManager.getFile(warp, "warps");
				    
				    if (!file.exists()) {
				    	s.sendMessage(ConfigManager.getConfig("mensagens").getString("Warp-Nao-Existe").replace("&", "§").replace("%warp%", warp));
				    	ComandoWarps.ListWarps(s);
				    	
				    } else {
					    FileConfiguration config = DataManager.getConfiguration(file);
					    String[] locationSplitted = config.getString("Localizacao").split(",");
					    Location location = new Location(
					    		Bukkit.getWorld(locationSplitted[0]),
					    		Double.parseDouble(locationSplitted[1]),
					    		Double.parseDouble(locationSplitted[2]),
					    		Double.parseDouble(locationSplitted[3]),
					    		Float.parseFloat(locationSplitted[4]),
					    		Float.parseFloat(locationSplitted[5]));
					    
				    	if (!s.hasPermission(config.getString("Permissao"))) {
				    	s.sendMessage(config.getString("MensagemSemPermissao").replace("&", "§"));
				    	return false;
				    	} 
				    	
				    	if (!s.hasPermission("system.semdelay") || config.getBoolean("DelayParaVips") == true) {
				    		s.sendMessage(config.getString("MensagemInicio").replace("&", "§"));
				    		new BukkitRunnable() {
				    			@Override
				    			public void run() {
				    				s.sendMessage(config.getString("MensagemFinal").replace("&", "§"));
				    				p.teleport(location);
				    				if (config.getBoolean("EnviarTitle")) {
				    					p.sendTitle(config.getString("Title").replace("&", "§"), config.getString("SubTitle").replace("&", "§"));
				    				}
				    			}
				    		}.runTaskLater(Main.aqui, 20 * config.getInt("Delay"));
				    		return false;
				    	}
				    	
				    	else {
						    s.sendMessage(config.getString("MensagemFinal").replace("&", "§"));
					 		p.teleport(location);
					 		if (config.getBoolean("EnviarTitle")) {
					 			p.sendTitle(config.getString("Title").replace("&", "§"), config.getString("SubTitle").replace("&", "§"));
					 		}
				    	}
				    }
				}
				return false;
			 }
			 s.sendMessage(ConfigManager.getConfig("mensagens").getString("Console-Nao-Pode").replace("&", "§"));
			 return false;
		 }
		return false;
	}
}
package rush.comandos;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import rush.utils.ConfigManager;
import rush.utils.DataManager;
import rush.utils.Locations;

public class ComandoSetspawn implements Listener, CommandExecutor {

	 public boolean onCommand(final CommandSender s, Command cmd, String lbl, String[] args) {
		 if (cmd.getName().equalsIgnoreCase("setspawn")) {
		      if (!s.hasPermission("system.setspawn")) {
		          s.sendMessage(ConfigManager.getConfig("mensagens").getString("Sem-Permissao").replaceAll("&", "§"));
		          return false;
		      }
		      
		      if (!(s instanceof Player)) {
		          s.sendMessage(ConfigManager.getConfig("mensagens").getString("Console-Nao-Pode").replaceAll("&", "§"));
		          return false;
		      }
		      
		      else {
				  File file = DataManager.getFile("locations");
				  FileConfiguration config = DataManager.getConfiguration(file);
		    	  Player p = (Player)s;
		    	  Location spawn = p.getLocation();
		    	  Locations.spawn = spawn;
		 	      config.set("Spawn", spawn);
			      config.set("Spawn.world", spawn.getWorld().getName());
			      config.set("Spawn.x", Double.valueOf(spawn.getX()));
			      config.set("Spawn.y", Double.valueOf(spawn.getY()));
			      config.set("Spawn.z", Double.valueOf(spawn.getZ()));
			      config.set("Spawn.yaw", Float.valueOf(spawn.getYaw()));
			      config.set("Spawn.pitch", Float.valueOf(spawn.getPitch()));
			      try {
					 config.save(file);
				  } catch (IOException e) {
					 Bukkit.getConsoleSender().sendMessage(ConfigManager.getConfig("mensagens").getString("Falha-Ao-Salvar").replace("&", "§").replace("%arquivo%", "locations.yml"));
				  }	
			      s.sendMessage(ConfigManager.getConfig("mensagens").getString("Spawn-Definido").replaceAll("&", "§"));
		      } 
		 }
		 return true;
	 }

	 public static void saveSpawn(Location spawn) {

	 }
}
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

public class ComandoSetmundovip implements Listener, CommandExecutor {

	@Override
	 public boolean onCommand(final CommandSender s, Command cmd, String lbl, String[] args) {
		 if (cmd.getName().equalsIgnoreCase("setmundovip")) {
			 if (s.hasPermission("system.setmundovip")) {
				 if (s instanceof Player) {
					 
				     if (args.length == 0) {
				          s.sendMessage(ConfigManager.getConfig("mensagens").getString("Setmundovip-Comando-Incorreto").replace("&", "§"));
				          return false;
				     }
				     
				     if (args.length > 1) {
				          s.sendMessage(ConfigManager.getConfig("mensagens").getString("Setmundovip-Comando-Incorreto").replace("&", "§"));
				          return false;
				     }
				     
				     File file = DataManager.getFile("locations");
				     FileConfiguration config = DataManager.getConfiguration(file);
		        	 Player p = (Player)s;
		             Location area = p.getLocation();
				     
			         if (args[0].equalsIgnoreCase("areavip")) {
			             Locations.areaVip = area;
			             config.set("AreaVip.world", area.getWorld().getName());
			         	 config.set("AreaVip.x", Double.valueOf(area.getX()));
			        	 config.set("AreaVip.y", Double.valueOf(area.getY()));
			        	 config.set("AreaVip.z", Double.valueOf(area.getZ()));
			        	 config.set("AreaVip.yaw", Float.valueOf(area.getYaw()));
			        	 config.set("AreaVip.pitch", Float.valueOf(area.getPitch()));
			    	     try {
			    	 		 config.save(file);
			    		 } catch (IOException e) {
			    			 Bukkit.getConsoleSender().sendMessage(ConfigManager.getConfig("mensagens").getString("Falha-Ao-Salvar").replace("&", "§").replace("%arquivo%", "locations.yml"));
			    		 }
			             s.sendMessage(ConfigManager.getConfig("mensagens").getString("Area-Vip-Definida").replace("&", "§"));
			             return false;
			         }
			         
			         if (args[0].equalsIgnoreCase("areanaovip")) {
			             Locations.areaNaoVip = area;
			         	 config.set("AreaNaoVip", area);
			        	 config.set("AreaNaoVip.world", area.getWorld().getName());
			        	 config.set("AreaNaoVip.x", Double.valueOf(area.getX()));
			        	 config.set("AreaNaoVip.y", Double.valueOf(area.getY()));
			        	 config.set("AreaNaoVip.z", Double.valueOf(area.getZ()));
			        	 config.set("AreaNaoVip.yaw", Float.valueOf(area.getYaw()));
			        	 config.set("AreaNaoVip.pitch", Float.valueOf(area.getPitch()));
			    	     try {
			    			 config.save(file);
			    	 	 } catch (IOException e) {
			    			 Bukkit.getConsoleSender().sendMessage(ConfigManager.getConfig("mensagens").getString("Falha-Ao-Salvar").replace("&", "§").replace("%arquivo%", "locations.yml"));
			    		 }
			             s.sendMessage(ConfigManager.getConfig("mensagens").getString("Area-Nao-Vip-Definida").replace("&", "§"));
			             return false;
			         }
				 }
				 s.sendMessage(ConfigManager.getConfig("mensagens").getString("Console-Nao-Pode").replace("&", "§"));
				 return false;
			 }
			 s.sendMessage(ConfigManager.getConfig("mensagens").getString("Sem-Permissao").replace("&", "§"));
			 return false;
		 }
		 return false;
	}
}
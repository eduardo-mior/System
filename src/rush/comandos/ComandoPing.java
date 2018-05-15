package rush.comandos;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import rush.utils.ConfigManager;

public class ComandoPing implements Listener, CommandExecutor {
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("ping"))
 	    	 
			if (!(s instanceof Player)) {
				s.sendMessage(ConfigManager.getConfig("mensagens").getString("Console-Nao-Pode").replace("&", "§"));
				return false;
			}
 	     
			if (args.length > 1) {
				s.sendMessage(ConfigManager.getConfig("mensagens").getString("Ping-Comando-Incorreto").replace("&", "§"));
				return false;
			}
 	     
 	     	try {
 	     		
 	     		if (args.length == 0) {
	 	     		Player player = (Player)s;
	 	     		Method player_getHandle = player.getClass().getMethod("getHandle");
		     		Object player_MC = player_getHandle.invoke(player, (Object[])null);
		     		Field player_ping = player_MC.getClass().getField("ping");
		     		String ping = String.valueOf(player_ping.get(player_MC));
		     		s.sendMessage(ConfigManager.getConfig("mensagens").getString("Ping.Seu-Ping").replace("&", "§").replace("%ping%", ping));
		     		return false;
 	     		}
 	     		
 	     		if (args.length == 1) {
 	     			Player player = Bukkit.getPlayer(args[0]);
 	 	     		if (player == null) {
 	 	     			s.sendMessage(ConfigManager.getConfig("mensagens").getString("Player-Offline").replace("&", "§"));
 	 	     			return false;
 	 	     		} else {
 	 	     			Method player_getHandle = player.getClass().getMethod("getHandle");
 	 	     			Object player_MC = player_getHandle.invoke(player, (Object[])null);
 	 	     			Field player_ping = player_MC.getClass().getField("ping");
 	 	     			String ping = String.valueOf(player_ping.get(player_MC));
	     				s.sendMessage(ConfigManager.getConfig("mensagens").getString("Ping.Player-Ping").replace("&", "§").replace("%ping%", ping).replace("%player%", player.getName()));
 	 	     		}
 	     		}
 	     	} catch (Exception var10) {
 	     		var10.printStackTrace();
 	     	}
 	     	return false;
       }
}

package rush.comandos;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import rush.utils.ConfigManager;

public class ComandoPing implements Listener, CommandExecutor {
	
	public boolean onCommand(final CommandSender s, Command cmd, String lbl, String[] args) {
 	     if (cmd.getName().equalsIgnoreCase("ping"))
             if (!(s instanceof Player)) {
      	          s.sendMessage(ConfigManager.getConfig("mensagens").getString("Console-Nao-Pode").replaceAll("&", "§"));
      	          return false;
             }
 	     
 	     	try {
 	     		Player player = (Player)s;
 	     		if (player == null) {
 	     			s.sendMessage(String.format(ConfigManager.getConfig("mensagens").getString("Player-Offline").replaceAll("&", "§")));
 	     			return false;
 	     		} else {
 	     			Method player_getHandle = player.getClass().getMethod("getHandle");
 	     			Object player_MC = player_getHandle.invoke(player, (Object[])null);
 	     			Field player_ping = player_MC.getClass().getField("ping");
 	     			String ping = String.valueOf(player_ping.get(player_MC));
 	     			s.sendMessage(args.length == 0 ? 
 	     					ConfigManager.getConfig("mensagens").getString("Ping.Seu-Ping").replace("&", "§").replace("%ping%", ping) : 
 	     					ConfigManager.getConfig("mensagens").getString("Ping.Player-Ping").replace("&", "§").replace("%ping%", ping).replace("%player%", player.getName()));
 	     			}
 	     	} catch (Exception var10) {
 	     		var10.printStackTrace();     
 	     	}
 	     	return false;
       }
}

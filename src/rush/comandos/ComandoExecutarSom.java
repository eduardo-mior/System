package rush.comandos;

import org.apache.commons.lang3.EnumUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import rush.utils.ConfigManager;

public class ComandoExecutarSom implements Listener, CommandExecutor {
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
        if (cmd.getName().equalsIgnoreCase("executarsom")) {
            
            if (args.length != 2) {
            	s.sendMessage(ConfigManager.getConfig("mensagens").getString("ExecutarSom-Comando-Incorreto").replace("&", "§"));
            	return false;
            }
    	        
           	if (args[1].equalsIgnoreCase("all")) {
           		if (EnumUtils.isValidEnum(Sound.class, args[0].toUpperCase())) {
                    for (Player todos : Bukkit.getOnlinePlayers()) {
                    	todos.playSound(todos.getLocation(), Sound.valueOf(args[0].toUpperCase()), 1, 1);
                    }
                	s.sendMessage(ConfigManager.getConfig("mensagens").getString("Som-Executado-Todos").replace("&", "§").replace("%som%", args[0]));
                	return false;
           		}
       			s.sendMessage(ConfigManager.getConfig("mensagens").getString("Som-Invalido").replace("&", "§"));
       			return false;
            }
    	        
           	String player = args[1];
           	Player beneficiado = Bukkit.getPlayer(player);
           	if (beneficiado != null) {
           		if (EnumUtils.isValidEnum(Sound.class, args[0].toUpperCase())) {
           			beneficiado.playSound(beneficiado.getLocation(), Sound.valueOf(args[0].toUpperCase()), 1, 1);
           			s.sendMessage(ConfigManager.getConfig("mensagens").getString("Som-Executado-Player").replace("&", "§").replace("%som%", args[0]).replace("%player%", args[1]));
           			return false;
           		}
           		s.sendMessage(ConfigManager.getConfig("mensagens").getString("Som-Invalido").replace("&", "§"));
           		return false;
           	}
           	s.sendMessage(ConfigManager.getConfig("mensagens").getString("Player-Offline").replace("&", "§"));
           	return false;
        }
		return false;
	}
}

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
	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (cmd.getName().equalsIgnoreCase("executarsom")) {
            if (sender.hasPermission("system.executarsom")) {
        	    for (Player todos : Bukkit.getOnlinePlayers()) {

                if (args.length >= 3) {
                	sender.sendMessage(ConfigManager.getConfig("mensagens").getString("ExecutarSom-Comando-Incorreto").replaceAll("&", "§"));
                    return true;
                }
            	
                if (args.length <= 1) {
                	sender.sendMessage(ConfigManager.getConfig("mensagens").getString("ExecutarSom-Comando-Incorreto").replaceAll("&", "§"));
                    return true;
                }
    	        
    	        if (args[1].equalsIgnoreCase("all")) {
                    if (args.length == 2) {
            			if (EnumUtils.isValidEnum(Sound.class, args[0].toUpperCase())) {
            				todos.playSound(todos.getLocation(), Sound.valueOf(args[0].toUpperCase()), 1, 1);
            				sender.sendMessage(ConfigManager.getConfig("mensagens").getString("Som-Executado-Todos").replaceAll("&", "§").replaceAll("%som%", args[0]));
            				return true;
                        }
                        sender.sendMessage(ConfigManager.getConfig("mensagens").getString("Som-Invalido").replaceAll("&", "§"));
                        return true;
                    }
                	sender.sendMessage(ConfigManager.getConfig("mensagens").getString("ExecutarSom-Comando-Incorreto").replaceAll("&", "§"));
                    return true;
    	        }
    	        
    	        if (args[1] != null) {
                    if (args.length == 2) {
                    final String nome = args[1];
                    final Player beneficiado = Bukkit.getPlayer(nome);
                    	if (beneficiado != null) {
                    			if (EnumUtils.isValidEnum(Sound.class, args[0].toUpperCase())) {
                    				beneficiado.playSound(beneficiado.getLocation(), Sound.valueOf(args[0].toUpperCase()), 1, 1);
                    				sender.sendMessage(ConfigManager.getConfig("mensagens").getString("Som-Executado-Player").replaceAll("&", "§").replaceAll("%som%", args[0]).replaceAll("%player%", args[1]));
                    				return true;
                                }
                                sender.sendMessage(ConfigManager.getConfig("mensagens").getString("Som-Invalido").replaceAll("&", "§"));
                                return true;
                        }
                        sender.sendMessage(ConfigManager.getConfig("mensagens").getString("Player-Offline").replaceAll("&", "§"));
                        return true;
                    }
                	sender.sendMessage(ConfigManager.getConfig("mensagens").getString("ExecutarSom-Comando-Incorreto").replaceAll("&", "§"));
                    return true;
    	        }
        	    }
            }
            sender.sendMessage(ConfigManager.getConfig("mensagens").getString("Sem-Permissao").replaceAll("&", "§"));
        }
		return false;
	}
}

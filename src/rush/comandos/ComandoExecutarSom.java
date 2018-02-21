package rush.comandos;

import org.apache.commons.lang3.EnumUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import rush.Main;

public class ComandoExecutarSom implements Listener, CommandExecutor {
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (cmd.getName().equalsIgnoreCase("executarsom")) {
            if (sender.hasPermission("system.executarsom")) {
        	    for (Player todos : Bukkit.getOnlinePlayers()) {

                if (args.length >= 3) {
                	sender.sendMessage(Main.aqui.getMensagens().getString("ExecutarSom-Comando-Incoreto").replaceAll("&", "§"));
                    return true;
                }
            	
                if (args.length <= 1) {
                	sender.sendMessage(Main.aqui.getMensagens().getString("ExecutarSom-Comando-Incoreto").replaceAll("&", "§"));
                    return true;
                }
    	        
    	        if (args[1].equalsIgnoreCase("all")) {
                    if (args.length == 2) {
            			if (EnumUtils.isValidEnum(Sound.class, args[0])) {
            				todos.playSound(todos.getLocation(), Sound.valueOf(args[0]), 1, 1);
            				sender.sendMessage(Main.aqui.getMensagens().getString("Som-Executado-Todos").replaceAll("&", "§").replaceAll("%som%", args[0]));
            				return true;
                        }
                        sender.sendMessage(Main.aqui.getMensagens().getString("Som-Invalido").replaceAll("&", "§"));
                        return true;
                    }
                	sender.sendMessage(Main.aqui.getMensagens().getString("ExecutarSom-Comando-Incoreto").replaceAll("&", "§"));
                    return true;
    	        }
    	        
    	        if (args[1] != null) {
                    if (args.length == 2) {
                    final String nome = args[1];
                    final Player beneficiado = Bukkit.getPlayer(nome);
                    	if (beneficiado != null) {
                    			if (EnumUtils.isValidEnum(Sound.class, args[0])) {
                    				beneficiado.playSound(beneficiado.getLocation(), Sound.valueOf(args[0]), 1, 1);
                    				sender.sendMessage(Main.aqui.getMensagens().getString("Som-Executado-Player").replaceAll("&", "§").replaceAll("%som%", args[0]).replaceAll("%player%", args[1]));
                    				return true;
                                }
                                sender.sendMessage(Main.aqui.getMensagens().getString("Som-Invalido").replaceAll("&", "§"));
                                return true;
                        }
                        sender.sendMessage(Main.aqui.getMensagens().getString("Player-Offline").replaceAll("&", "§"));
                        return true;
                    }
                	sender.sendMessage(Main.aqui.getMensagens().getString("ExecutarSom-Comando-Incoreto").replaceAll("&", "§"));
                    return true;
    	        }
                
        	    }
            }
            sender.sendMessage(Main.aqui.getMensagens().getString("Sem-Permissao").replaceAll("&", "§"));
        }
		return false;
	}
}

package rush.comandos;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rush.configuracoes.Mensagens;
import rush.utils.EnumUtils;

public class ComandoExecutarSom implements CommandExecutor {
	
	@SuppressWarnings("deprecation")	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
        if (cmd.getName().equalsIgnoreCase("executarsom")) {
            
			// Verificando se o player digitou o número minimo de argumentos
            if (args.length != 2) {
            	s.sendMessage(Mensagens.ExecutarSom_Comando_Incorreto);
            	return false;
            }
            
            // Verificando se o player quer mandar um som para todo o servidor
            if (args[1].equalsIgnoreCase("all")) {
           		
            	// Verificando se o som é um som valido
            	if (!EnumUtils.isValidEnum(Sound.class, args[0].toUpperCase())) {
            		s.sendMessage(Mensagens.Som_Invalido);
            		return false;
            	}
            	
            	// Executando o som para todos os players
            	for (Player todos : Bukkit.getOnlinePlayers()) {
            		todos.playSound(todos.getLocation(), Sound.valueOf(args[0].toUpperCase()), 1, 1);
            	}
            	s.sendMessage(Mensagens.Som_Executado_Todos.replace("%som%", args[0]));
            	return false;
            }
    	    
			// Pegando o player e verificando se ele esta online
           	Player p = Bukkit.getPlayer(args[1]);
           	if (p == null) {
               	s.sendMessage(Mensagens.Player_Offline);
               	return false;
            }
           	
        	// Verificando se o som é um som valido
        	if (!EnumUtils.isValidEnum(Sound.class, args[0].toUpperCase())) {
        		s.sendMessage(Mensagens.Som_Invalido);
        		return false;
        	}
        	
        	// Executando o som para o player
        	p.playSound(p.getLocation(), Sound.valueOf(args[0].toUpperCase()), 1, 1);
        	s.sendMessage(Mensagens.Som_Executado_Player.replace("%som%", args[0]).replace("%player%", args[1]));
		}
		return false;
	}
}

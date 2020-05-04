package rush.comandos;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rush.Main;
import rush.apis.OnlinePlayersAPI;
import rush.configuracoes.Mensagens;
import rush.utils.Utils;

@SuppressWarnings("all")
public class ComandoExecutarSom implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
            
		// Verificando se o player digitou o número minimo de argumentos
		if (args.length != 2) {
			s.sendMessage(Mensagens.ExecutarSom_Comando_Incorreto);
			return true;
		}
            
		// Verificando se o player quer mandar um som para todo o servidor
		if (args[1].equalsIgnoreCase("all") || args[1].equalsIgnoreCase("@a") || args[1].equals("*")) {
			
			// Verificando se o som é um som valido
			if (!Utils.isValidEnum(Sound.class, args[0].toUpperCase())) {
				s.sendMessage(Mensagens.Som_Invalido);
				return true;
			}
            
			// Executando o som para todos os players
			Player[] players = OnlinePlayersAPI.getOnlinePlayers();
			for (Player p : players) {
				p.playSound(p.getLocation(), Sound.valueOf(args[0].toUpperCase()), 1, 1);
			}
			
			// Informando o sender // sucesso
			s.sendMessage(Mensagens.Som_Executado_Todos.replace("%som%", args[0]));
			return true;
		}
    	    
		// Pegando o player e verificando se ele esta online
		Player p = Bukkit.getPlayer(args[1]);
		if (p == null) {
			s.sendMessage(Mensagens.Player_Offline);
			return true;
		}
           	
		// Verificando se o som é um som valido
		if (!Utils.isValidEnum(Sound.class, args[0].toUpperCase())) {
			s.sendMessage(Mensagens.Som_Invalido);
			return true;
		}
        	
		// Executando o som para o player
		p.playSound(p.getLocation(), Sound.valueOf(args[0].toUpperCase()), 1, 1);
		s.sendMessage(Mensagens.Som_Executado_Player.replace("%som%", args[0]).replace("%player%", p.getName()));
		return true;
	}
	
}
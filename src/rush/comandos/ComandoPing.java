package rush.comandos;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rush.configuracoes.Mensagens;

public class ComandoPing implements CommandExecutor {
	
	@SuppressWarnings("deprecation")	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("ping")) {
 	    	 
			// Verificando se o sender é um player
			if (!(s instanceof Player)) {
				s.sendMessage(Mensagens.Console_Nao_Pode); 
				return false;
			}
		
			// Verificando se o player digitou o número de argumentos corretos
			if (args.length > 1) {
				s.sendMessage(Mensagens.Ping_Comando_Incorreto);
				return false;
			}
 	     
			try {
 	     		
 	     		// Caso o número de argumentos for 0 então pegaremos o ping do sender
 	     		if (args.length == 0) {
	 	     		Player player = (Player)s;
	 	     		Method player_getHandle = player.getClass().getMethod("getHandle");
		     		Object player_MC = player_getHandle.invoke(player, (Object[])null);
		     		Field player_ping = player_MC.getClass().getField("ping");
		     		String ping = String.valueOf(player_ping.get(player_MC));
		     		s.sendMessage(Mensagens.Seu_Ping.replace("%ping%", ping));
		     		return false;
 	     		}
 	     		
 	     		// Caso o número de argumentos for 1 então pegaremos o ping do player especificado
 	     		if (args.length == 1) {
 	     			
 	     			// Pegando o players e verificando se ele esta online
 	     			Player player = Bukkit.getPlayer(args[0]);
 	 	     		if (player == null) {
 	 	     			s.sendMessage(Mensagens.Player_Offline);
 	 	     			return false;
 	 	     		}
 	 	     		
 	 	     		Method player_getHandle = player.getClass().getMethod("getHandle");
 	 	     		Object player_MC = player_getHandle.invoke(player, (Object[])null);
 	 	     		Field player_ping = player_MC.getClass().getField("ping");
 	 	     		String ping = String.valueOf(player_ping.get(player_MC));
 	 	     		s.sendMessage(Mensagens.Player_Ping.replace("%ping%", ping).replace("%player%", player.getName()));
 	 	     		return false;
 	     		}
 	     	} catch (Exception e) {
 	     		e.printStackTrace();
 	     	}
		}
		return false;
	}
}

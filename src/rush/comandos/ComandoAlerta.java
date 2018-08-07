package rush.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rush.configuracoes.Mensagens;

public class ComandoAlerta implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("alerta")) {	
			
			// Verificando se o player digitou uma mensagem
			if (args.length < 1) { 
				s.sendMessage(Mensagens.Alerta_Comando_Incorreto);
				return false;
			}

			// Obtendo a mensagem digitada
			String alerta = "";
			for (int i = 0; i < args.length; i++) {	
				alerta += args[i] + " ";
			}
			
			// Colocando cores na mensagem digitada
			alerta = alerta.replaceAll("&", "§");
			
			// Enviando a mensagem digita
		    for (Player todos : Bukkit.getOnlinePlayers()) {	
		    	todos.sendTitle(
    			Mensagens.Alerta_Title.replace("%alerta%", alerta).replace("%player%", s.getName()),
    			Mensagens.Alerta_SubTitle.replace("%alerta%", alerta).replace("%player%", s.getName()));
    			todos.sendMessage("");
    			todos.sendMessage(Mensagens.Alerta_Chat.replace("%alerta%", alerta).replace("%player%", s.getName()));
    			todos.sendMessage("");
	    	}
		}
		return false;
	}
}
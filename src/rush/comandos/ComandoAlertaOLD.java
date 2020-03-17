package rush.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import rush.configuracoes.Mensagens;

public class ComandoAlertaOLD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {		
		
		// Verificando se o player digitou uma mensagem
		if (args.length < 1) { 
			s.sendMessage(Mensagens.Alerta_Comando_Incorreto);
			return true;
		}

		// Obtendo a mensagem digitada
		String alerta = String.join(" ", args).replace('&', '§');
			
		// Enviando a mensagem digita
		Bukkit.broadcastMessage(Mensagens.Alerta_Chat.replace("%alerta%", alerta).replace("%player%", s.getName()));
		return true;
	}
}
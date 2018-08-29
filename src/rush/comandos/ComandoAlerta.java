package rush.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rush.apis.TitleAPI;
import rush.configuracoes.Mensagens;

public class ComandoAlerta implements CommandExecutor {

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
		for (Player p : Bukkit.getOnlinePlayers()) {	
			TitleAPI.sendTitle(p, 20, 60, 20,
			Mensagens.Alerta_Title.replace("%alerta%", alerta).replace("%player%", s.getName()),
    		Mensagens.Alerta_SubTitle.replace("%alerta%", alerta).replace("%player%", s.getName()));
    		p.sendMessage("");
    		p.sendMessage(Mensagens.Alerta_Chat.replace("%alerta%", alerta).replace("%player%", s.getName()));
    		p.sendMessage("");
		}
		return true;
	}
}
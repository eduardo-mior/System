package rush.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rush.apis.OnlinePlayersAPI;
import rush.configuracoes.Mensagens;

@SuppressWarnings("all")
public class ComandoSudo implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {

		// Verificando se o player informou menos de 2 argumentos
		if (args.length < 2) {
			s.sendMessage(Mensagens.Sudo_Comando_Incorreto);
			return true;
		}
		
		// Pegando o comando que sera executado
		String comando = "";
		for (int i = 1; i < args.length; i++) {comando += args[i] + " ";}
		
		// Verificando se ele quer executar o comando para todos
		if (args[0].equalsIgnoreCase("all") || args[0].equalsIgnoreCase("@a") || args[0].equals("*")) {
			for (Player p : OnlinePlayersAPI.getOnlinePlayers()) {
				p.chat(comando);
			}
			s.sendMessage(Mensagens.Sudo_Executado_Com_Sucesso_Para_Todos.replace("%comando%", comando));
			return true;
		}

		// Pegando o player e verificando se ele esta online
		Player p = Bukkit.getPlayer(args[0]);
		if (p == null) {
			s.sendMessage(Mensagens.Player_Offline);
			return true;
		}

		// Executando para o player especificado
		p.chat(comando);
		s.sendMessage(Mensagens.Sudo_Executado_Com_Sucesso.replace("%player%", p.getName()).replace("%comando%", comando));
		return true;
	}
}
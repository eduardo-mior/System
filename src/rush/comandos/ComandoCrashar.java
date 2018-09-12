package rush.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rush.apis.CrashAPI;
import rush.configuracoes.Mensagens;

@SuppressWarnings("all")
public class ComandoCrashar implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		
		// Verificando se o player digitou o número de argumentos corretos
		if (args.length != 1) {
			s.sendMessage(Mensagens.Crashar_Comando_Incorreto);
			return true;
		}
			
		// Pegando o player e verificando se ele esta online
		Player p = Bukkit.getPlayer(args[0]);
		if (p == null) {
			s.sendMessage(Mensagens.Player_Offline);
			return true;
		}
		
		// Chamando o metodo que crasha o player e enviando a mensagem.
		CrashAPI.crashPlayer(p);
		s.sendMessage(Mensagens.Crashado_Com_Sucesso.replace("%player%", p.getName()).replace('&', '§'));
		return true;
	}
}
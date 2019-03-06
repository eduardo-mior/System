package rush.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rush.apis.PingAPI;
import rush.configuracoes.Mensagens;

@SuppressWarnings("all")
public class ComandoPing implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {

		// Verificando se o sender é um player
		if (!(s instanceof Player)) {
			s.sendMessage(Mensagens.Console_Nao_Pode);
			return true;
		}

		// Verificando se o player digitou o número de argumentos corretos
		if (args.length > 1) {
			s.sendMessage(Mensagens.Ping_Comando_Incorreto);
			return true;
		}

		// Caso o número de argumentos for 0 então pegaremos o ping do sender
		if (args.length == 0) {
			Player p = (Player) s;
			String ping = PingAPI.getPlayerPing(p);
			s.sendMessage(Mensagens.Seu_Ping.replace("%ping%", ping));
			return true;
		}

		// Caso o número de argumentos for 1 então pegaremos o ping do player especificado
		if (args.length == 1) {

			// Pegando o player e verificando se ele esta online
			Player p = Bukkit.getPlayer(args[0]);
			if (p == null) {
				s.sendMessage(Mensagens.Player_Offline);
				return true;
			}

			// Pegando o ping do player e informando
			String ping = PingAPI.getPlayerPing(p);
			s.sendMessage(Mensagens.Player_Ping.replace("%ping%", ping).replace("%player%", p.getName()));
			return true;
		}
		return true;
	}
}
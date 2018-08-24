package rush.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rush.configuracoes.Mensagens;

@SuppressWarnings("all")
public class ComandoTphere implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {

		// Verificando se o sender é um player
		if (!(s instanceof Player)) {
			s.sendMessage(Mensagens.Console_Nao_Pode);
			return true;
		}

		// Verificando se o player digitou o número de argumentos corretos
		if (args.length != 1) {
			s.sendMessage(Mensagens.Tphere_Comando_Incorreto);
			return true;
		}

		// Pegando o player e verificando se ele esta online
		Player target = Bukkit.getPlayer(args[0]);
		if (target == null) {
			s.sendMessage(Mensagens.Player_Offline);
			return true;
		}

		if (target.getName().equals(s.getName())) {
			s.sendMessage(Mensagens.Tp_Erro_Voce_Mesmo);
			return true;
		}

		// Teleportando o player até o alvo e informando
		Player p = (Player) s;
		target.teleport(p);
		s.sendMessage(Mensagens.Tphere_Puxou_Com_Sucesso.replace("%player%", target.getName()));
		target.sendMessage(Mensagens.Tphere_Puxado_Com_Sucesso.replace("%player%", s.getName()));
		return true;
	}
}
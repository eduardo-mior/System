package rush.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rush.configuracoes.Mensagens;

public class ComandoTphere implements CommandExecutor {
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("tphere")) {

			// Verificando se o sender é um player
			if (!(s instanceof Player)) {
				s.sendMessage(Mensagens.Console_Nao_Pode);
				return false;
			}
			
			// Verificando se o player digitou o número de argumentos corretos
			if (args.length != 1) {
				s.sendMessage(Mensagens.Tphere_Comando_Incorreto);
				return false;
			}

			// Pegando o player e verificando se ele esta online
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				s.sendMessage(Mensagens.Player_Offline);
				return false;
			}
				
			if (target.getName().equals(s.getName())) {
				s.sendMessage(Mensagens.Tp_Erro_Voce_Mesmo);
					return false;
			}

			// Teleportando o player até o alvo e informando
			Player p = (Player) s;
			target.teleport(p);
			s.sendMessage(Mensagens.Tphere_Puxou_Com_Sucesso.replace("%player%", args[0]));
			target.sendMessage(Mensagens.Tphere_Puxado_Com_Sucesso.replace("%player%", s.getName()));
		}
		return false;
	}
}
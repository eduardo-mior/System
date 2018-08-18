package rush.comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rush.configuracoes.Mensagens;

@SuppressWarnings("all")
public class ComandoHeal implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("heal")) {

			// Verificando se o sender é um player
			if (!(s instanceof Player)) {
				s.sendMessage(Mensagens.Console_Nao_Pode);
				return true;
			}

			// Pegando o player e verificando se ele ja esta com a vida cheia
			Player p = (Player) s;
			if (p.getHealth() >= 20) {
				p.sendMessage(Mensagens.Vida_Level_Maximo);
				return true;
			}
			
			// Regerando a vida do player e informando
			p.sendMessage(Mensagens.Vida_Regenerada_Com_Sucesso);
			p.setHealth(20);
			return true;
		}
		return false;
	}
}
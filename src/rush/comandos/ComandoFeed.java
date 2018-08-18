package rush.comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rush.configuracoes.Mensagens;

public class ComandoFeed implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("feed")) {

			// Verificando se o sender é um player
			if (!(s instanceof Player)) {
				s.sendMessage(Mensagens.Console_Nao_Pode);
				return true;
			}

			// Pegando o player e verificando se ele ja esta com a fome cheia
			Player p = (Player) s;
			if (p.getFoodLevel() >= 20) {
				p.sendMessage(Mensagens.Fome_Level_Maximo);
				return true;
			}
			
			// Regerando a fome do player e informando
			p.sendMessage(Mensagens.Fome_Regenerada_Com_Sucesso);
			p.setFoodLevel(20);
			return true;
		}
		return false;
	}
}
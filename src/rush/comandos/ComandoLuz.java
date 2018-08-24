package rush.comandos;

import java.util.HashSet;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import rush.configuracoes.Mensagens;

public class ComandoLuz implements CommandExecutor {

	private HashSet<Player> luz = new HashSet<>();

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {

		// Verificando se o sender é um player
		if (!(s instanceof Player)) {
			s.sendMessage(Mensagens.Console_Nao_Pode);
			return true;
		}

		// Pegando o player e verificando se ele já esta com a luz ativada
		Player p = (Player) s;
		if (luz.contains(p)) {
			luz.remove(p);
			p.removePotionEffect(PotionEffectType.NIGHT_VISION);
			p.sendMessage(Mensagens.Luz_Desativada);
		} else {
			luz.add(p);
			p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 99999, 5, true));
			p.sendMessage(Mensagens.Luz_Ativada);
		}
		return true;

	}
}

package rush.comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import rush.configuracoes.Mensagens;
import rush.utils.Utils;

public class ComandoLuz implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {

		// Verificando se o sender é um player
		if (!(s instanceof Player)) {
			s.sendMessage(Mensagens.Console_Nao_Pode);
			return true;
		}

		// Pegando o player e verificando se ele já esta com a luz ativada
		Player p = (Player) s;
		if (Utils.hasPotionEffect(p, PotionEffectType.NIGHT_VISION, 50)) {
			p.removePotionEffect(PotionEffectType.NIGHT_VISION);
			p.sendMessage(Mensagens.Luz_Desativada);
		} else {
			p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 50), true);
			p.sendMessage(Mensagens.Luz_Ativada);
		}
		return true;
	}
	
}
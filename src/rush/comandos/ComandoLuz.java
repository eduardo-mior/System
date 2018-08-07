package rush.comandos;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import rush.configuracoes.Mensagens;

public class ComandoLuz implements CommandExecutor {
	
	private List<String> luz = new ArrayList<>();
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("luz")) {
			
			// Verificando se o sender é um player
			if (!(s instanceof Player)) {
				s.sendMessage(Mensagens.Console_Nao_Pode); 
				return false;
			}
	       
			// Pegando o player e verificando se ele já esta com a luz ativada
			Player p = (Player)s;
			if (luz.contains(p.getName())) {
				luz.remove(p.getName());
				p.removePotionEffect(PotionEffectType.NIGHT_VISION);
				p.sendMessage(Mensagens.Luz_Desativada);
			} else {
				luz.add(p.getName());
				p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 99999, 5, true));
				p.sendMessage(Mensagens.Luz_Ativada);	
			}
		}
		return false;
	}
}

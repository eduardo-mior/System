package rush.comandos;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import rush.configuracoes.Mensagens;

@SuppressWarnings("all")
public class ComandoChapeu implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("chapeu")) {

			// Verificando se o sender é um player
			if (!(s instanceof Player)) {
				s.sendMessage(Mensagens.Console_Nao_Pode);
				return true;
			}

			// Pegando o player, o item na mão e o seu capacete atual
			Player p = (Player) s;
			PlayerInventory i = p.getInventory();
			ItemStack hand = p.getItemInHand();
			ItemStack helmet = i.getHelmet();

			// Verificando se o item é um capacete valido
			if (hand.getType() != Material.AIR && hand.getType().getMaxDurability() == 0) {
				i.setHelmet(hand);
				i.setItemInHand(helmet);
				s.sendMessage(Mensagens.Chapeu_Colocado);
			} else {
				s.sendMessage(Mensagens.Chapeu_Invalido);
			}
			return true;
		}
		return false;
	}
}
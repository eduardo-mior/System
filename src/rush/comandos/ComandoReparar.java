package rush.comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import rush.configuracoes.Mensagens;

@SuppressWarnings("all")
public class ComandoReparar implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {

		// Verificando se o sender é um player
		if (!(s instanceof Player)) {
			s.sendMessage(Mensagens.Console_Nao_Pode);
			return true;
		}

		// Verificando se o player digitou o número de argumentos corretos
		if (args.length > 1) {
			s.sendMessage(Mensagens.Reparar_Comando_Incorreto);
			return true;
		}

		// Pegando o player
		Player p = (Player) s;
		
		// Caso o número de argumentos for 1 então 
		if (args.length == 1) {
			
			// Caso o argumento não seja all ou hand então é dado como comando invalido
			if (!args[0].equalsIgnoreCase("all") && !args[0].equalsIgnoreCase("hand")) {
				s.sendMessage(Mensagens.Reparar_Comando_Incorreto);
				return true;
			}
			
			// Caso o argumento seja 'all' então todos os itens são reparados
			if (args[0].equalsIgnoreCase("all")) {
				
				// Verificando se o player tem permissão para reparar tudo de uma vez
				if (!s.hasPermission("system.reparar.all")) {
					s.sendMessage(Mensagens.Reparar_All_Sem_Permissao);
					return true;
				}
				
				boolean reparado = false;
				for (ItemStack i : p.getInventory().getContents()) {
					if (i != null && i.getType().getMaxDurability() != 0 && i.getDurability() != 0) {
				        i.setDurability((short)0);
						reparado = true;
					}
				}
				for (ItemStack i : p.getInventory().getArmorContents()) {
					if (i != null && i.getType().getMaxDurability() != 0 && i.getDurability() != 0) {
				        i.setDurability((short)0);
				        reparado = true;
					}
				}
				if (reparado) {
					p.sendMessage(Mensagens.Reparar_All_Com_Sucesso);
					p.updateInventory();
					return true;
				} else {
					p.sendMessage(Mensagens.Reparar_Nao_Possui);
					return true;
				}
			}
		}
		
		/** Caso o argumento sejá 'hand' ou caso não haja argumento o item da mão é repadado*/
		
		// Verificando se o item pode ser reparado
		ItemStack hand = p.getItemInHand();
		if (hand == null || hand.getType().getMaxDurability() == 0) {
			s.sendMessage(Mensagens.Reparar_Item_Invalido);
			return true;
		}
		
		// Verificando se o item já esta reparado
		if (hand.getDurability() == 0) {
			s.sendMessage(Mensagens.Reparar_Ja_Reparado);
			return true;
		}
		
		// Reparando o item e informando
        hand.setDurability((short)0);
		s.sendMessage(Mensagens.Reparar_Hand_Com_Sucesso);
		return true;
	}
}
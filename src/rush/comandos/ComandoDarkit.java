package rush.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import rush.configuracoes.Mensagens;
import rush.entidades.Kit;
import rush.entidades.Kits;

@SuppressWarnings("all")
public class ComandoDarkit implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
			
		// Verificando se o player digitou o número de argumentos corretos
		if (args.length != 2) {
			s.sendMessage(Mensagens.DarKit_Comando_Incorreto);
			return true;
		}
			
		// Pegando o argumento e verificando se o kit existe
		String id = args[0].toLowerCase();
		if (!Kits.contains(id)) {
			s.sendMessage(Mensagens.Kit_Nao_Existe.replace("%kit-id%", id));
			ComandoKits.ListKits(s);
			return true;
		}

		// Pegando o player e verificando se ele esta online
		Player p = Bukkit.getPlayer(args[1]);
		if (p == null) {
			s.sendMessage(Mensagens.Player_Offline);
			return true;
		}
		
		// Pegando o kit e adicionando para o player
		Kit kit = Kits.get(id);
		ItemStack[] ITENS = kit.getItens();
		forceAddItensToInventory(p, ITENS);
		s.sendMessage(Mensagens.Kit_Enviado.replace("%player%", p.getName()).replace("%kit-id%", id).replace("%kit-nome%", kit.getNome()));
		return true;			
	}
	
	// Método para adicionar os itens no inv do player
	private void forceAddItensToInventory(Player p, ItemStack[] itens) {
		PlayerInventory inv = p.getInventory();
		for (ItemStack item : itens) {
			if (item != null) {
				if (inv.firstEmpty() != -1) inv.addItem(item.clone());
				else p.getWorld().dropItem(p.getLocation(), item);
			}
		}
	}
}
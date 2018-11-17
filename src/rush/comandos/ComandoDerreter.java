package rush.comandos;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import rush.configuracoes.Mensagens;

public class ComandoDerreter implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
			
		// Verificando se o sender é um player
		if (!(s instanceof Player)) {
			s.sendMessage(Mensagens.Console_Nao_Pode);
			return true;
		}
			
		// Pegando o player e o inventario
		Player p = (Player)s;
		PlayerInventory i = p.getInventory();
			
		// Verificando se o player possui itens para derreter
		if (!possuiItensParaDerreter(i)) {
			s.sendMessage(Mensagens.Derreter_Nao_Possui);
			return true;
		}
			
		// Chamando o método que derrete os itens e enviando mensagem
		int derretidos = derreterItens(i);
		s.sendMessage(Mensagens.Derreter_Com_Sucesso.replace("%quantia%", String.valueOf(derretidos)));
		return true;
	}
	
	// Método para verificar se o player possui itens para derreter
	private boolean possuiItensParaDerreter(PlayerInventory i) {
		if( i.contains(Material.IRON_ORE) ||
		    i.contains(Material.GOLD_ORE) ||
		    i.contains(Material.DIAMOND_ORE) ||
		    i.contains(Material.LAPIS_ORE)||
		    i.contains(Material.EMERALD_ORE) ||
		    i.contains(Material.REDSTONE_ORE) ||
		    i.contains(Material.COAL_ORE))
		    return true;
		    else return false;
	}
	
	// Método para derreter os itens
	private int derreterItens(PlayerInventory inv) {
		int derretidos = 0;
		for (ItemStack item : inv.getContents()) {
			
			// Verificando se o item é valido
			if (item == null || item.getType().equals(Material.AIR)) continue;
			
			// Derrentendo o item
			try {
				Blocks block = Blocks.valueOf(item.getType().name());
				int quantidade = item.getAmount();
				ItemStack ore = block.gerOre() != Material.INK_SACK ?
								new ItemStack(block.gerOre(), quantidade) :
								new ItemStack(Material.INK_SACK, quantidade, (byte) 4);
				inv.remove(item);
				inv.addItem(ore);
				
				// Adicionando a quantia de itens derretidos para exibir na mensagem
				derretidos += quantidade;
			} catch (Exception e) {
				continue;
			}
		}
		
		// Retorna a quantia de itens derretidos para exibir na mensagem
		return derretidos;
	}
}

/**
 * Powered by kickpost;
 */

enum Blocks {
	
	IRON_ORE(Material.IRON_INGOT), 
	GOLD_ORE(Material.GOLD_INGOT), 
	DIAMOND_ORE(Material.DIAMOND), 
	EMERALD_ORE(Material.EMERALD), 
	LAPIS_ORE(Material.INK_SACK),
	REDSTONE_ORE(Material.REDSTONE), 
	COAL_ORE(Material.COAL);

	private Material item;

	private Blocks(Material item) {
		this.item = item;
	}

	public Material gerOre() {
		return item;
	}
}
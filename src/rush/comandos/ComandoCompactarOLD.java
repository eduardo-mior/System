package rush.comandos;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import rush.configuracoes.Mensagens;

public class ComandoCompactarOLD implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
			
		// Verificando se o sender é um player
		if (!(s instanceof Player)) {
			s.sendMessage(Mensagens.Console_Nao_Pode);
			return true;
		}

		// Pegando o player, o inventario e os itens do inventario
		Player p = (Player) s;
			
		// Chamando o método que compacta os itens e enviando mensagem
		int compactados = compactarItens(p.getInventory().getContents(), p.getInventory(), p);
		
		// Verificando se o player compactou algum item
		if (compactados == 0) {
			p.sendMessage(Mensagens.Compactar_Nao_Possui);
			return true;
		}
		
		// Recompactandos os itens pois podem haver sobras no inventario e informado o player
		compactados += compactarItens(p.getInventory().getContents(), p.getInventory(), p);
		p.sendMessage(Mensagens.Compactar_Com_Sucesso.replace("%quantia%", String.valueOf(compactados)));
		return true;
	}
	
	// Método para compactar os itens
	private int compactarItens(ItemStack[] itens, PlayerInventory inv, Player p) {
		int compactados = 0;
		List<ItemStack> devolver = new ArrayList<>();
		
		for (ItemStack item : itens) {
			
			// Verificando se o item é valido
			if (item == null || item.getType() == Material.AIR) continue;
			
			// Verificando se o item possui a quantidade minima para ser compactado
			// Verificando se o item possui ItemMeta (nome ou lore)
			if (item.getAmount() < 9 || item.hasItemMeta()) continue;
			
			// Verificando se o item é um corante e esse corante não é o LapidAzul
			if (item.getType() == Material.INK_SACK && item.getDurability() != 4) continue;
			
			// Compactando o item
			try {
				OldOres ores = OldOres.valueOf(item.getType().name());
				int quantidade = item.getAmount();
				int give = (int) quantidade / 9;
				int resto = quantidade - (give * 9);
				ItemStack block = new ItemStack(ores.getBlock(), give);
				inv.remove(item);
				inv.addItem(block);
				
				// Verificando se o item não pode ser compactado por completo
				if (resto > 0) {
					devolver.add(new ItemStack(item.getType(), resto, item.getDurability()));
				}
				
				// Adicionando a quantia de itens compactados para exibir na mensagem
				compactados += give * 9;
			} catch (Throwable e) {
				continue;
			}
		}
		
		// Devolvendo as sobras
		for (ItemStack item : devolver) {
			
			/* Verificando se o player possui espaço no inventario para armazenar o item,
			   caso ele não possuir espaço no inventarios o item sera dropado */
			for (ItemStack rest : inv.addItem(item).values()) {
				p.getWorld().dropItem(p.getLocation(), rest);
			}
		}
		
		// Retorna a quantia de itens compactados para exibir na mensagem
		return compactados;
	}
}
	
/**
 * Powered by kickpost;
 */

enum OldOres {
	
	IRON_INGOT(Material.IRON_BLOCK), 
	GOLD_INGOT(Material.GOLD_BLOCK), 
	DIAMOND(Material.DIAMOND_BLOCK), 
	EMERALD(Material.EMERALD_BLOCK), 
	INK_SACK(Material.LAPIS_BLOCK), 
	GOLD_NUGGET(Material.GOLD_INGOT),
	MELON(Material.MELON_BLOCK),
	REDSTONE(Material.REDSTONE_BLOCK);
	
	private Material item;
	
	private OldOres(Material item) {
		this.item = item;
	}
	
	public Material getBlock() {
		return item;
	}
}
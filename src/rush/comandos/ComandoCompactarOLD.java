package rush.comandos;

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
		if (cmd.getName().equalsIgnoreCase("compactar")) {
			
			// Verificando se o sender é um player
			if (!(s instanceof Player)) {
			    s.sendMessage(Mensagens.Console_Nao_Pode);
				return true;
			}

			// Pegando o player, o inventario e os itens do inventario
			Player p = (Player)s;
			PlayerInventory i = p.getInventory();
			ItemStack[] itens = i.getContents();
			
			// Verificando se o player possui itens para compactar
			if (!possuiItensParaCompactar(i)) {
		        s.sendMessage(Mensagens.Compactar_Nao_Possui);
				return true;
			}
			
			// Chamando o método que compacta os itens e enviando mensagem
			int compactados = compactarItens(itens, i, p);
		    s.sendMessage(Mensagens.Compactar_Com_Sucesso.replace("%quantia%", String.valueOf(compactados)));
			return true;
		}
		return false;
	}
	
	// Método para verificar se o player possui itens para compactar
	private boolean possuiItensParaCompactar(PlayerInventory i) {
		if(	i.contains(Material.IRON_INGOT) ||
			i.contains(Material.GOLD_INGOT) ||
		    i.contains(Material.REDSTONE) ||
		    i.contains(Material.COAL) ||
		    i.contains(Material.INK_SACK) ||
		    i.contains(Material.SLIME_BALL) ||
		    i.contains(Material.GOLD_NUGGET) ||
		    i.contains(Material.DIAMOND) ||
		    i.contains(Material.EMERALD) ||
		    i.contains(Material.MELON))
			return true;
			else return false;
	}
	
	// Método para compactar os itens
	private int compactarItens(ItemStack[] itens, PlayerInventory inv, Player p) {
		int compactados = 0;
		for (ItemStack item : itens) {
			
			// Verificando se o item é valido
			if(item == null || item.getType() == Material.AIR) continue;
			
			// Verificando se o item possui a quantidade minima para ser compactado
			if(item.getAmount() < 9) continue;
			
			// Verificando se o item é um corante e esse corante não é o LapidAzul
			if(item.getType() == Material.INK_SACK && item.getDurability() != 4) continue;
			
			// Compactando o item
			try {
				OldOres ores = OldOres.valueOf(item.getType().name());
				int quantidade = item.getAmount();
				int give = (int) Math.ceil(quantidade / 9);
				int resto = quantidade - (give * 9);
				ItemStack block = new ItemStack(ores.getBlock(), give);
				inv.remove(item);
				inv.addItem(block);
				
				// Verificando se o item não pode ser compactado por completo
				if(resto > 0) {
					ItemStack RestoOre = item.clone();
					RestoOre.setAmount(resto);
					
					/* Verificando se o player possui espaço no inventario para armazenar o item,
					   caso ele não possuir espaço no inventarios o item sera dropado */
					if (inv.firstEmpty() != -1) inv.addItem(RestoOre);
					else p.getWorld().dropItem(p.getLocation(), RestoOre);
				}
				
				// Adicionando a quantia de itens compactados para exibir na mensagem
				compactados += quantidade;
			} catch (Exception e) {
				continue;
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
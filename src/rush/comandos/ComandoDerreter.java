package rush.comandos;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import rush.utils.ConfigManager;

public class ComandoDerreter implements Listener, CommandExecutor {
	
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("derreter")) {
			
			if (!(s instanceof Player)) {
				s.sendMessage(ConfigManager.getConfig("mensagens").getString("Console-Nao-Pode").replace("&", "§"));
				return false; 
			}
			
			Player p = (Player)s;
			PlayerInventory i = p.getInventory();
			ItemStack[] itens = i.getContents();
			if (verificarItens(i)) {
		        s.sendMessage(ConfigManager.getConfig("mensagens").getString("Derreter-Nao-Possui").replace("&", "§"));
				return false;
			}
			
			int derretidos = derreterItens(itens, i);
		    s.sendMessage(ConfigManager.getConfig("mensagens").getString("Derreter-Com-Sucesso").replace("&", "§").replace("%quantia%", String.valueOf(derretidos)));
		}
		return false;
	}
	
	private boolean verificarItens(PlayerInventory i) {
		if(!i.contains(Material.IRON_ORE) &&
		   !i.contains(Material.GOLD_ORE) &&
		   !i.contains(Material.DIAMOND_ORE) &&
		   !i.contains(Material.LAPIS_ORE) &&
		   !i.contains(Material.EMERALD_ORE) &&
		   !i.contains(Material.REDSTONE_ORE) &&
		   !i.contains(Material.COAL_ORE))
		   return true;
		   else return false;
	}
	
	private int derreterItens(ItemStack[] itens, PlayerInventory inv) {
		int derretidos = 0;
		for (ItemStack item : itens) {
			if(item == null || item.getType().equals(Material.AIR)) continue;
			try {
				Blocks block = Blocks.valueOf(item.getType().name());
				int quantidade = item.getAmount();
				ItemStack ore = block.gerOre().equals(Material.INK_SACK) ? 
						new ItemStack(Material.INK_SACK, quantidade, (byte) 4): 
						new ItemStack(block.gerOre(), quantidade);
				inv.addItem(ore);
				inv.remove(item);
				derretidos += quantidade;

			} catch (Exception e) {
				continue;
			}
		}
		return derretidos;
	}
}

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
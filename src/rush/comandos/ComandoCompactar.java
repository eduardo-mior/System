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

public class ComandoCompactar implements Listener, CommandExecutor {
	
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("compactar")) {
			
			if (!(s instanceof Player)) {
				s.sendMessage(ConfigManager.getConfig("mensagens").getString("Console-Nao-Pode").replace("&", "§"));
				return false; 
			}

			Player p = (Player)s;
			PlayerInventory i = p.getInventory();
			ItemStack[] itens = i.getContents();
			if (verificarItens(i)) {
		        s.sendMessage(ConfigManager.getConfig("mensagens").getString("Compactar-Nao-Possui").replace("&", "§"));
				return false;
			}
			
			int compactados = compactarItens(itens, i, p);
		    s.sendMessage(ConfigManager.getConfig("mensagens").getString("Compactar-Com-Sucesso").replace("&", "§").replace("%quantia%", String.valueOf(compactados)));
			
		}
		return false;
	}
	
	private boolean verificarItens(PlayerInventory i) {
		if(!i.contains(Material.IRON_INGOT) &&
		   !i.contains(Material.GOLD_INGOT) &&
		   !i.contains(Material.DIAMOND) &&
		   !i.contains(Material.INK_SACK, (byte) 4) &&
		   !i.contains(Material.EMERALD) &&
		   !i.contains(Material.REDSTONE) &&
		   !i.contains(Material.COAL) &&
		   !i.contains(Material.SLIME_BALL) &&
		   !i.contains(Material.GOLD_NUGGET) &&
		   !i.contains(Material.MELON))
		   return true;
		   else return false;
	}
	
	public int compactarItens(ItemStack[] itens, PlayerInventory inv, Player p) {
		int compactados = 0;
		for (ItemStack item : itens) {
			if(item == null || item.getType().equals(Material.AIR)) continue;
			if(item.getAmount() < 9) continue;
			if(item.getType().equals(Material.INK_SACK) && item.getDurability() != 4) continue;
			try {
				Ores ores = Ores.valueOf(item.getType().name());
				int quantidade = item.getAmount();
				int give = (int) Math.ceil(quantidade/9);
				int resto = quantidade - (give*9);
				ItemStack block = new ItemStack(ores.getBlock(), give);
				inv.remove(item);
				inv.addItem(block);
				if(resto > 0) {
					ItemStack RestoOre = item.clone();
					RestoOre.setAmount(resto);
					if (inv.firstEmpty() != -1) inv.addItem(RestoOre);
					else p.getWorld().dropItem(p.getLocation(),RestoOre);
				}
				compactados += quantidade;

			} catch (Exception e) {
				continue;
			}
		}
		return compactados;
	}
}
	
enum Ores {
	COAL(Material.COAL_BLOCK),
	IRON_INGOT(Material.IRON_BLOCK), 
	GOLD_INGOT(Material.GOLD_BLOCK), 
	DIAMOND(Material.DIAMOND_BLOCK), 
	EMERALD(Material.EMERALD_BLOCK), 
	INK_SACK(Material.LAPIS_BLOCK), 
	GOLD_NUGGET(Material.GOLD_INGOT),
	MELON(Material.MELON_BLOCK),
	REDSTONE(Material.REDSTONE_BLOCK),
	SLIME_BALL(Material.SLIME_BLOCK);
	
	private Material item;
	
	private Ores(Material item) {
		this.item = item;
	}
	
	public Material getBlock() {
		return item;
	}
}
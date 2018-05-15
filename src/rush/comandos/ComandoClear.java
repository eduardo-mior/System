package rush.comandos;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import rush.utils.ConfigManager;

public class ComandoClear implements Listener, CommandExecutor {
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("clear")) {
			if (!(s instanceof Player)) {
				
				if(args.length != 1) {
					s.sendMessage(ConfigManager.getConfig("mensagens").getString("Clear-Comando-Incorreto").replace("&", "§"));
		    		return false;
				}
				
				Player p = Bukkit.getPlayer(args[0]);
				if (p == null) {
					s.sendMessage(ConfigManager.getConfig("mensagens").getString("Player-Offline").replace("&", "§"));
					return false;
				}
				
				if (isEmpty(p)) {
					s.sendMessage(ConfigManager.getConfig("mensagens").getString("Clear-Inventario-Vazio-Outro").replace("&", "§").replace("%player%", p.getName()));
					return false;
				}
				
				clearInventory(p);
				s.sendMessage(ConfigManager.getConfig("mensagens").getString("Clear-Inventario-Limpado-Outro").replace("&", "§").replace("%player%", p.getName()));
				return false;
			}
			
			if (args.length > 1) {
				s.sendMessage(ConfigManager.getConfig("mensagens").getString("Clear-Comando-Incorreto").replace("&", "§"));
				return false;
			}
			
			if (args.length == 1) {
	    		if(!s.hasPermission("system.clear.outros")) {
	    			s.sendMessage(ConfigManager.getConfig("mensagens").getString("Clear-Outro-Sem-Permissao").replace("&", "§"));
	    			return false;
	    		}
	    		
				Player p = Bukkit.getPlayer(args[0]);
				if (p == null) {
					s.sendMessage(ConfigManager.getConfig("mensagens").getString("Player-Offline").replace("&", "§"));
					return false;
				}
				
				if (isEmpty(p)) {
					s.sendMessage(ConfigManager.getConfig("mensagens").getString("Clear-Inventario-Vazio-Outro").replace("&", "§").replace("%player%", p.getName()));
					return false;
				}
				
				clearInventory(p);
				s.sendMessage(ConfigManager.getConfig("mensagens").getString("Clear-Inventario-Limpado-Outro").replace("&", "§").replace("%player%", p.getName()));
				return false;
			}
			
			Player p = (Player) s;
			if (isEmpty(p)) {
				s.sendMessage(ConfigManager.getConfig("mensagens").getString("Clear-Inventario-Vazio-Voce").replace("&", "§"));
				return false;
			}
				
			clearInventory(p);
			s.sendMessage(ConfigManager.getConfig("mensagens").getString("Clear-Inventario-Limpado-Voce").replace("&", "§"));
		}
		return false;
	}
	
	public boolean isEmpty(Player p) {
		PlayerInventory inv = p.getInventory();
		for (ItemStack i : inv.getContents()) {
			if(i != null && !(i.getType().equals(Material.AIR))) return false;
		}
		for (ItemStack i : inv.getArmorContents()) {
			if(i != null && !(i.getType().equals(Material.AIR))) return false;
		}
		return true;
	}
	
	public void clearInventory(Player p) {
		PlayerInventory inv = p.getInventory();
		p.setItemOnCursor(null);
		inv.clear();
		inv.setHelmet(null);
		inv.setChestplate(null);
		inv.setLeggings(null);
		inv.setBoots(null);
	}
}
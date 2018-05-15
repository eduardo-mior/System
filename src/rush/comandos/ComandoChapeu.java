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

public class ComandoChapeu implements Listener, CommandExecutor {
	
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("chapeu")) {
			
			if (!(s instanceof Player)) {
				s.sendMessage(ConfigManager.getConfig("mensagens").getString("Console-Nao-Pode").replace("&", "§"));
				return false;
			}
			
			Player p = (Player)s;
			PlayerInventory i = p.getInventory();
			ItemStack hand = p.getItemInHand();
			ItemStack helmet = i.getHelmet();
			if (hand.getType() != Material.AIR && hand.getType().getMaxDurability() == 0) {
				i.setHelmet(hand);
				i.setItemInHand(helmet);
		        s.sendMessage(ConfigManager.getConfig("mensagens").getString("Chapeu-Colocado").replace("&", "§"));
			} else {
		        s.sendMessage(ConfigManager.getConfig("mensagens").getString("Chapeu-Invalido").replace("&", "§"));
			}
		}
		return false;
	}
}
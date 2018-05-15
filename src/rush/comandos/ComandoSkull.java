package rush.comandos;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import rush.utils.ConfigManager;

public class ComandoSkull implements Listener, CommandExecutor {
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("skull")) {
			if (!(s instanceof Player)) {
				
				if (args.length != 2) {
					s.sendMessage(ConfigManager.getConfig("mensagens").getString("Skull-Comando-Incorreto").replace("&", "§"));
					return false;
				}
				
				Player p = Bukkit.getPlayer(args[1]);
				if (p == null) {
					s.sendMessage(ConfigManager.getConfig("mensagens").getString("Player-Offline").replace("&", "§"));
					return false;
				}
				
				ItemStack skull = cabeca(args[0]);
				p.getInventory().addItem(skull);
				s.sendMessage(ConfigManager.getConfig("mensagens").getString("Skull-Enviada-Outro").replace("&", "§").replace("%dono%", args[0]).replace("%player%", p.getName()));
				return false;
			}
			
			if (args.length != 2 && args.length != 1) {
				s.sendMessage(ConfigManager.getConfig("mensagens").getString("Skull-Comando-Incorreto").replace("&", "§"));
				return false;
			}
			
			ItemStack skull = cabeca(args[0]);
			
			if (args.length == 2) {
				Player p = Bukkit.getPlayer(args[1]);
				if (p == null) {
					s.sendMessage(ConfigManager.getConfig("mensagens").getString("Player-Offline").replace("&", "§"));
					return false;
				}

				p.getInventory().addItem(skull);
				s.sendMessage(ConfigManager.getConfig("mensagens").getString("Skull-Enviada-Outro").replace("&", "§").replace("%dono%", args[0]).replace("%player%", p.getName()));
				return false;
			}
			
			Player p = (Player) s;
			p.getInventory().addItem(skull);
			s.sendMessage(ConfigManager.getConfig("mensagens").getString("Skull-Enviada-Voce").replace("&", "§").replace("%dono%", args[0]));
		}
		return false;
	}
	
	private ItemStack cabeca(String dono) {
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
		SkullMeta meta = (SkullMeta)skull.getItemMeta();
		meta.setOwner(dono);
		skull.setItemMeta(meta);
		return skull;
	}
}
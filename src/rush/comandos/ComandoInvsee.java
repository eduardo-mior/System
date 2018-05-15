package rush.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import rush.utils.ConfigManager;

public class ComandoInvsee implements Listener, CommandExecutor {
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("invsee")) {
			if (!(s instanceof Player)) {
				s.sendMessage(ConfigManager.getConfig("mensagens").getString("Console-Nao-Pode").replace("&", "§"));
				return false;
			}
			
			if (args.length != 1) {
				s.sendMessage(ConfigManager.getConfig("mensagens").getString("Invsee-Comando-Incorreto").replace("&", "§"));
				return false;
			}
			
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				s.sendMessage(ConfigManager.getConfig("mensagens").getString("Player-Offline").replace("&", "§"));
				return false;
			}
			
			Player sender = (Player)s;
			sender.openInventory(target.getInventory());
			s.sendMessage(ConfigManager.getConfig("mensagens").getString("Invsee-Abrindo-Inventario").replace("&", "§").replaceAll("%player%", target.getName()));
		}
		return false;
	}
}
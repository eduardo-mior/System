package rush.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

import rush.utils.ConfigManager;

public class ComandoEchest implements Listener, CommandExecutor {

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender s, Command cmd, String commandlabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("echest")) {
			 
			 if (!(s instanceof Player)) {
				 s.sendMessage(ConfigManager.getConfig("mensagens").getString("Console-Nao-Pode").replace("&", "§"));
				 return false;
			 }
			 
			 Player sender = (Player)s;
			 if (args.length == 1 && s.hasPermission("system.echest.mod")) {
				 Player p = Bukkit.getPlayer(args[0]);
				 if (p == null) {
         			s.sendMessage(ConfigManager.getConfig("mensagens").getString("Player-Offline").replaceAll("&", "§"));
         			return false;
				 }
				 Inventory i = p.getEnderChest();
				 sender.openInventory(i);
			 } else {
				 Inventory i = sender.getEnderChest();
				 sender.openInventory(i);
			 }
		}
		return false;
	}
}
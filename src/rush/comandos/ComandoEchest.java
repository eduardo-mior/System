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
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("echest")) {
			 
			 if (!(s instanceof Player)) {
				 s.sendMessage(ConfigManager.getConfig("mensagens").getString("Console-Nao-Pode").replace("&", "§"));
				 return false;
			 }
			 
			 Player sender = (Player)s;
			 if (args.length > 0 && s.hasPermission("system.echest.mod")) {
				 Player target = Bukkit.getPlayer(args[0]);
				 if (target == null) {
         			s.sendMessage(ConfigManager.getConfig("mensagens").getString("Player-Offline").replace("&", "§"));
         			return false;
				 }
				 
				 Inventory i = target.getEnderChest();
				 sender.openInventory(i);
				 return false;
			 }
				 
			 Inventory i = sender.getEnderChest();
			 sender.openInventory(i);
		}
		return false;
	}
}
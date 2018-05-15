package rush.comandos;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

import rush.utils.ConfigManager;
import rush.utils.DataManager;

public class ComandoCriarkit implements Listener, CommandExecutor {

	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("criarkit")) {
			
			if (!(s instanceof Player)) {
			    s.sendMessage(ConfigManager.getConfig("mensagens").getString("Console-Nao-Pode").replace("&", "§"));
			    return false;
			}
			
			if (args.length != 1) {
		        s.sendMessage(ConfigManager.getConfig("mensagens").getString("CriarKit-Comando-Incorreto").replace("&", "§"));
		        return false;
			}
			 
			String kit = args[0].toLowerCase();
			File file = DataManager.getFile(kit, "kits");		
			if (file.exists()) {
		        s.sendMessage(ConfigManager.getConfig("mensagens").getString("Kit-Ja-Existe").replace("&", "§").replace("%kit%", kit));
		        return false;
			}
			
			Player p = (Player)s;
			Inventory inv = Bukkit.getServer().createInventory(p, 36, "§0Criar Kit §n" + kit);
	        p.openInventory(inv);
		}
		return false;
	}
}
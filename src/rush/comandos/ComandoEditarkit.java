package rush.comandos;

import java.io.File;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import rush.utils.ConfigManager;
import rush.utils.DataManager;

public class ComandoEditarkit implements Listener, CommandExecutor {

	public boolean onCommand(CommandSender s, Command cmd, String commandlabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("editarkit")) {
			
			if (!(s instanceof Player)) {
			    s.sendMessage(ConfigManager.getConfig("mensagens").getString("Console-Nao-Pode").replace("&", "§"));
			    return false;
			}
			 
			if (!s.hasPermission("system.editarkit")) {
		        s.sendMessage(ConfigManager.getConfig("mensagens").getString("Sem-Permissao").replaceAll("&", "§"));
		        return false;
		    }
			
			if (args.length > 1 || args.length < 1) {
		        s.sendMessage(ConfigManager.getConfig("mensagens").getString("EditarKit-Comando-Incorreto").replaceAll("&", "§"));
		        return false;
			}
			
			Player p = (Player)s;
			String kit = args[0].toLowerCase();
			File file = DataManager.getFile(kit, "kits");
			
			if (!file.exists()) {
		        s.sendMessage(ConfigManager.getConfig("mensagens").getString("Kit-Nao-Existe").replaceAll("&", "§").replace("%kit%", kit));
		        ComandoKits.ListKits(s);
		        return false;
			}
			
			FileConfiguration config = DataManager.getConfiguration(file);
	    	Set<String> ITENS = config.getConfigurationSection("Itens").getKeys(false);
	    	int n = ITENS.size();
			Inventory inv = Bukkit.getServer().createInventory(p, 54, "§0Editar Kit §n" + kit);
	    	for (int i=0; n > i; i++) {
	    		 ItemStack item = config.getItemStack("Itens." + i);
	    		 inv.addItem(item);
	    	}
	    	p.openInventory(inv);
		}
		return false;
	}
}
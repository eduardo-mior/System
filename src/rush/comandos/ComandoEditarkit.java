package rush.comandos;

import java.io.File;
import java.io.IOException;
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

	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("editarkit")) {
			
			if (!(s instanceof Player)) {
			    s.sendMessage(ConfigManager.getConfig("mensagens").getString("Console-Nao-Pode").replace("&", "§"));
			    return false;
			}
			
			if (args.length > 3  || args.length < 2) {
		        s.sendMessage(ConfigManager.getConfig("mensagens").getString("EditarKit-Comando-Incorreto").replace("&", "§"));
		        return false;
			}
			
			String kit = args[0].toLowerCase();
			File file = DataManager.getFile(kit, "kits");
			if (!file.exists()) {
		        s.sendMessage(ConfigManager.getConfig("mensagens").getString("Kit-Nao-Existe").replace("&", "§").replace("%kit%", kit));
		        ComandoKits.ListKits(s);
		        return false;
			}
			
			FileConfiguration config = DataManager.getConfiguration(file);
			
			if (args[1].equalsIgnoreCase("itens")) {
				Player p = (Player)s;
		    	Set<String> ITENS = config.getConfigurationSection("Itens").getKeys(false);
		    	int n = ITENS.size();
				Inventory inv = Bukkit.getServer().createInventory(p, 36, "§0Editar Kit §n" + kit);
		    	for (int i=0; n > i; i++) {
		    		 ItemStack item = config.getItemStack("Itens." + i);
		    		 if (item != null) inv.setItem(i, item);
		    	}
		    	p.openInventory(inv);
		    	return false;
			}
			
			if (args[1].equalsIgnoreCase("delay")) {
				if (args.length != 3) {
			        s.sendMessage(ConfigManager.getConfig("mensagens").getString("EditarKit-Comando-Incorreto-Delay").replace("&", "§"));
			        return false;
				}
	            int delay;
	            try {
	                delay = Integer.valueOf(args[2]);
	            }
	            catch (NumberFormatException e) {
	                s.sendMessage(ConfigManager.getConfig("mensagens").getString("Numero-Invalido").replace("&", "§"));
	                return false;
	            }
	            config.set("Delay", delay);
				try {
					config.save(file);
					s.sendMessage(ConfigManager.getConfig("mensagens").getString("Kit-Editado").replace("&", "§").replace("%kit%", args[0]));
				} catch (IOException e) {
					Bukkit.getConsoleSender().sendMessage(ConfigManager.getConfig("mensagens").getString("Falha-Ao-Salvar").replace("&", "§").replace("%arquivo%", file.getName()));
				}
				return false;
			}
			
			if (args[1].equalsIgnoreCase("perm")) {
				if (args.length != 3) {
			        s.sendMessage(ConfigManager.getConfig("mensagens").getString("EditarKit-Comando-Incorreto-Perm").replace("&", "§"));
			        return false;
				}
				config.set("Permissao", args[2]);
				try {
					config.save(file);
					s.sendMessage(ConfigManager.getConfig("mensagens").getString("Kit-Editado").replace("&", "§").replace("%kit%", args[0]));
				} catch (IOException e) {
					Bukkit.getConsoleSender().sendMessage(ConfigManager.getConfig("mensagens").getString("Falha-Ao-Salvar").replace("&", "§").replace("%arquivo%", file.getName()));
				}
				return false;
			}
			
	        s.sendMessage(ConfigManager.getConfig("mensagens").getString("EditarKit-Comando-Incorreto").replace("&", "§"));
		}
		return false;
	}
}
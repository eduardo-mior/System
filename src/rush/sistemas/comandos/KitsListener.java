package rush.sistemas.comandos;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import rush.utils.ConfigManager;
import rush.utils.DataManager;

public class KitsListener implements Listener {
	
	@EventHandler
	public void InventoryClose(InventoryCloseEvent e) {
		if (e.getInventory().getName().contains("§0Criar Kit §n")) {
			Player p = (Player) e.getPlayer();
			Inventory inv = e.getInventory();
			ItemStack[] itens = inv.getContents();
			int j = 0;
			String kit = e.getInventory().getName().substring(14, e.getInventory().getName().length());
			File file = DataManager.getListFiles(kit, "kits");
			FileConfiguration config = DataManager.getConfiguration(file);
			DataManager.createFile(file);
			config.set("Permissao", "system.kit." + kit);
			config.set("Delay", 5);
        	config.createSection("Itens");
			for (ItemStack item : itens) {
				if (item == null) continue;
				config.set("Itens." + j, item);
				j++;
			}
			try {
				config.save(file);
				p.sendMessage(ConfigManager.getConfig("mensagens").getString("Kit-Criado").replace("&", "§").replace("%kit%", kit));
			} catch (IOException ex) {
				Bukkit.getConsoleSender().sendMessage(ConfigManager.getConfig("mensagens").getString("Falha-Ao-Salvar").replace("&", "§").replace("%arquivo%", file.getName()));
			}
		}
		
		else if (e.getInventory().getName().contains("§0Editar Kit §n")) {
			Player p = (Player) e.getPlayer();
			Inventory inv = e.getInventory();
			ItemStack[] itens = inv.getContents();
			int j = 0;
			String kit = e.getInventory().getName().substring(15, e.getInventory().getName().length());
			File file = DataManager.getListFiles(kit, "kits");
			FileConfiguration config = DataManager.getConfiguration(file);
			config.set("Itens", null);
	    	config.createSection("Itens");
			for (ItemStack item : itens) {
				if (item == null) continue;
				config.set("Itens." + j, item);
				j++;
			}
			try {
				config.save(file);
				p.sendMessage(ConfigManager.getConfig("mensagens").getString("Kit-Editado").replace("&", "§").replace("%kit%", kit));
			} catch (IOException ex) {
				Bukkit.getConsoleSender().sendMessage(ConfigManager.getConfig("mensagens").getString("Falha-Ao-Salvar").replace("&", "§").replace("%arquivo%", file.getName()));
			}
		}
	}
}

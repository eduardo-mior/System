package rush.sistemas.comandos;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import rush.configuracoes.Mensagens;
import rush.entidades.Kit;
import rush.entidades.Kits;
import rush.utils.DataManager;
import rush.utils.Serializer;

public class KitsListener implements Listener {

	@EventHandler
	public void InventoryClose(InventoryCloseEvent e) {
		
		if (e.getInventory().getTitle().startsWith("Kit §k§r§2§n")) {
			Player p = (Player) e.getPlayer();
			if (p.hasPermission("system.criarkit")) {
				Inventory inv = e.getInventory();
				createKit(inv, p);
				return;
			}
		}

		if (e.getInventory().getTitle().startsWith("Kit §k§r§4§n")) {
			Player p = (Player) e.getPlayer();
			if (p.hasPermission("system.editarkit")) {
				Inventory inv = e.getInventory();
				editKit(inv, p);
				return;
			}
		}
	}
	
	@EventHandler
	public void InvetoryClick(InventoryClickEvent e) {
		if (e.getInventory().getTitle().startsWith("Kit §k§r§1§n")) {
			e.setCancelled(true);
		}
	}

	private void createKit(Inventory inv, Player p) {
		String nome = inv.getName().substring(12, inv.getName().length());
		String permissao = "system.kit." + nome;
		String itens = Serializer.serializeListItemStack(inv.getContents());
		Kit kit = new Kit(nome, permissao, 5, itens);
		File file = DataManager.getFile(nome, "kits");
		FileConfiguration config = DataManager.getConfiguration(file);
		DataManager.createFile(file);
		config.set("Permissao", permissao);
		config.set("Delay", 5);
		config.set("Itens", itens);
		try {
			Kits.create(nome, kit);
			config.save(file);
			p.sendMessage(Mensagens.Kit_Criado.replace("%kit%", nome));
		} catch (IOException ex) {
			Bukkit.getConsoleSender().sendMessage(Mensagens.Falha_Ao_Salvar.replace("%arquivo%", file.getName()));
		}
	}
	
	private void editKit(Inventory inv, Player p) {
		String itens = Serializer.serializeListItemStack(inv.getContents());
		String nome = inv.getName().substring(12, inv.getName().length());
		Kit kit = Kits.get(nome);
		File file = DataManager.getFile(nome, "kits");
		FileConfiguration config = DataManager.getConfiguration(file);
		kit.setItens(inv.getContents());
		config.set("Itens", itens);
		try {
			config.save(file);
			p.sendMessage(Mensagens.Kit_Editado.replace("%kit%", nome));
		} catch (IOException ex) {
			Bukkit.getConsoleSender().sendMessage(Mensagens.Falha_Ao_Salvar.replace("%arquivo%", file.getName()));
		}
	}
}

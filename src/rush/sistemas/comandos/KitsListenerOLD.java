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

import rush.configuracoes.Mensagens;
import rush.entidades.Kit;
import rush.entidades.Kits;
import rush.utils.DataManager;
import rush.utils.SerializerOLD;

public class KitsListenerOLD implements Listener {

	@EventHandler
	public void InventoryClose(InventoryCloseEvent e) {
		
		if (e.getInventory().getName().contains("§0Kit §n")) {
			Inventory inv = e.getInventory();
			Player p = (Player) e.getPlayer();
			createKit(inv, p);
			return;
		}

		if (e.getInventory().getName().contains("§1Kit §n")) {
			Inventory inv = e.getInventory();
			Player p = (Player) e.getPlayer();
			editKit(inv, p);
			return;
		}
	}

	private void createKit(Inventory inv, Player p) {
		String nome = inv.getName().substring(8, inv.getName().length());
		String permissao = "system.kit." + nome;
		String itens = SerializerOLD.serializeListItemStack(inv.getContents());
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
		String itens = SerializerOLD.serializeListItemStack(inv.getContents());
		String nome = inv.getName().substring(8, inv.getName().length());
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

package rush.sistemas.comandos;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import rush.Main;
import rush.configuracoes.Mensagens;
import rush.entidades.Kit;
import rush.entidades.Kits;
import rush.utils.GuiHolder;
import rush.utils.manager.DataManager;
import rush.utils.serializer.Serializer;
import rush.utils.serializer.SerializerNEW;
import rush.utils.serializer.SerializerVeryNEW;
import rush.utils.serializer.SerializerOLD;

public class KitsListener implements Listener {

	@EventHandler
	public void InventoryClose(InventoryCloseEvent e) {
		if (e.getInventory().getHolder() instanceof GuiHolder) {
			
			Player p = (Player) e.getPlayer();
			GuiHolder holder = (GuiHolder) e.getInventory().getHolder();
			int guiID = holder.getId();
			
			if (guiID == 997) {
				String kit = (String) holder.getProperty("kit");
				if (p.hasPermission("system.criarkit")) {
					createKit(e.getInventory(), p, kit);
					return;
				}
			}
		
			else if (guiID == 995) {
				String kit = (String) holder.getProperty("kit");
				if (p.hasPermission("system.editarkit")) {
					editKit(e.getInventory(), p, kit);
					return;
				}
			}
		}
	}
	
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void InvetoryClick(InventoryClickEvent e) {
		if (e.getInventory().getHolder() instanceof GuiHolder) {
			int guiID = ((GuiHolder) e.getInventory().getHolder()).getId();
			if (guiID == -990) {
				e.setResult(Result.DENY);
				e.setCancelled(true);
			}
		}
	}

	// Método para criar o kit
	private void createKit(Inventory inv, Player p, String id) {
		String permissao = "system.kit." + id;
		String itens = serializeItens(inv.getContents());
		Kit kit = new Kit(id, permissao, "§rKit '" + id + "' sem nome! Use /editarkit!", 5, Mensagens.Kit_Sem_Permissao, itens);
		File file = DataManager.getFile(id, "kits");
		FileConfiguration config = DataManager.getConfiguration(file);
		DataManager.createFile(file);
		config.set("Permissao", permissao);
		config.set("Nome", "§rKit '" + id + "' sem nome! Use /editarkit!");
		config.set("Delay", 5);
		config.set("MensagemDeErro", Mensagens.Kit_Sem_Permissao);
		config.set("Itens", itens);
		try {
			Kits.create(id, kit);
			config.save(file);
			p.sendMessage(Mensagens.Kit_Criado.replace("%kit-id%", id));
		} catch (IOException ex) {
			Bukkit.getConsoleSender().sendMessage(Mensagens.Falha_Ao_Salvar.replace("%arquivo%", file.getName()));
		}
	}
	
	// Método para ediar o kit
	private void editKit(Inventory inv, Player p, String id) {
		String itens = serializeItens(inv.getContents());
		Kit kit = Kits.get(id);
		File file = DataManager.getFile(id, "kits");
		FileConfiguration config = DataManager.getConfiguration(file);
		kit.setItens(inv.getContents());
		config.set("Itens", itens);
		try {
			config.save(file);
			p.sendMessage(Mensagens.Kit_Editado.replace("%kit-id%", id).replace("%kit-nome%", kit.getNome()));
		} catch (IOException ex) {
			Bukkit.getConsoleSender().sendMessage(Mensagens.Falha_Ao_Salvar.replace("%arquivo%", file.getName()));
		}
	}
	
	// Método para serializar os itens de acordo com a versão
	private String serializeItens(ItemStack[] itens) {
		if (Main.isOldVersion()) 
		{
			return SerializerOLD.serializeListItemStack(itens);
		}
		else if (Main.isNewVersion()) 
		{
			return SerializerNEW.serializeListItemStack(itens);
		}
		else if (Main.isMotherFuckerVersion()) 
		{
			return SerializerVeryNEW.serializeListItemStack(itens);
		}
		else 
		{
			return Serializer.serializeListItemStack(itens);
		}
	}
	
}
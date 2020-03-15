package rush.sistemas.comandos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;

import rush.apis.OfflinePlayerAPI;
import rush.configuracoes.Mensagens;

public class EnderChestListener implements Listener {
	
	public static HashMap<String, String> echestlist = new HashMap<String, String>();

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void aoClicarEnderChest(InventoryClickEvent e) {
		Inventory i = e.getInventory();
		if (i.getType() == InventoryType.ENDER_CHEST) {
			Player p = (Player) e.getWhoClicked();
			if (echestlist.containsKey(p.getName()) && !(p.hasPermission("system.echest.admin"))) {
				e.setCancelled(true);
				e.setResult(Result.DENY);
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void aoFecharEnderChest(InventoryCloseEvent e) {
		Inventory i = e.getInventory();
		if (i.getType() == InventoryType.ENDER_CHEST) {
			Player p = (Player) e.getPlayer();
			if (echestlist.containsKey(p.getName())) {
				if (p.hasPermission("system.echest.admin")) {
					Player holder = OfflinePlayerAPI.getPlayer(echestlist.get(p.getName()));
					if (holder != null && !holder.isOnline()) {
						holder.getEnderChest().setContents(i.getContents());
						holder.saveData(); 
						// Só precisamos salvar os dados do player caso ele esteja offline...
						// Caso ele esteja online os dados são salvos automaticamente...
					}
				}
				echestlist.remove(p.getName());
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void aoEntrarComEchestAberto(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (echestlist.containsValue(p.getName())) {
			List<String> viewers = new ArrayList<>();
			for (Entry<String, String> entry : echestlist.entrySet()) {
				if (entry.getValue().equals(p.getName())) {
					viewers.add(entry.getKey());
				}
			}
			for (String viewer : viewers) {
				Player admin = Bukkit.getPlayer(viewer);
				if (admin != null) {
					admin.closeInventory();
					admin.sendMessage(Mensagens.Echest_Logou.replace("%player%", p.getName()));
				} else {
					echestlist.remove(viewer);
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void aoSairComEchestAberto(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if (echestlist.containsValue(p.getName())) {
			List<String> viewers = new ArrayList<>();
			for (Entry<String, String> entry : echestlist.entrySet()) {
				if (entry.getValue().equals(p.getName())) {
					viewers.add(entry.getKey());
				}
			}
			for (String viewer : viewers) {
				Player admin = Bukkit.getPlayer(viewer);
				if (admin != null) {
					admin.closeInventory();
					admin.sendMessage(Mensagens.Echest_Deslogou.replace("%player%", p.getName()));	
				}
				echestlist.remove(viewer);
			}
		}
	}
	
}
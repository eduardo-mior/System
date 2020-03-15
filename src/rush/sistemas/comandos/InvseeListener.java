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

public class InvseeListener implements Listener {

	public static HashMap<String, String> invseelist = new HashMap<String, String>();

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void aoClicarInvsee(InventoryClickEvent e) {
		Inventory i = e.getInventory();
		if (i.getType() == InventoryType.PLAYER) {
			Player p = (Player) e.getWhoClicked();
			if (invseelist.containsKey(p.getName()) && !(p.hasPermission("system.invsee.admin"))) {
				e.setCancelled(true);
				e.setResult(Result.DENY);
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void aoFecharInvsee(InventoryCloseEvent e) {
		Inventory i = e.getInventory();
		if (i.getType() == InventoryType.PLAYER) {
			Player p = (Player) e.getPlayer();
			if (invseelist.containsKey(p.getName())) {
				if (p.hasPermission("system.invsee.admin")) {
					Player holder = OfflinePlayerAPI.getPlayer(invseelist.get(p.getName()));
					if (holder != null && !holder.isOnline()) {
						holder.getInventory().setContents(i.getContents());
						holder.saveData(); 
						// Só precisamos salvar os dados do player caso ele esteja offline...
						// Caso ele esteja online os dados são salvos automaticamente...
					}
				}
				invseelist.remove(p.getName());
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void aoEntrarComInvseeAberto(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (invseelist.containsValue(p.getName())) {
			List<String> viewers = new ArrayList<>();
			for (Entry<String, String> entry : invseelist.entrySet()) {
				if (entry.getValue().equals(p.getName())) {
					viewers.add(entry.getKey());
				}
			}
			for (String viewer : viewers) {
				Player admin = Bukkit.getPlayer(viewer);
				if (admin != null) {
					admin.closeInventory();
					admin.sendMessage(Mensagens.Invsee_Logou.replace("%player%", p.getName()));
				} else {
					invseelist.remove(viewer);
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void aoSairComInvseeAberto(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if (invseelist.containsValue(p.getName())) {
			List<String> viewers = new ArrayList<>();
			for (Entry<String, String> entry : invseelist.entrySet()) {
				if (entry.getValue().equals(p.getName())) {
					viewers.add(entry.getKey());
				}
			}
			for (String viewer : viewers) {
				Player admin = Bukkit.getPlayer(viewer);
				if (admin != null) {
					admin.closeInventory();
					admin.sendMessage(Mensagens.Invsee_Deslogou.replace("%player%", p.getName()));	
				}
				invseelist.remove(viewer);
			}
		}
	}
	
}
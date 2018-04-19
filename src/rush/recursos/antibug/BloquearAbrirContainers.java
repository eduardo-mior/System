package rush.recursos.antibug;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.Inventory;

import rush.utils.ConfigManager;

import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.Listener;

public class BloquearAbrirContainers implements Listener {

	@EventHandler
	public void aoAbrirContainer(final InventoryOpenEvent e) {
		Player p = (Player) e.getPlayer();
		for (String containers : ConfigManager.getConfig("settings").getStringList("Bloquear-Abrir-Containers.Containers")) {
			Inventory inv = e.getInventory();
			if (inv.getType() == InventoryType.valueOf(containers)) {
				if (!(p.hasPermission("system.bypass.containerbloqueado"))) {
					e.setCancelled(true);
				}
			}
		}
	}
}
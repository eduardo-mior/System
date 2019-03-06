package rush.sistemas.comandos;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.Event.Result;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class InvseeListener implements Listener {

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void aoClicarInvsee(InventoryClickEvent e) {
		Inventory i = e.getInventory();
		if (i.getType() == InventoryType.PLAYER) {
			Player p = (Player) e.getWhoClicked();
			Inventory inv = p.getInventory();
			if (!(i.equals(inv)) && !(p.hasPermission("system.invsee.admin"))) {
				e.setCancelled(true);
				e.setResult(Result.DENY);
			}
		}
	}
	
}
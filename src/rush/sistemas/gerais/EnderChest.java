package rush.sistemas.gerais;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.Event.Result;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class EnderChest implements Listener {
	
	@EventHandler
	public void onOpen(InventoryClickEvent e) {
		Inventory i = e.getInventory();
		if (i.getType() == InventoryType.ENDER_CHEST) {
			Player p = (Player)e.getWhoClicked();
			Inventory ec = p.getEnderChest();
			if ((!i.equals(ec)) && (!p.hasPermission("system.echest.admin"))) {
				e.setCancelled(true);
				e.setResult(Result.DENY);
			}
		}
	}
}

package rush.recursos.adicionais;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class BigornaInfinita implements Listener {
	
	@EventHandler
	public void aoUsarBigorna(PlayerInteractEvent e) {
		Block block = e.getClickedBlock();
		Player p = e.getPlayer();
		Inventory i = Bukkit.createInventory(p, InventoryType.ANVIL);
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK && block.getType() == Material.ANVIL) {
	    	p.openInventory(i);
		}
	}
}

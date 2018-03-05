package rush.recursos.adicionais;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CoresNaBigorna implements Listener {
	
	@EventHandler
	public void aoRenomearNaBigorna(InventoryClickEvent e) {
		if (e.getInventory().getType() == InventoryType.ANVIL && e.getWhoClicked().hasPermission("system.cornabigorna")) {
			ItemStack item;
	        ItemMeta meta;
	        if (e.getRawSlot() == 2) {
	        	if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || !e.getCurrentItem().getItemMeta().hasDisplayName()) {
	        		return;
	            }

	            item = e.getCurrentItem();
	            meta = item.getItemMeta();
	            if (meta.getDisplayName().length() > 30) {
	               meta.setDisplayName(meta.getDisplayName().substring(0, 30).replaceAll("&", "§"));
	            } else {
	               meta.setDisplayName(meta.getDisplayName().replaceAll("&", "§"));
	            }

	            item.setItemMeta(meta);
	            e.setCurrentItem(item);
	         } else if (e.getRawSlot() == 0) {
	            if (e.getCursor() == null || e.getCursor().getType() == Material.AIR || !e.getCursor().getItemMeta().hasDisplayName()) {
	               return;
	            }

	            item = e.getCursor();
	            meta = item.getItemMeta();
	            if (meta.getDisplayName().length() > 30) {
	               meta.setDisplayName(meta.getDisplayName().substring(0, 30).replaceAll("&", "§"));
	            } else {
	               meta.setDisplayName(meta.getDisplayName());
	            }

	            item.setItemMeta(meta);
	            item.setAmount(item.getAmount());
	         }
	      }
	   }

}

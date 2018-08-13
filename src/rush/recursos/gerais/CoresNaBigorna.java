package rush.recursos.gerais;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CoresNaBigorna implements Listener {
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void aoRenomearNaBigorna(InventoryClickEvent e) {
		
		// Verificando se o inventário é uma bigorna e verificando se o player possui permissão
		if (e.getInventory().getType() == InventoryType.ANVIL && e.getWhoClicked().hasPermission("system.cornabigorna")) {
			
			// Verificando se ele clicou em um slot da bigorna
			if (e.getRawSlot() <= 2) {
				
				// Verificando se o item é valido
				if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || !e.getCurrentItem().getItemMeta().hasDisplayName()) {
					return;
				}

				// Pegando o item, a meta do item e o nome do item
				ItemStack item = e.getCurrentItem();
				ItemMeta meta = item.getItemMeta();
				String name = meta.getDisplayName();
	            
				// Caso o tamanho do nome seja maior que 30 então o nome é cortado
	            if (name.length() > 30) {
	            	name = name.substring(0, 30);
	            }

	            // Setando o novo nome colorido no item
	            meta.setDisplayName(name.replace('&', '§'));
	            item.setItemMeta(meta);
	            e.setCurrentItem(item);
	        }
		}
	}
}

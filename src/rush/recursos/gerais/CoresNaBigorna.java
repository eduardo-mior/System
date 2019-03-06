package rush.recursos.gerais;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CoresNaBigorna implements Listener {
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void aoRenomearNaBigorna(InventoryClickEvent e) {
		
		// Verificando se o inventário é uma bigorna
		if (e.getInventory().getType() == InventoryType.ANVIL) {
			
			// Verificando se ele clicou em um slot da bigorna
			if (e.getSlotType() == SlotType.RESULT) {
				
				// Verificando se o item é valido
				ItemStack item = e.getCurrentItem();
				if (item == null || item.getType() == Material.AIR || !item.getItemMeta().hasDisplayName()) {
					return;
				}

				// Pegando a meta do item e o nome do item
				ItemMeta meta = item.getItemMeta();
				String name = meta.getDisplayName();
				
				// Pegando o item antigo e verificando se o item esta sendo reparado e não renomeado
				ItemStack oldItem = e.getInventory().getItem(0);
				if (oldItem != null) {
					ItemMeta oldMeta = oldItem.getItemMeta();
					if (oldMeta.hasDisplayName()) {
						
						// Pegando o nome antigo do item e verificando se ele é igual ao novo
						String oldName = oldMeta.getDisplayName().replace("§", "");
						if (name.equals(oldName)) {
							
							// Caso o nome antigo seja igual ao novo então significa que o 
							// item esta sendo reparado e não renomeado.
							// Quando o item é reparado sem ser renomado, seu nome fica
							// bugado (sem as cores, sem o '§'), por isso é necessario definir 
							// um novo nome mesmo que o item esteja apena sendo reparado.
				            meta.setDisplayName(oldMeta.getDisplayName());
				            item.setItemMeta(meta);
				            e.setCurrentItem(item);
							return;
						}
					}
				}
				
				// Caso o item não esteja sendo reparado, isso significa que ele esta
				// sendo renomeado, e para renomear com cores precisa de permissão!
				if (e.getWhoClicked().hasPermission("system.cornabigorna")) {
	
		            // Setando o novo nome colorido no item
		            meta.setDisplayName(name.replace('&', '§'));
		            item.setItemMeta(meta);
		            e.setCurrentItem(item);
				}
	        }
		}
	}
	
}
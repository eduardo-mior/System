package rush.recursos.adicionais;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

import rush.utils.ConfigManager;

public class BloquearCrafts implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
    public void aoPrepararCraft(PrepareItemCraftEvent e){
		Player p = (Player) e.getView().getPlayer();
		int itemType = e.getRecipe().getResult().getType().getId();
			if (ConfigManager.getConfig("settings").getIntegerList("Lista-Dos-Crafts-Bloqueados").contains(Integer.valueOf(itemType))) {
				if (!(p.hasPermission("system.bypass.craftbloqueado"))) {
					e.getInventory().setResult(new ItemStack(Material.AIR));
			}
		}
	}
}

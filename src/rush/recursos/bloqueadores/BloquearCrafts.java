package rush.recursos.bloqueadores;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

import rush.configuracoes.Settings;

@SuppressWarnings("all")
public class BloquearCrafts implements Listener {

	@EventHandler(priority = EventPriority.LOWEST)
	public void aoPrepararCraft(PrepareItemCraftEvent e) {
		Player p = (Player) e.getView().getPlayer();
		int itemType = e.getRecipe().getResult().getType().getId();
		if (Settings.Lista_Dos_Crafts_Bloqueados.contains(itemType)) {
			if (!(p.hasPermission("system.bypass.craftbloqueado"))) {
				e.getInventory().setResult(new ItemStack(Material.AIR));
			}
		}
	}
}

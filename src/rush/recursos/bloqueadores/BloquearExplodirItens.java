package rush.recursos.bloqueadores;

import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class BloquearExplodirItens implements Listener {
	
	@EventHandler(ignoreCancelled = true)
	public void aoExplodirItem(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Item) {
			e.setCancelled(true);
		}
	}
	
}
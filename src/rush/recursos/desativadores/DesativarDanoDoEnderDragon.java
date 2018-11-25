package rush.recursos.desativadores;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class DesativarDanoDoEnderDragon implements Listener {

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void aoQuebrarOsBlocos(EntityExplodeEvent e) {
		if (e.getEntity().getType() == EntityType.ENDER_DRAGON) {
			e.setCancelled(true);
		}
	}
}

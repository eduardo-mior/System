package rush.recursos.desativadores;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class DesativarDanoDoBlaze implements Listener {

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void aoLancarAsCabecas(ProjectileLaunchEvent e) {
		if (e.getEntityType() == EntityType.SMALL_FIREBALL) {
			if (e.getEntity().getShooter() != null) {
				e.setCancelled(true);
			}
		}
	}
}

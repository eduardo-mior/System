package rush.recursos.desativadores;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class DesativarDanoDoWither implements Listener {

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void aoDestruirOsBlocos(EntityChangeBlockEvent e) {
		if (e.getEntity().getType() == EntityType.WITHER) {
			e.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void aoLancarAsCabecas(ProjectileLaunchEvent e) {
		if (e.getEntityType() == EntityType.WITHER_SKULL) {
			if (e.getEntity().getShooter() != null) {
				e.setCancelled(true);
			}
		}
	}
	
}
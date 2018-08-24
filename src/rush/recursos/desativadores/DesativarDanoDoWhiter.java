package rush.recursos.desativadores;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class DesativarDanoDoWhiter implements Listener {

	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
	public void aoDestruirOsBlocos(EntityChangeBlockEvent e) {
		if (e.getEntity().getType() == EntityType.WITHER) {
			e.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
	public void aoLancarAsCabecas(ProjectileLaunchEvent e) {
		if (e.getEntityType() == EntityType.WITHER_SKULL) {
			e.setCancelled(true);
		}
	}
}

package rush.recursos.bloqueadores;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class BloquearMobsDePegaremItensDoChao implements Listener {
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void aoNascer(CreatureSpawnEvent e) {
		e.getEntity().setCanPickupItems(false);
	}

}
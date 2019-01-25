package rush.recursos.bloqueadores;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustByBlockEvent;
import org.bukkit.event.entity.EntityCombustByEntityEvent;
import org.bukkit.event.entity.EntityCombustEvent;

public class BloquearMobsDePegaremFogoParaOSol implements Listener {

	@EventHandler(priority = EventPriority.LOWEST)
	public void aoPegarFogo(EntityCombustEvent e) {
		if (!(e instanceof EntityCombustByEntityEvent) && !(e instanceof EntityCombustByBlockEvent)) {
			e.setCancelled(true);
		}
	}
	
}
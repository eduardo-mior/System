package rush.recursos.bloqueadores;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.PortalCreateEvent;

public class BloquearCriarPortal implements Listener {

	@EventHandler
	public void aoCriarPortal(PortalCreateEvent e) {
		e.setCancelled(true);
	}
}
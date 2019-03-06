package rush.recursos.bloqueadores;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class BloquearCama implements Listener {

	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
	public void aoEntrarNaCama(PlayerBedEnterEvent e) {
		if (!(e.getPlayer().hasPermission("system.bypass.entrarnacama"))) {
			e.setCancelled(true);
		}
	}
	
}
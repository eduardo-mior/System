package rush.recursos.desativadores;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.LeavesDecayEvent;

public class DesativarQuedaDasFolhas implements Listener {

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void aoCairFolha(LeavesDecayEvent e) {
		e.setCancelled(true);
	}
}

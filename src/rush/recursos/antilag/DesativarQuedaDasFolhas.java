package rush.recursos.antilag;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.LeavesDecayEvent;

public class DesativarQuedaDasFolhas implements Listener {

	@EventHandler
	public void aoCairFolha(LeavesDecayEvent e) {
		e.setCancelled(true);
	}
}

package rush.recursos.gerais;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class CoresNaPlaca implements Listener {

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void aoUsarPlaca(SignChangeEvent e) {
		if (e.getPlayer().hasPermission("system.cornaplaca")) {
			for (int i = 0; i < e.getLines().length; i++) {
				e.setLine(i, e.getLine(i).replace("&", "§"));
			}
		}
	}
}

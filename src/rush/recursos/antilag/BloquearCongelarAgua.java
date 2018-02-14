package rush.recursos.antilag;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFormEvent;

public class BloquearCongelarAgua implements Listener {
	
	@EventHandler
	public void aoConcelar(BlockFormEvent e) {
		if (e.getBlock().getType() == Material.WATER || e.getBlock().getType() == Material.STATIONARY_WATER) {
			e.setCancelled(true);
		}
	}
}

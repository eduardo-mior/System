package rush.recursos.desativadores;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

public class DesativarFlowDaAguaELava implements Listener {
	
	/**
	 * Powered by kickpost;
	 */
	
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onWater(BlockFromToEvent e) {
		if (   !e.getBlock().getType().equals(Material.WATER) 
			&& !e.getBlock().getType().equals(Material.STATIONARY_WATER)
			&& !e.getBlock().getType().equals(Material.LAVA)
			&& !e.getBlock().getType().equals(Material.STATIONARY_LAVA))
			return;

		Location partidaLoc = e.getBlock().getLocation();
		Location finalLoc = e.getToBlock().getLocation();

		if (partidaLoc.getY() != finalLoc.getY()) {
			e.setCancelled(true);
		}
	}
}

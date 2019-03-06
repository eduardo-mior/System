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
		Material type = e.getBlock().getType();
		if (   type != Material.WATER
			&& type != Material.STATIONARY_WATER
			&& type != Material.LAVA
			&& type != Material.STATIONARY_LAVA)
			return;

		Location partidaLoc = e.getBlock().getLocation();
		Location finalLoc = e.getToBlock().getLocation();

		if (partidaLoc.getY() != finalLoc.getY()) {
			e.setCancelled(true);
		}
	}
	
}
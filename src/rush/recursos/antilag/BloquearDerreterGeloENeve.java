package rush.recursos.antilag;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFadeEvent;

public class BloquearDerreterGeloENeve  implements Listener {
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void aoDerreterBlock(BlockFadeEvent e) {
		if (e.getBlock().getType() == Material.ICE || e.getBlock().getType() == Material.SNOW || e.getBlock().getType() == Material.SNOW_BLOCK) {
			e.setCancelled(true);
		}
	}
}
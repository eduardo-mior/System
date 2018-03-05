package rush.recursos.antibug;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BloquearXpAoQuebrarMobSpawners implements Listener {
	
	@EventHandler (priority=EventPriority.HIGHEST)
	public void aoQuebrarMobsSpawner(BlockBreakEvent e) {
		Block block = e.getBlock();
		if (block.getType() == Material.MOB_SPAWNER) {
			e.setExpToDrop(0);
		}
	}
}

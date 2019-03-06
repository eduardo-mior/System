package rush.sistemas.spawners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class BloquearTrocarTipoDoSpawnerComOvo implements Listener {
	
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void aoUsarOvo(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.MOB_SPAWNER && e.getItem() != null && e.getItem().getType() == Material.MONSTER_EGG) {
			e.setCancelled(true);
		}
    }
	
}
package rush.sistemas.spawners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class BloquearTrocarTipoDoSpawnerComOvo implements Listener {
	
    @EventHandler(priority = EventPriority.MONITOR)
    public void onMobspawnerChange(final PlayerInteractEvent e) {
        if (e.isCancelled() || !e.hasBlock() || !e.hasItem() || e.getItem().getType() != Material.MONSTER_EGG || e.getClickedBlock().getType() != Material.MOB_SPAWNER || e.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        e.setCancelled(true);
    }
}

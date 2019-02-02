package rush.recursos.gerais;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import rush.apis.AnvilAPI;

public class BigornaInfinita implements Listener {

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void aoUsarBigorna(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.ANVIL) {
			if (!e.getPlayer().isSneaking()) {
				AnvilAPI.openAnvil(e.getPlayer());
				e.setCancelled(true);
			}
		}
	}
}

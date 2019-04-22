package rush.recursos.desativadores;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class DesativarQuedaDaAreia implements Listener {

	/**
	 * Código criado por iPyronic
	 * Link: https://www.spigotmc.org/threads/prevent-sand-from-falling-upon-placing-sand.133386/
	 */
	
	@EventHandler(ignoreCancelled = true)
	public void aoCair(EntityChangeBlockEvent e) {
		if (e.getEntityType() == EntityType.FALLING_BLOCK && e.getTo() == Material.AIR) {
			if (e.getBlock().getType() == Material.SAND || e.getBlock().getType() == Material.GRAVEL) {
				e.setCancelled(true);
				e.getBlock().getState().update(false, false);
			}
		}
	}
	
}
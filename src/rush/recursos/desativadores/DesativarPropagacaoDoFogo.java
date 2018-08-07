package rush.recursos.desativadores;

import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockIgniteEvent.IgniteCause;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class DesativarPropagacaoDoFogo implements Listener {

	@EventHandler(priority = EventPriority.LOWEST)
	public void aoPegarFogo(BlockBurnEvent e) {
		e.setCancelled(true);
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void aoEspalharFogo(BlockIgniteEvent e) {
		if (e.getCause() == IgniteCause.LAVA || e.getCause() == IgniteCause.SPREAD) {
			e.setCancelled(true);
		}
	}
}
package rush.recursos.gerais;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

@SuppressWarnings("all")
public class BigornaInfinita implements Listener {

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void aoUsarBigorna(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.ANVIL) {
			byte oldData = e.getClickedBlock().getData();
			byte newData = (byte) (oldData % 2 == 0 ? 0 : 1);
			e.getClickedBlock().setData(newData);
		}
	}
}

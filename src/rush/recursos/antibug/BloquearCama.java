package rush.recursos.antibug;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class BloquearCama implements Listener {
	
	@EventHandler
	public void aoEntrarNaCama(PlayerBedEnterEvent e) {
		Player p = e.getPlayer();
		if (!(p.hasPermission("system.bypass.entrarnacama"))) {
			e.setCancelled(true);
		}
	}
}

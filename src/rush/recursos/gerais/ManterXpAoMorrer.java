package rush.recursos.gerais;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class ManterXpAoMorrer implements Listener {

	@EventHandler
	public void aoMorrer(PlayerDeathEvent e) {
		Player p = e.getEntity();
		if (p.hasPermission("system.manterxp")) {
			e.setKeepLevel(true);
			e.setDroppedExp(0);
		}
	}
}
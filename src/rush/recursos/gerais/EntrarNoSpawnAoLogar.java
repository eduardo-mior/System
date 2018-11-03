package rush.recursos.gerais;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import rush.configuracoes.Locations;

public class EntrarNoSpawnAoLogar implements Listener {

	@EventHandler(priority = EventPriority.MONITOR)
	public void aoRenascer(PlayerRespawnEvent e) {
		e.setRespawnLocation(Locations.spawn);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void aoLogar(PlayerJoinEvent e) {
		if (e.getPlayer().hasPermission("system.spawn.vip")) {
			e.getPlayer().teleport(Locations.spawnVip);
		} else {
			e.getPlayer().teleport(Locations.spawn);
		}
	}
}

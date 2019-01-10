package rush.recursos.gerais;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import rush.configuracoes.Locations;

public class EntrarNoSpawnAoLogar implements Listener {

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void aoRenascer(PlayerRespawnEvent e) {
		e.setRespawnLocation(Locations.spawn);
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void aoLogar(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (p.hasPermission("system.spawn.vip")) {
			p.teleport(Locations.spawnVip, TeleportCause.PLUGIN);
		} else {
			p.teleport(Locations.spawn, TeleportCause.PLUGIN);
		}
	}
}

package rush.recursos.gerais;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import rush.configuracoes.Locations;

public class EntrarNoSpawnAoLogar implements Listener {

	@EventHandler
	public void aoRenascer(PlayerRespawnEvent e) {
		e.setRespawnLocation(Locations.spawn);
	}

	@EventHandler
	public void aoLogar(PlayerJoinEvent e) {
		e.getPlayer().teleport(Locations.spawn);
	}
}

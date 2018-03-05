package rush.recursos.gerais;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import rush.utils.Locations;

public class EntrarNoSpawnAoLogar implements Listener {

	@EventHandler (priority=EventPriority.LOW)
	public void aoRenascer(PlayerRespawnEvent e) {
		e.setRespawnLocation(Locations.spawn);
	}
	    
	@EventHandler (priority=EventPriority.LOW)
	public void aoLogar(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		p.teleport(Locations.spawn);
	}
}

package rush.sistemas.comandos;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class BackListener implements Listener {

	public static HashMap<String, Location> backList = new HashMap<String, Location>();

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void aoTeleportar(PlayerTeleportEvent e) {
		backList.put(e.getPlayer().getName(), e.getFrom());
	}

	@EventHandler
	public void aoMorrer(PlayerDeathEvent e) {
		backList.put(e.getEntity().getName(), e.getEntity().getLocation());
	}

	@EventHandler
	public void aoSair(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if (backList.containsKey(p.getName())) backList.remove(p.getName());
	}
}

package rush.sistemas.comandos;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

public class BackListener implements Listener {

	public static HashMap<String, Location> backList = new HashMap<String, Location>();

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void aoTeleportar(PlayerTeleportEvent e) {
		if (isValidTeleportCause(e.getCause()) && !isSameBlock(e.getTo(), e.getFrom()))
			backList.put(e.getPlayer().getName(), e.getFrom());
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void aoMorrer(PlayerDeathEvent e) {
		backList.put(e.getEntity().getName(), e.getEntity().getLocation());
	}
	
	// Método para verificar se o bloco é o mesmo
	private boolean isSameBlock(Location one, Location two) {
		return 	one.getBlockX() == two.getBlockX() && 
				one.getBlockZ() == two.getBlockZ() && 
				one.getBlockY() == two.getBlockY() && 
				one.getWorld().equals(two.getWorld());
	}
	
	// Método para verificar se a causa do teleporte é valida
	private boolean isValidTeleportCause(TeleportCause cause) {
		return cause == TeleportCause.COMMAND || cause == TeleportCause.PLUGIN;
	}
	
}
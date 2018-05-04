package rush.sistemas.comandos;

import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class BackListener implements Listener {
	
	public static ConcurrentHashMap<Player, Location> backList = new ConcurrentHashMap<Player, Location>();
	
	@EventHandler
	public void aoTeleportar(PlayerTeleportEvent e) {
		Player p = e.getPlayer();
		Location l = e.getFrom();
		if (backList.containsKey(p)) backList.replace(p, l);
		else backList.put(p, l);
	}
	
	@EventHandler
	public void aoMorrer(PlayerDeathEvent e) {
		Player p = e.getEntity();
		Location l = p.getLocation();
		if (backList.containsKey(p)) backList.replace(p, l);
		else backList.put(p, l);
	}
	
	public void aoSair(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if (backList.containsKey(p)) backList.remove(p);
	}
}

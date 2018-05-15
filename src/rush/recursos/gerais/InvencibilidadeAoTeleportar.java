package rush.recursos.gerais;

import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.scheduler.BukkitRunnable;

import rush.Main;
import rush.utils.ConfigManager;

public class InvencibilidadeAoTeleportar implements Listener {
	
	private ConcurrentHashMap<Player, Integer> godList = new ConcurrentHashMap<Player, Integer>();
	
	@EventHandler
	public void aoTeleportar(PlayerTeleportEvent e) {
		Player p = e.getPlayer();
		if (e.getCause() == TeleportCause.COMMAND) {
			godList.put(p, 0);
			new BukkitRunnable() {
				public void run() {
					godList.remove(p);
				}
			}.runTaskLater(Main.aqui, 20 * ConfigManager.getConfig("settings").getInt("Tempo-De-Invencibilidade-Ao-Teleportar"));
		}
	}
	
	
	@EventHandler
	public void aoTomarDano(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (godList.containsKey(p)) {
				Bukkit.broadcastMessage(String.valueOf(godList));
				e.setCancelled(true);
			}
		}
	}
}

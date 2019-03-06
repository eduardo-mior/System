package rush.sistemas.comandos;

import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import rush.configuracoes.Settings;

public class FlyListener implements Listener {

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void aoTeleportar(PlayerTeleportEvent e) {
		Player p = e.getPlayer();
		if (!p.hasPermission("system.fly.staff") && p.getGameMode() != GameMode.CREATIVE) {
			String world = e.getTo().getWorld().getName();
			List<String> worlds = Settings.Mundos_Onde_Pode_Usar_Fly;
			if (!worlds.contains(world)) {
				p.setFlying(false);
				p.setAllowFlight(false);
			}
		}
	}
	
}
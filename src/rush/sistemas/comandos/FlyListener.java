package rush.sistemas.comandos;

import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import rush.configuracoes.Settings;

public class FlyListener implements Listener {

	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void aoTeleportar(PlayerTeleportEvent e) {
		if (!e.getPlayer().hasPermission("system.fly.staff")) {
			String world = e.getFrom().getWorld().getName();
			List<String> worlds = Settings.Mundos_Onde_Pode_Usar_Fly;
			if (!worlds.contains(world)) {
				e.getPlayer().setFlying(false);
				e.getPlayer().setAllowFlight(false);
			}
		}
	}
}
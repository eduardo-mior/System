package rush.sistemas.comandos;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import rush.utils.ConfigManager;

public class FlyListener implements Listener {
	
	@EventHandler
	public void aoTeleportar(PlayerTeleportEvent e) {
		Player p = e.getPlayer();
		if (!p.hasPermission("system.fly.staff")) {
			String world = e.getFrom().getWorld().getName();
			List<String> worlds = ConfigManager.getConfig("mensagens").getStringList("Mundos-Onde-Pode-Usar-Fly");
			if (!worlds.contains(world)) {
				p.setAllowFlight(false);
				p.setFlying(false);
			}
		}
	}
}
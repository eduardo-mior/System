package rush.recursos.bloqueadores;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import rush.configuracoes.Settings;

public class BloquearTeleportPorPortal implements Listener {

	@EventHandler(priority = EventPriority.LOWEST)
	public static void aoTeleportaPorPortal(PlayerPortalEvent e) {
		if (!(e.getPlayer().hasPermission("system.bypass.teleportarporportal"))) {

			if (e.getCause() == TeleportCause.NETHER_PORTAL) {
				if (Settings.Bloquear_Teleport_Por_Portal_NetherPortal) {
					e.setCancelled(true);
					return;
				}
			}

			if (e.getCause() == TeleportCause.END_PORTAL) {
				if (Settings.Bloquear_Teleport_Por_Portal_EndPortal) {
					e.setCancelled(true);
					return;
				}
			}
		}
	}
}

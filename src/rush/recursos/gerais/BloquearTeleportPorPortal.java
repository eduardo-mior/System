package rush.recursos.gerais;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import rush.utils.ConfigManager;

public class BloquearTeleportPorPortal implements Listener {
	
    @EventHandler
    public static void aoTeleportaPorPortal(final PlayerPortalEvent e) {
    	if (e.getCause() == PlayerTeleportEvent.TeleportCause.NETHER_PORTAL) {
    	    if (ConfigManager.getConfig("settings").getBoolean("Bloquear-Teleport-Por-Portal.NetherPortal")) {
	    		if (!(e.getPlayer().hasPermission("system.bypass.teleportarporportal"))) {
	    			e.setCancelled(true);
	    			return;
	    		}
	    	}
	    }
	    
	    if (e.getCause() == PlayerTeleportEvent.TeleportCause.END_PORTAL) {
		    if (ConfigManager.getConfig("settings").getBoolean("Bloquear-Teleport-Por-Portal.EndPortal")) {
	    		if (!(e.getPlayer().hasPermission("system.bypass.teleportarporportal"))) {
	    			e.setCancelled(true);
	    			return;
	    		}
	    	}
	    }
    }
}

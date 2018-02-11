package rush.recursos.antibug;

import org.bukkit.World.Environment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import rush.Main;

public class BloquearSubirNoTetoNether implements Listener {

	@EventHandler
	public void aoTeleportar(PlayerTeleportEvent e) {
		  if (e.getTo().getWorld().getEnvironment() == Environment.NETHER && e.getTo().getY() > 124.0D) {
		      e.setCancelled(true);
		      e.getPlayer().teleport(Main.loc);
		      e.getPlayer().setFallDistance(0);
	     }
      }
}

package rush.recursos.bloqueadores;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import rush.configuracoes.Locations;

public class BloquearCairNoVoid implements Listener {

	@EventHandler
	public void aoSofrerDano(EntityDamageEvent e) {
		if (e.getCause() == DamageCause.VOID && e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (p.getLocation().getBlockY() < 0) {
				e.setCancelled(true);
				p.setFallDistance(1);
				p.teleport(Locations.spawn);
			}
		}
	}
}

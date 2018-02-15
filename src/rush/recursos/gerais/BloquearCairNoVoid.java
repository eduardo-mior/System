package rush.recursos.gerais;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import rush.utils.Locations;

public class BloquearCairNoVoid implements Listener {

	@EventHandler
    public void aoSofrerDano(EntityDamageEvent e) {
    	if(e.getCause() == DamageCause.VOID && e.getEntity() instanceof Player) {
            e.setCancelled(true);
    		e.getEntity().setFallDistance(1);
    		e.getEntity().teleport(Locations.spawn);
    	}
    }
	
}

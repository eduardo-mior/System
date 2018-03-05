package rush.recursos.gerais;

import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustByBlockEvent;
import org.bukkit.event.entity.EntityCombustByEntityEvent;
import org.bukkit.event.entity.EntityCombustEvent;

public class BloquearMobsDePegaremFogoParaOSol implements Listener {
	
	boolean sol = false;
	@EventHandler(priority = EventPriority.LOWEST)
	public void aoPegarFogo(EntityCombustEvent e) {
		if (!sol) {
			if ((e.getEntity() instanceof Zombie || e.getEntity() instanceof Skeleton)) {
	        e.setCancelled(true);
			}
		}
	sol = false;
	}
	  
	@EventHandler(priority = EventPriority.HIGHEST)
	public void aoPegarFogoPorCausaDeEntidade(EntityCombustByEntityEvent e) { 
	sol = true;
	}
	   
	@EventHandler(priority = EventPriority.HIGHEST)
	public void aoPegarFogoPorCausaDeBloco(EntityCombustByBlockEvent e) {
	sol = true;
	}
}

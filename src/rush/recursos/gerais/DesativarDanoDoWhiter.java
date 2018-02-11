package rush.recursos.gerais;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class DesativarDanoDoWhiter implements Listener {

    @EventHandler
    public void aoDestruirOsBlocos(final EntityChangeBlockEvent a) {
        if (a.getEntity().getType() == EntityType.WITHER) {
            a.setCancelled(true);
	  }
    }
    
	@EventHandler
	private void aoLancarAsCabecas(ProjectileLaunchEvent e) {
		if((e.getEntityType() == EntityType.WITHER_SKULL)) {
			e.setCancelled(true);
	  }
    }
	
}

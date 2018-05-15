package rush.recursos.antibug;

import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.event.entity.EntityPortalExitEvent;

public class Outros implements Listener {
    
	@EventHandler
	public void morreuAoEntrar(EntityPortalEnterEvent e) {
		Entity entity = e.getEntity();
		if (entity.isDead()) {
			entity.remove();
		}
	}

	@EventHandler
	public void mooreuAoSair(EntityPortalExitEvent e) {
		Entity entity = e.getEntity();
		if (entity.isDead()) {
			entity.remove();
		}
	}
}

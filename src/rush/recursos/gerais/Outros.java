package rush.recursos.gerais;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.event.entity.EntityPortalExitEvent;

public class Outros implements Listener {

	@EventHandler
	public void morreuAoEntrar(EntityPortalEnterEvent e) {
		if (e.getEntity().isDead()) {
			e.getEntity().remove();
		}
	}

	@EventHandler
	public void mooreuAoSair(EntityPortalExitEvent e) {
		if (e.getEntity().isDead()) {
			e.getEntity().remove();
		}
	}
}

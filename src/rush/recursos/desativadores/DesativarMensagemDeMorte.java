package rush.recursos.desativadores;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DesativarMensagemDeMorte implements Listener {

	@EventHandler(priority = EventPriority.HIGH)
	public void aoMorrer(PlayerDeathEvent e) {
		e.setDeathMessage(null);
	}
	
}
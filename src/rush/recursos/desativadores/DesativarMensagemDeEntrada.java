package rush.recursos.desativadores;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class DesativarMensagemDeEntrada implements Listener {

	@EventHandler
	public void aoEntrar(PlayerJoinEvent e) {
		e.setJoinMessage(null);
	}
}

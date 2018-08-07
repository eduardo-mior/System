package rush.sistemas.gerais;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import rush.configuracoes.Settings;

public class Motd implements Listener {

	String motd = Settings.Motd_Linha1 + "\n" + Settings.Motd_Linha2;

	@EventHandler
	public void definirMotd(ServerListPingEvent e) {
		e.setMotd(motd);
	}
}

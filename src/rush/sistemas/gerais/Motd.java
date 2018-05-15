package rush.sistemas.gerais;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import rush.utils.ConfigManager;

public class Motd implements Listener {

	String motd = ConfigManager.getConfig("settings").getString("Motd.Linha1").replace("&", "§") + "\n"
				+ ConfigManager.getConfig("settings").getString("Motd.Linha2").replace("&", "§");
	
	@EventHandler
	public void definirMotd(ServerListPingEvent e) {
		e.setMotd(motd);
	}
}


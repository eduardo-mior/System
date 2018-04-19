package rush.sistemas.gerais;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import rush.utils.ConfigManager;

public class Motd implements Listener {

	String motd =  ConfigManager.getConfig("settings").getString("Motd.Linha1").replaceAll("&", "§") + "\n" + ConfigManager.getConfig("settings").getString("Motd.Linha2").replaceAll("&", "§");
	
	@EventHandler
	public void definirMotd(ServerListPingEvent e) {
		e.setMotd(motd);
	}
}


package rush.sistemas.gerais;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import rush.Main;

public class Motd implements Listener {

	String motd =  Main.aqui.getConfig().getString("Motd.Linha1").replaceAll("&", "§") + "\n" + Main.aqui.getConfig().getString("Motd.Linha2").replaceAll("&", "§");
	
	@EventHandler
	public void definirMotd(ServerListPingEvent e) {
		e.setMotd(motd);
	}
}


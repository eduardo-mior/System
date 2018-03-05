package rush.recursos.gerais;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.server.ServerListPingEvent;

import rush.Main;

public class LimiteDePlayers implements Listener {
	
	@EventHandler
	public void aoEntrar(PlayerLoginEvent e) {
		int i = Bukkit.getOnlinePlayers().size();
		if (i >= Main.aqui.getConfig().getInt("Limite-De-Players")) {
			e.disallow(Result.KICK_OTHER, Main.aqui.getMensagens().getString("Servidor-Lotado").replace("&", "§"));
		}
	}
	   
	@EventHandler
	public void aoVerMotd(ServerListPingEvent e) { 
		e.setMaxPlayers(Main.aqui.getConfig().getInt("Limite-De-Players"));
	}
}

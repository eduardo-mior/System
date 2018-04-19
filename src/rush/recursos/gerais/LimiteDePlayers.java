package rush.recursos.gerais;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.server.ServerListPingEvent;

import rush.utils.ConfigManager;

public class LimiteDePlayers implements Listener {
	
	@EventHandler
	public void aoEntrar(PlayerLoginEvent e) {
		int i = Bukkit.getOnlinePlayers().size();
		if (i >= ConfigManager.getConfig("settings").getInt("Limite-De-Players")) {
			e.disallow(Result.KICK_OTHER, ConfigManager.getConfig("mensagens").getString("Servidor-Lotado").replace("&", "§"));
		}
	}
	   
	@EventHandler
	public void aoVerMotd(ServerListPingEvent e) { 
		e.setMaxPlayers(ConfigManager.getConfig("settings").getInt("Limite-De-Players"));
	}
}

package rush.recursos.gerais;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.server.ServerListPingEvent;

import rush.configuracoes.Mensagens;
import rush.configuracoes.Settings;

@SuppressWarnings("all")
public class LimiteDePlayers implements Listener {

	@EventHandler(priority = EventPriority.LOWEST)
	public void aoEntrar(PlayerLoginEvent e) {
		int playersOnline = getNumberOnlinePlayers(Bukkit.getOnlinePlayers());
		int limite = Settings.Limite_De_Players;
		if (playersOnline >= limite) {
			e.setResult(Result.KICK_FULL);
			e.disallow(Result.KICK_FULL, Mensagens.Servidor_Lotado);
		}
	}

	@EventHandler
	public void aoVerMotd(ServerListPingEvent e) {
		e.setMaxPlayers(Settings.Limite_De_Players);
	}
	
	// Método para pegar o número de players online 1.8 - 1.13
	private int getNumberOnlinePlayers(Collection<? extends Player>  collection) {
		return collection.size();
	}
	
	// Método para pegar o número de players online 1.5 - 1.7
	private int getNumberOnlinePlayers(Player[]  list) {
		return list.length;
	}
}

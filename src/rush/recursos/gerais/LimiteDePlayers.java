package rush.recursos.gerais;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.server.ServerListPingEvent;

import rush.configuracoes.Mensagens;
import rush.configuracoes.Settings;

public class LimiteDePlayers implements Listener {

	@EventHandler(priority = EventPriority.LOWEST)
	public void aoEntrar(PlayerLoginEvent e) {
		int playersOnline = Bukkit.getOnlinePlayers().size();
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
}

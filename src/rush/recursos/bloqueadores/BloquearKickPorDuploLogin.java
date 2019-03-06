
package rush.recursos.bloqueadores;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;

import rush.configuracoes.Mensagens;

public class BloquearKickPorDuploLogin implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.LOWEST)
	public void aoTentarEntrar(AsyncPlayerPreLoginEvent e) {
		Player p = Bukkit.getPlayerExact(e.getName());
		if (p != null) {
			e.setKickMessage(Mensagens.Nick_Ja_Logado.replace("%nome%", e.getName()));
			e.setLoginResult(Result.KICK_OTHER);
		}
	}
	
}
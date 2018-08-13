package rush.recursos.bloqueadores;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerLoginEvent;

import rush.configuracoes.Mensagens;
import rush.configuracoes.Settings;

public class BloquearNicksImproprios implements Listener {

	@EventHandler(priority = EventPriority.LOWEST)
	public void aoEntrar(PlayerLoginEvent e) {
		for (String nomebloqueado : Settings.Nicks_Bloqueados) {
			String nome = e.getPlayer().getName().toLowerCase();
			String check = nomebloqueado.toLowerCase();
			if (nome.contains(check)) {
				e.setKickMessage(Mensagens.Nick_Bloqueado.replace("%nick%", nomebloqueado));
				e.setResult(Result.KICK_OTHER);
				return;
			}
		}
	}
}

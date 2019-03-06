package rush.recursos.bloqueadores;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import rush.configuracoes.Mensagens;
import rush.configuracoes.Settings;

import org.bukkit.event.player.PlayerLoginEvent;

public class BloquearNicksImproprios implements Listener {

	@EventHandler(priority = EventPriority.LOWEST)
	public void aoEntrar(PlayerLoginEvent e) {
		String nome = e.getPlayer().getName().toLowerCase();
		for (String nomeBloqueado : Settings.Nicks_Bloqueados) {
			if (nome.contains(nomeBloqueado)) {
				e.setKickMessage(Mensagens.Nick_Bloqueado.replace("%nick%", nomeBloqueado));
				e.setResult(Result.KICK_OTHER);
				return;
			}
		}
	}
	
}
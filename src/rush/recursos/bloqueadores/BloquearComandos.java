package rush.recursos.bloqueadores;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import rush.configuracoes.Mensagens;
import rush.configuracoes.Settings;

public class BloquearComandos implements Listener {

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void aoExecutarComando(PlayerCommandPreprocessEvent e) {
		String cmd = e.getMessage().toLowerCase().split(" ")[0];
		for (String blockedCmd : Settings.Lista_Dos_Comandos_Bloqueados) {
			if (blockedCmd.equals(cmd) || (cmd.split(":").length > 1 && blockedCmd.equals("/" + cmd.split(":")[1]))) {
				if (!(e.getPlayer().hasPermission("system.bypass.comandobloqueado"))) {
					e.getPlayer().sendMessage(Mensagens.Comando_Bloqueado);
					e.setCancelled(true);
					return;
				}
			}
		}
	}
	
}
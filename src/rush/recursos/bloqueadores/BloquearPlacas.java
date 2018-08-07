package rush.recursos.bloqueadores;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import rush.configuracoes.Mensagens;
import rush.configuracoes.Settings;

public class BloquearPlacas implements Listener {

	@EventHandler(priority = EventPriority.LOWEST)
	public void aoUsarPlaca(SignChangeEvent e) {
		if (!(e.getPlayer().hasPermission("system.bypass.placabloqueada"))) {

			if (Settings.Bloquear_Todas_As_Palavras) {
				e.setCancelled(true);
				e.getPlayer().sendMessage(Mensagens.Palavras_Desativadas_Na_Placa);
				return;
			}

			if (Settings.Bloquear_Apenas_Algumas_Palavras) {
				String[] linhas = e.getLines();
				for (int i = 0; i < linhas.length; i++) {
					String s = linhas[i];
					for (String palavra : Settings.Lista_Das_Palavras_Bloqueadas) {
						if (s.toLowerCase().contains(palavra.toLowerCase())) {
							e.setCancelled(true);
							e.getPlayer().sendMessage(Mensagens.Palavra_Bloqueada_Na_Placa.replace("%palavra%", palavra));
							e.getBlock().breakNaturally();
							return;
						}
					}
				}
			}
		}
	}
}
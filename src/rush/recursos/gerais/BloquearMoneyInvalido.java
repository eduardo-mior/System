package rush.recursos.gerais;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import rush.configuracoes.Mensagens;
import rush.configuracoes.Settings;

public class BloquearMoneyInvalido implements Listener {

	@EventHandler(priority = EventPriority.LOWEST)
	public void aoTentarVender(PlayerCommandPreprocessEvent e) {
		if (e.getMessage().contains("mercado vender -") || e.getMessage().contains("market vender -")) {
			e.getPlayer().sendMessage(Mensagens.Numero_Invalido.replace("%numero%", "-"));
			e.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onCommandEvent(PlayerCommandPreprocessEvent e) {
		String cmd = e.getMessage().toLowerCase();
		String[] args = cmd.split(" ");

		for (String cmdmoney : Settings.Comandos_Que_Envolvem_Money) {
			if (args[0].contains(cmdmoney)) {
				if (cmd.contains(" null") || cmd.contains(" nan") || cmd.contains(" -nan") || cmd.contains(" -null")) {
					e.getPlayer().sendMessage(Mensagens.Money_Null);
					e.setCancelled(true);
					return;
				}
			}
		}
	}
}

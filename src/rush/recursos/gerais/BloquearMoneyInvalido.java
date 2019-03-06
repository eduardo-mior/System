package rush.recursos.gerais;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import rush.configuracoes.Mensagens;
import rush.configuracoes.Settings;

public class BloquearMoneyInvalido implements Listener {

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void aoTentarVender(PlayerCommandPreprocessEvent e) {
		String fullCmd = e.getMessage().toLowerCase();
		if (fullCmd.contains("mercado vender -") || fullCmd.contains("market vender -")) {
			e.getPlayer().sendMessage(Mensagens.Numero_Invalido.replace("%numero%", "-"));
			e.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void aoTentarBugar(PlayerCommandPreprocessEvent e) {
		String fullCmd = e.getMessage().toLowerCase();
		String cmd = fullCmd.split(" ")[0];
		for (String moneyCmd : Settings.Comandos_Que_Envolvem_Money) {
			if (moneyCmd.equals(cmd) || (cmd.split(":").length > 1 && moneyCmd.equals("/" + cmd.split(":")[1]))) {
				if (fullCmd.contains(" null") || fullCmd.contains(" nan") || fullCmd.contains(" -nan") || fullCmd.contains(" -null")) {
					e.getPlayer().sendMessage(Mensagens.Money_Null);
					e.setCancelled(true);
					return;
				}
			}
		}
	}
	
}
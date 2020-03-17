package rush.sistemas.gerais;

import java.util.Map.Entry;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.scheduler.BukkitRunnable;

import rush.Main;
import rush.configuracoes.Mensagens;
import rush.configuracoes.Settings;
import rush.utils.TimeFormatter;

public class DelayComandos implements Listener {
	
	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
	public void aoExecutarComando(PlayerCommandPreprocessEvent e) {
		final String fullCmd = e.getMessage();
		String cmd = e.getMessage().toLowerCase().split(" ")[0];
		for (Entry<String, Long> delayCmd : Settings.Lista_Dos_Comandos_Com_Delay.entrySet()) {
			if (delayCmd.getKey().equals(cmd) || (cmd.split(":").length > 1 && delayCmd.getKey().equals("/" + cmd.split(":")[1]))) {
				final Player p = e.getPlayer();
				if (!p.hasPermission("system.bypass.comandodelay")) {
					p.sendMessage(Mensagens.Aguarde_Delay_Comandos.replace("%comando%", cmd).replace("%tempo%", TimeFormatter.format(delayCmd.getValue() * 1000L)));
					new BukkitRunnable() {
						@Override
						public void run() {
							if (p != null && p.isOnline()) {
								p.performCommand(fullCmd);
							}
						}
					}.runTaskLater(Main.get(), 20L * delayCmd.getValue());
					e.setCancelled(true);	
				}
				return;
			}
		}
	}
	
}
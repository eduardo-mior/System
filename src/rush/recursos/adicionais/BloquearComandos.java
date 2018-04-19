package rush.recursos.adicionais;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import rush.utils.ConfigManager;

public class BloquearComandos implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void aoExecutarComando(final PlayerCommandPreprocessEvent e) {
        final String cmd = e.getMessage().toLowerCase();
        final Player p = e.getPlayer();
        for (final String cmds : ConfigManager.getConfig("settings").getStringList("Lista-Dos-Comandos-Bloqueados")) {
            	if (cmd.equals(cmds) || cmd.startsWith(cmds + " ")) {
                    if (!(p.hasPermission("system.bypass.comandobloqueado"))) {
            		p.sendMessage(ConfigManager.getConfig("mensagens").getString("Comando-Bloqueado").replaceAll("&", "§"));
            		e.setCancelled(true);
            	}
            }
        }
    }
}

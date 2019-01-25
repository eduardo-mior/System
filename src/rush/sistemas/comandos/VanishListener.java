package rush.sistemas.comandos;

import java.util.HashSet;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

@SuppressWarnings("all")
public class VanishListener implements Listener {

	public static HashSet<Player> VANISH = new HashSet<>();
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (!p.hasPermission("system.vanish.bypass")) {
			for (Player vanished : VANISH) {
				p.hidePlayer(vanished);
			}
		}
	}
}

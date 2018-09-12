package rush.sistemas.comandos;

import java.util.HashSet;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffectType;

@SuppressWarnings("all")
public class VanishListener implements Listener {

	public static HashSet<Player> VANISH = new HashSet<>();
	
	@EventHandler
	public void onLogin(PlayerJoinEvent e) {
		if (!e.getPlayer().hasPermission("system.vanish.bypass")) {
			for (Player p : VANISH) {
				e.getPlayer().hidePlayer(p);
			}
		}
	}
}

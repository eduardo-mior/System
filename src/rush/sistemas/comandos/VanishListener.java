package rush.sistemas.comandos;

import java.util.HashSet;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffectType;

import rush.utils.Utils;

public class VanishListener implements Listener {

	public static HashSet<Player> VANISHEDS = new HashSet<>();
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		if (Utils.hasPotionEffect(p, PotionEffectType.INVISIBILITY, 50)) {
			p.removePotionEffect(PotionEffectType.INVISIBILITY);
		}
		
		if (!p.hasPermission("system.vanish.bypass")) {
			for (Player vanished : VANISHEDS) {
				p.hidePlayer(vanished);
			}
		}
	}
	
}
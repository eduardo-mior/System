package rush.recursos.adicionais;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class CoresNaPlaca implements Listener {

	@EventHandler
	public void aoUsarPlaca(SignChangeEvent e) {
		Player p = e.getPlayer();
		if (p.hasPermission("system.cornaplaca")) {
			for (int i = 0; i < 4; i++) {
				e.setLine(i, ChatColor.translateAlternateColorCodes('&', e.getLines()[i]));
			}
		}
	}
}

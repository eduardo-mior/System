package rush.recursos.antibug;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;

public class BloquearNameTag implements Listener {
	
	@EventHandler(priority = EventPriority.LOW)
	public void onInteract(PlayerInteractAtEntityEvent e) {
		Player p = e.getPlayer();
		ItemStack itemStack = p.getItemInHand();
		if (itemStack.getType().equals(Material.NAME_TAG)) {
			if (!(p.hasPermission("system.bypass.usarnametag"))) {
				e.setCancelled(true);
			}
		}
	}
}

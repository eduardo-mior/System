package rush.recursos.antibug;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;

public class BloquearNameTag implements Listener {
	
	@EventHandler(priority = EventPriority.LOW)
	public void onInteract(PlayerInteractAtEntityEvent event) {
		ItemStack itemStack = event.getPlayer().getItemInHand();
		if(itemStack == null) return;
		if (itemStack.getType().equals(Material.NAME_TAG)) {
			event.setCancelled(true);
		}
	}

}

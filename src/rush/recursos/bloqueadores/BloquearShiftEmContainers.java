package rush.recursos.bloqueadores;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryType.SlotType;

import rush.configuracoes.Mensagens;
import rush.configuracoes.Settings;

public class BloquearShiftEmContainers implements Listener {

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void aoUsarShift(InventoryClickEvent e) {
		if (e.getSlotType() == SlotType.OUTSIDE || e.getCurrentItem().getType() == Material.AIR	|| e.getCurrentItem() == null) {
			return;
		}

		if (e.getClick().isShiftClick()) {
			for (String container : Settings.Bloquear_Shift_Em_Containers_Containers) {
				if (e.getInventory().getType() == InventoryType.valueOf(container)) {
					if (!(e.getWhoClicked().hasPermission("system.bypass.shiftemcontainer"))) {
						Player p = (Player) e.getWhoClicked();
						p.sendMessage(Mensagens.Shift_Bloqueado_No_Container.replace("%tipo%", container));
						e.setCancelled(true);
						return;
					}
				}
			}
		}
	}
}

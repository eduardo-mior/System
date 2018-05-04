package rush.recursos.antibug;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.Listener;

import rush.utils.ConfigManager;

public class BloquearShiftEmContainers implements Listener {

	@EventHandler
	public void aoUsarShift(InventoryClickEvent e) {
		if (e.getSlotType().equals(SlotType.OUTSIDE) || e.getCurrentItem().getType() == Material.AIR || e.getCurrentItem() == null) {
			return;
		}
		
		if ((e.getClick() == ClickType.SHIFT_LEFT || e.getClick() == ClickType.SHIFT_RIGHT)) {
			for (String containers : ConfigManager.getConfig("settings").getStringList("Bloquear-Shift-Em-Containers.Containers")) {
				if (e.getInventory().getType().equals(InventoryType.valueOf(containers))) {
					HumanEntity p = e.getWhoClicked();
					if (!(p.hasPermission("system.bypass.shiftemcontainer"))) {
						e.setCancelled(true);
						p.sendMessage(ConfigManager.getConfig("mensagens").getString("Shift-Bloqueado-No-Container")
							.replaceAll("%tipo%", String.valueOf(e.getInventory().getType()))
							.replaceAll("ANVIL", "na bigorna")
							.replaceAll("BEACON", "no sinalizdor")
							.replaceAll("BREWING", "no suporte de poções")
							.replaceAll("CRAFTING", "no inventario")
							.replaceAll("DISPENSER", "no ejetor")
							.replaceAll("DROPPER", "no liberador")
							.replaceAll("ENCHANTING", "na mesa de encantamentos")
							.replaceAll("ENDER_CHEST", "no echest")
							.replaceAll("FURNACE", "no fornalha")
							.replaceAll("HOPPER", "no funil")
							.replaceAll("MERCHANT", "no aldeão")
							.replaceAll("WORKBENCH", "na bancada de trabalho")
							.replaceAll("CHEST", "no baú")
							.replaceAll("&", "§"));
							return;
					}
				}
			}
		}
	}
}

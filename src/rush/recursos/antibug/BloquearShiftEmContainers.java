package rush.recursos.antibug;

import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.Listener;
import rush.Main;

public class BloquearShiftEmContainers implements Listener {

	@EventHandler
	public void aoUsarShift(InventoryClickEvent e) {
	for (String containers : Main.aqui.getConfig().getStringList("Bloquear-Shift-Em-Containers.Containers"))
    if (e.getInventory().getType().equals((Object)InventoryType.valueOf(containers))) {
        if (e.getClick() == ClickType.SHIFT_LEFT || e.getClick() == ClickType.SHIFT_RIGHT) {
            e.setCancelled(true);
            e.getWhoClicked().sendMessage(Main.aqui.getMensagens().getString("Shift-Bloqueado-No-Container")
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
        e.setCancelled(false);
    }
  }
}

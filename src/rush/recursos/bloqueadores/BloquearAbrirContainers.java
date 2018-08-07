package rush.recursos.bloqueadores;

import org.bukkit.event.EventHandler;

import rush.configuracoes.Settings;

import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.Listener;

public class BloquearAbrirContainers implements Listener {

	@EventHandler
	public void aoAbrirContainer(InventoryOpenEvent e) {
		for (String container : Settings.Bloquear_Abrir_Containers_Containers) {
			if (e.getInventory().getType() == InventoryType.valueOf(container)) {
				if (!(e.getPlayer().hasPermission("system.bypass.containerbloqueado"))) {
					e.setCancelled(true);
					return;
				}
			}
		}
	}
}
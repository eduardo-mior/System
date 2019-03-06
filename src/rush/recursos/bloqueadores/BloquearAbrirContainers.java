package rush.recursos.bloqueadores;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryOpenEvent;

import rush.configuracoes.Settings;

import org.bukkit.event.Listener;

public class BloquearAbrirContainers implements Listener {

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void aoAbrirContainer(InventoryOpenEvent e) {
		if (Settings.Bloquear_Abrir_Containers_Containers.contains(e.getInventory().getType())) {
			if (!(e.getPlayer().hasPermission("system.bypass.containerbloqueado"))) {
				e.setCancelled(true);
				return;
			}
		}
	}
	
}
package rush.recursos.antibug;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleEnterEvent;

public class BloquearSubirEmVeiculos implements Listener {

	@EventHandler
    public void aoEntrarNoVeiculo(VehicleEnterEvent e) {
		if (e.getEntered() instanceof Player ) {
			if (e.getEntered().hasPermission("system.bypass.entraremveiculos")) {
				e.setCancelled(true);
			}
		}
	}
}

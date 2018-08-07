package rush.recursos.desativadores;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public class DesativarMobsNaturais implements Listener {

	@EventHandler(priority = EventPriority.LOWEST)
	public void aoSpawnarMob(CreatureSpawnEvent e) {
		if (e.getSpawnReason() != SpawnReason.SPAWNER && e.getSpawnReason() != SpawnReason.SPAWNER_EGG && e.getSpawnReason() != SpawnReason.CUSTOM) {
			e.setCancelled(true);
		}
	}
}

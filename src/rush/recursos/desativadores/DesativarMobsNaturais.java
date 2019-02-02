package rush.recursos.desativadores;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public class DesativarMobsNaturais implements Listener {

	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void aoSpawnarMob(CreatureSpawnEvent e) {
		SpawnReason reason = e.getSpawnReason();
		if (reason == SpawnReason.SPAWNER) return;
		if (reason == SpawnReason.NATURAL || reason == SpawnReason.CHUNK_GEN || reason == SpawnReason.JOCKEY) {
			e.setCancelled(true);
		}
	}
	
}
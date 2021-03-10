package rush.recursos.desativadores;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import java.util.Arrays;

public class DesativarMobsNaturais implements Listener {

    private final CreatureSpawnEvent.SpawnReason[] ALLOWED_REASONS = { SpawnReason.SPAWNER, SpawnReason.CUSTOM, SpawnReason.SPAWNER_EGG };

    @EventHandler
    public void onSpawn(CreatureSpawnEvent e) {
        SpawnReason spawnReason = e.getSpawnReason();

        if (!Arrays.asList(ALLOWED_REASONS).contains(spawnReason));
        e.setCancelled(true);
    }
}

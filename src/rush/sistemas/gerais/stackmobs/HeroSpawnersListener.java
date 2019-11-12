package rush.sistemas.gerais.stackmobs;

import com.heroslender.herospawners.api.events.SpawnerSpawnStackEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.metadata.FixedMetadataValue;
import rush.Main;
import rush.configuracoes.Settings;
import rush.enums.EntityName;

public class HeroSpawnersListener implements Listener {

    @EventHandler
    private void onSpawnerSpawn(SpawnerSpawnStackEvent e) {
        Location location = e.getSpawner().getLocation();

        Entity entity = location.getWorld().spawnEntity(location.add(1.5, 0, 0), e.getEntityType());

        String entityDisplayname = Settings.Nome_Dos_Mobs
                .replace("%tipo%", EntityName.valueOf(e.getEntityType()).getName())
                .replace("%quantia%", Integer.toString(e.getStackSize()));

        entity.setCustomName(entityDisplayname);
        entity.setCustomNameVisible(true);
        entity.setMetadata("stack", new FixedMetadataValue(Main.get(), e.getStackSize()));

        CreatureSpawnEvent event = new CreatureSpawnEvent((LivingEntity) entity, CreatureSpawnEvent.SpawnReason.CUSTOM);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            entity.remove();
        }

        e.setCancelled(true);
    }
}

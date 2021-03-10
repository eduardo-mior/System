package rush.recursos.desativadores;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;

public class DesativarRastreamentoMobs implements Listener {

    @EventHandler
    public void onTarget(EntityTargetEvent e) {
        Entity entityTarget = e.getTarget();

        if (entityTarget.getType() == EntityType.PLAYER) {
            e.setCancelled(true);
        }
    }
}

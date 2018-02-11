package rush.recursos.gerais;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class DesativarDanoDoEnderDragon implements Listener {

    @EventHandler
    public void aoQuebrarOsBlocos(final EntityExplodeEvent e) {
        if (e.getEntity().getType() == EntityType.ENDER_DRAGON) {
            e.setCancelled(true);
      }
    }
}

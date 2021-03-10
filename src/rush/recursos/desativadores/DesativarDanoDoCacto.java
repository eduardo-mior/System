package rush.recursos.desativadores;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class DesativarDanoDoCacto implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByBlockEvent e) {
        DamageCause damageCause = e.getCause();

        if (damageCause == DamageCause.CONTACT) {
            e.setCancelled(true);
        }
    }
}

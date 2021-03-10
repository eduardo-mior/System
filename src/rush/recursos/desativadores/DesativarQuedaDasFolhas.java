package rush.recursos.desativadores;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.LeavesDecayEvent;

public class DesativarQuedaDasFolhas.java implements Listener {

    @EventHandler
    public void onDecay(LeavesDecayEvent e) {
        e.setCancelled(true);
    }
}

package rush.recursos.desativadores;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockIgniteEvent.IgniteCause;
import org.bukkit.event.block.BlockSpreadEvent;

public class DesativarPropagacaoDoFogo implements Listener {

    @EventHandler
    public void onIgnite(BlockIgniteEvent e) {
        IgniteCause igniteCause = e.getCause();

        if (igniteCause.equals(IgniteCause.SPREAD) & igniteCause.equals(IgniteCause.LAVA)) {
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onSpread(BlockSpreadEvent e) {
        e.setCancelled(true);
    }
}

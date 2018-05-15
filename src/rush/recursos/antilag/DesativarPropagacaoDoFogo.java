package rush.recursos.antilag;

import org.bukkit.event.block.*;
import org.bukkit.event.block.BlockIgniteEvent.IgniteCause;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class DesativarPropagacaoDoFogo implements Listener {

    @EventHandler
    public void aoPegarFogo(final BlockBurnEvent e) {
    	e.setCancelled(true);
    }
    
    @EventHandler
    public void aoEspalharFogo(final BlockIgniteEvent e) {
        if (e.getCause() == IgniteCause.LAVA || e.getCause() == IgniteCause.SPREAD)
        	e.setCancelled(true);
      }
}
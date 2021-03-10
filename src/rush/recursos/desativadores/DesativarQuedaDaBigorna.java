package rush.recursos.desativadores;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class DesativarQuedaDaBigorna implements Listener {

    @EventHandler
    public void onChange(EntityChangeBlockEvent e) {
        Block block = e.getBlock();
        Entity entity = e.getEntity();

        if (entity.getType() == EntityType.FALLING_BLOCK & block.getType() == Material.ANVIL) {
            e.setCancelled(true);
            block.getState().update(false, false);
        }
    }
}

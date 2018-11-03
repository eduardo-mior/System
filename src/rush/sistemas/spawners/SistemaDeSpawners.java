package rush.sistemas.spawners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import rush.configuracoes.Mensagens;

public class SistemaDeSpawners implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void aoQuebrarSpawner(BlockBreakEvent e) {
		Player p = e.getPlayer();
		Block b = e.getBlock();
		if (b.getType() == Material.MOB_SPAWNER) {
			if (p.getItemInHand().getType().name().contains("PICKAXE")) {
				if (p.getItemInHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
					String type = ((CreatureSpawner) b.getState()).getSpawnedType().name();
					ItemStack spawner = MobSpawner.get(type, 1);
					boolean droped = false;
					for (ItemStack is : p.getInventory().addItem(spawner).values()) {
						p.getWorld().dropItem(p.getLocation(), is);
						droped = true;
					}
					p.updateInventory();
					if (droped)
						p.sendMessage(Mensagens.Inventario_Cheio_Quebrou);
					b.setType(Material.AIR);
					e.setExpToDrop(0);
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void aoColocarSpawner(BlockPlaceEvent e) {
		if (e.getBlock().getType() == Material.MOB_SPAWNER) {
			try {
				CreatureSpawner cs = (CreatureSpawner) e.getBlock().getState();
				cs.setSpawnedType(EntityType.valueOf(e.getItemInHand().getItemMeta().getLore().get(0)));
			} catch (Exception ex) {
				e.setCancelled(true);
				e.getPlayer().sendMessage(Mensagens.Spawner_Bugado);
			}
		}
	}
}

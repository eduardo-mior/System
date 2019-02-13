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

public class SistemaDeSpawnersOLD implements Listener {

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void aoQuebrarSpawner(BlockBreakEvent e) {
		Player p = e.getPlayer();
		Block b = e.getBlock();
		if (b.getType() == Material.MOB_SPAWNER) {
			if (p.getItemInHand().getType().name().contains("PICKAXE")) {
				if (p.getItemInHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
					CreatureSpawner mobSpawner = (CreatureSpawner) b.getState();
					String type = mobSpawner.getSpawnedType().name();
					ItemStack spawner = MobSpawner.getOld(type, 1);
					for (ItemStack is : p.getInventory().addItem(spawner).values()) {
						b.getWorld().dropItem(b.getLocation(), is);
						p.sendMessage(Mensagens.Inventario_Cheio_Quebrou);
					}
					e.setExpToDrop(0);
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void aoColocarSpawner(BlockPlaceEvent e) {
		Block b = e.getBlock();
		if (b.getType() == Material.MOB_SPAWNER) {
			try {
				CreatureSpawner mobSpawner = (CreatureSpawner) b.getState();
				short type = e.getItemInHand().getDurability();
				mobSpawner.setSpawnedType(EntityType.fromId(type));
				mobSpawner.update(true);
			} catch (Throwable ex) {
				e.getPlayer().sendMessage(Mensagens.Spawner_Bugado);
			}
		}
	}
	
}
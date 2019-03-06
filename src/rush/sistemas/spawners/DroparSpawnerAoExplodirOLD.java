package rush.sistemas.spawners;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.ItemStack;

import rush.configuracoes.Settings;

public class DroparSpawnerAoExplodirOLD implements Listener {

	private static Random rnd = new Random();

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void aoExplodir(EntityExplodeEvent e) {
		for (Block b : e.blockList()) {
			if (b.getType() == Material.MOB_SPAWNER && rnd.nextInt(100) <= Settings.Chance_De_Dropar_Ao_Explodir) {
				String type = ((CreatureSpawner) b.getState()).getSpawnedType().name();
				ItemStack spawner = MobSpawner.getOld(type, 1);
				b.getWorld().dropItem(b.getLocation(), spawner);
			}
		}
	}
	
}
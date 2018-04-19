package rush.sistemas.spawners;

import java.util.Arrays;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import rush.utils.ConfigManager;

public class DroparSpawnerAoExplodir implements Listener {

	private static Random random = new Random();
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void aoExplodir(final EntityExplodeEvent e) {
		if (!e.isCancelled()) {
			for (final Block b : e.blockList()) {
				if (b.getType() == Material.MOB_SPAWNER && random.nextInt(100) <= (ConfigManager.getConfig("settings").getInt("Chance-De-Dropar-Ao-Explodir"))) {
	               final ItemStack is = new ItemStack(Material.MOB_SPAWNER);
	               final ItemMeta im = is.getItemMeta();
	               im.setDisplayName(ConfigManager.getConfig("mensagens").getString("Nome-Do-MobSpawner").replace("&", "§"));
	               im.setLore(Arrays.asList(((CreatureSpawner)b.getState()).getCreatureTypeName()
	            		    .replace("Pig", ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Porco")
				    		.replace("Chicken", ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Galinha")
				    		.replace("Sheep", ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Ovelha")
				    		.replace("Cow", ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Vaca")
				    		.replace("Skeleton", ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Esqueleto")
				    		.replace("CaveSpider", ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Aranha da Caverna")
				    		.replace("Spider", ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Aranha")
				    		.replace("Creeper", ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Creeper")
				    		.replace("Rabbit", ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Coelho")
				    		.replace("LavaSlime", ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Cubo de Magma")
				    		.replace("VillagerGolem", ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Iron Golem")
				    		.replace("WitherBoss", ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Wither")
				    		.replace("Slime", ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Slime")
				    		.replace("Enderman", ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Enderman")
				    		.replace("Zombie", ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Zumbi")
				    		.replace("PigZombie", ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Zumbi Pigman")
				    		.replace("Blaze", ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Blaze")
				    		));
	               is.setItemMeta(im);
                b.getWorld().dropItem(b.getLocation(), is);
				}
			}
		}
	}
}

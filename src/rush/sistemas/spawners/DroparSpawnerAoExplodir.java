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

import rush.Main;

public class DroparSpawnerAoExplodir implements Listener {

	private static Random random = new Random();
	
    @EventHandler(priority = EventPriority.MONITOR)
    public void aoExplodir(final EntityExplodeEvent e) { 	// Ao explodir o MobSpawner
	     if (!e.isCancelled()) {
         for (final Block b : e.blockList()) {
            if (b.getType() == Material.MOB_SPAWNER && random.nextInt(100) <= (Main.aqui.getConfig().getInt("Chance-De-Dropar-Ao-Explodir"))) {
	             final ItemStack is = new ItemStack(Material.MOB_SPAWNER);
	             final ItemMeta im = is.getItemMeta();
	               im.setDisplayName(Main.aqui.getMensagens().getString("Nome-Do-MobSpawner").replace("&", "§"));
	               im.setLore(Arrays.asList(((CreatureSpawner)b.getState()).getCreatureTypeName()
	            		    .replace("Pig", Main.aqui.getMensagens().getString("Lore-Do-MobSpawner") + "Porco")
				    		.replace("Chicken", Main.aqui.getMensagens().getString("Lore-Do-MobSpawner") + "Galinha")
				    		.replace("Sheep", Main.aqui.getMensagens().getString("Lore-Do-MobSpawner") + "Ovelha")
				    		.replace("Cow", Main.aqui.getMensagens().getString("Lore-Do-MobSpawner") + "Vaca")
				    		.replace("Skeleton", Main.aqui.getMensagens().getString("Lore-Do-MobSpawner") + "Esqueleto")
				    		.replace("CaveSpider", Main.aqui.getMensagens().getString("Lore-Do-MobSpawner") + "Aranha da Caverna")
				    		.replace("Spider", Main.aqui.getMensagens().getString("Lore-Do-MobSpawner") + "Aranha")
				    		.replace("Creeper", Main.aqui.getMensagens().getString("Lore-Do-MobSpawner") + "Creeper")
				    		.replace("Rabbit", Main.aqui.getMensagens().getString("Lore-Do-MobSpawner") + "Coelho")
				    		.replace("VillagerGolem", Main.aqui.getMensagens().getString("Lore-Do-MobSpawner") + "Iron Golem")
				    		.replace("WitherBoss", Main.aqui.getMensagens().getString("Lore-Do-MobSpawner") + "Wither")
				    		.replace("Slime", Main.aqui.getMensagens().getString("Lore-Do-MobSpawner") + "Slime")
				    		.replace("Enderman", Main.aqui.getMensagens().getString("Lore-Do-MobSpawner") + "Enderman")
				    		.replace("Zombie", Main.aqui.getMensagens().getString("Lore-Do-MobSpawner") + "Zumbi")
				    		.replace("PigZombie", Main.aqui.getMensagens().getString("Lore-Do-MobSpawner") + "Zumbi Pigman")
				    		.replace("Blaze", Main.aqui.getMensagens().getString("Lore-Do-MobSpawner") + "Blaze")
				    		));
	               is.setItemMeta(im);
                b.getWorld().dropItem(b.getLocation(), is);
            }
         }
	   }
    }
}

package rush.sistemas.spawners;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import rush.utils.ConfigManager;

public class SistemaDeSpawners implements Listener {
	   
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.MONITOR)
	public static void quebrouSpawner(final BlockBreakEvent e) {
		Player p = e.getPlayer();
	    Block b = e.getBlock();
	    if (b.getType() == Material.MOB_SPAWNER && p.getItemInHand().getType().name().contains("PICKAXE") && p.getItemInHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH) && !e.isCancelled()) {
	    	e.setCancelled(true);
	            final ItemStack drop = new ItemStack(b.getType());
	            final CreatureSpawner cs = (CreatureSpawner)b.getState();
	            final ItemMeta imd = drop.getItemMeta();
	            imd.setDisplayName(ConfigManager.getConfig("mensagens").getString("Nome-Do-MobSpawner").replaceAll("&", "§"));
			    imd.setLore(Arrays.asList(cs.getCreatureTypeName()
			    		.replace("Pig", ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Porco")
			    		.replace("Chicken", ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Galinha")
			    		.replace("Sheep", ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Ovelha")
			    		.replace("Cow", ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Vaca")
			    		.replace("Skeleton", ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Esqueleto")
			    		.replace("CaveSpider", ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Aranha da Caverna")
			    		.replace("Spider", ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Aranha")
			    		.replace("Creeper", ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Creeper")
			    		.replace("Rabbit", ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Coelho")
			    		.replace("VillagerGolem", ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Iron Golem")
			    		.replace("LavaSlime", ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Cubo de Magma")
			    		.replace("WitherBoss", ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Wither")
			    		.replace("Slime", ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Slime")
			    		.replace("Enderman", ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Enderman")
			    		.replace("Zombie", ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Zumbi")
			    		.replace("PigZombie", ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Zumbi Pigman")
			    		.replace("Blaze", ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Blaze")
			    		));
			    drop.setItemMeta(imd);
	            boolean droped = false;
	            for (final ItemStack is : p.getInventory().addItem(new ItemStack[] { drop }).values()) {
	                p.getWorld().dropItem(p.getLocation(), is);
	                droped = true;
	            }
	            p.updateInventory();
	            if (!droped) {
	            }
	            else {
	                p.sendMessage(ConfigManager.getConfig("mensagens").getString("Inventario-Cheio-Quebrou").replaceAll("&", "§"));
	            }
	            b.setType(Material.AIR);
	        }
	    }

	@EventHandler(priority = EventPriority.HIGHEST)
	public static void colocouSpawner(final BlockPlaceEvent e) {
		Block b = e.getBlock();
		if (b.getType() == Material.MOB_SPAWNER) {
			if (e.getItemInHand().hasItemMeta() && e.getItemInHand().getItemMeta().hasLore()) {
				final CreatureSpawner cs = (CreatureSpawner)b.getState();
	                cs.setCreatureTypeByName((String)e.getItemInHand().getItemMeta().getLore().get(0)
				    		.replace(ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Porco", "Pig")
				    		.replace(ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Galinha", "Chicken")
				    		.replace(ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Ovelha", "Sheep")
				    		.replace(ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Vaca", "Cow")
				    		.replace(ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Esqueleto", "Skeleton")
				    		.replace(ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Aranha da Caverna", "CaveSpider")
				    		.replace(ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Aranha", "Spider")
				    		.replace(ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Cubo de Magma", "LavaSlime")
				    		.replace(ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Creeper", "Creeper")
				    		.replace(ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Coelho", "Rabbit")
				    		.replace(ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Iron Golem", "VillagerGolem")
				    		.replace(ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Wither", "WitherBoss")
				    		.replace(ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Slime", "Slime")
				    		.replace(ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Enderman", "Enderman")
				    		.replace(ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Zumbi", "Zombie")
				    		.replace(ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Zumbi Pigman", "PigZombie")
				    		.replace(ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner") + "Blaze", "Blaze")
	                		);
	            } else {
	                e.setCancelled(true);
	                e.getPlayer().sendMessage(ConfigManager.getConfig("mensagens").getString("Spawner-Bugado").replaceAll("&", "§"));
	            }
		}
	}
}

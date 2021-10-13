package rush.sistemas.gerais;

import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import rush.Main;
import rush.configuracoes.Settings;
import rush.enums.EntityName;

public class StackMobs implements Listener {
	
	private static int MAX_STACK = Settings.Limite_De_Mobs_Agrupados;
	private static String NAME = Settings.Nome_Dos_Mobs;
	private static boolean KILL_ALL = Settings.Kill_All;
	private static double RANGE = Settings.Raio_De_Distancia;
	private static List<EntityType> WHITE_LIST = Settings.Lista_De_Mobs_Que_Nao_Agrupam;
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public void onSpawn(CreatureSpawnEvent e) {
		
		LivingEntity spawned = e.getEntity();
		boolean stack = spawned.hasMetadata("stack");
		SpawnReason reason = e.getSpawnReason();
		EntityType spawnedType = e.getEntityType();

		if ((reason == SpawnReason.EGG) || (reason == SpawnReason.CUSTOM && !stack) || (WHITE_LIST.contains(spawnedType))) return;
		
		
		for (Entity entity : spawned.getNearbyEntities(RANGE, RANGE, RANGE)) {
			if (entity.getType() == spawnedType && !entity.isDead()) {
				e.setCancelled(true);
				int amount = 1;
				LivingEntity living = (LivingEntity) entity;
				
				if (entity.hasMetadata("stack")) 
					amount += entity.getMetadata("stack").isEmpty() ? 0 : entity.getMetadata("stack").get(0).asInt();
				
				if (stack) 
					amount += living.getMetadata("stack").isEmpty() ? 0 : living.getMetadata("stack").get(0).asInt();
				
				if (amount > MAX_STACK) {
					e.setCancelled(true);
					return;
				}
				
				String type = EntityName.valueOf(e.getEntityType()).getName();
				living.setCustomName(NAME.replace("%tipo%", type).replace("%quantia%", String.valueOf(amount)));
				living.setCustomNameVisible(true);
				living.setMetadata("stack",  new FixedMetadataValue(Main.get(), amount));
				return;
			}
		}
		
		if (!spawned.hasMetadata("stack")) {
			String type = EntityName.valueOf(e.getEntityType()).getName();
			spawned.setCustomName(NAME.replace("%tipo%", type).replace("%quantia%", String.valueOf(1)));
			spawned.setCustomNameVisible(true);
			spawned.setMetadata("stack",  new FixedMetadataValue(Main.get(), 1));
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onDeath(EntityDeathEvent e) {
		LivingEntity entity = e.getEntity();
		if (entity.hasMetadata("stack")) {
			int cont = entity.getMetadata("stack").isEmpty() ? 0 : entity.getMetadata("stack").get(0).asInt();
			if (cont > 1) {
				if (KILL_ALL && entity.getKiller() != null && !entity.getKiller().isSneaking()) {
					e.setDroppedExp(e.getDroppedExp() * cont);
					for (ItemStack drop : e.getDrops()) {
						if (drop.getType().getMaxDurability() == 0) {
							drop.setAmount(drop.getAmount() * cont);
						}
					}
				} else {
					String type = EntityName.valueOf(e.getEntityType()).getName();
					LivingEntity spawned = (LivingEntity) entity.getWorld().spawnEntity(entity.getLocation(), e.getEntityType());
					spawned.setCustomName(NAME.replace("%tipo%", type).replace("%quantia%", String.valueOf(--cont)));
					spawned.setCustomNameVisible(true);
					spawned.setMetadata("stack", new FixedMetadataValue(Main.get(), cont));
				}
			}
		}
	}
	
}
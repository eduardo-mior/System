package rush.sistemas.spawners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import rush.apis.ItemAPI;
import rush.configuracoes.Settings;
import rush.enums.EntityName;

public class MobSpawner {
	
	public static ItemStack get(String type, int amount) {
		ItemStack spawner = new ItemStack(Material.MOB_SPAWNER, amount);
		ItemMeta meta = spawner.getItemMeta();
		String entityName = EntityName.valueOf(type).getName();
		List<String> lore = new ArrayList<>();
		for (String line : Settings.Lore_Do_Spawner) lore.add(line.replace("%tipo%", entityName));
        meta.setDisplayName(Settings.Nome_Do_Spawner.replace("%tipo%", entityName));
        meta.setLore(lore);
        spawner.setItemMeta(meta);
        return ItemAPI.saveInfo(spawner, "Entity", type);
	}
	
	@SuppressWarnings("deprecation")
	public static ItemStack getOld(String type, int amount) {
		ItemStack spawner = new ItemStack(Material.MOB_SPAWNER, amount);
		ItemMeta meta = spawner.getItemMeta();
		short entityId = EntityType.valueOf(type).getTypeId();
		String entityName = EntityName.valueOf(type).getName();
		List<String> lore = new ArrayList<>();
		for (String line : Settings.Lore_Do_Spawner) lore.add(line.replace("%tipo%", entityName));
        meta.setDisplayName(Settings.Nome_Do_Spawner.replace("%tipo%", entityName));
        meta.setLore(lore);
        spawner.setItemMeta(meta);
        spawner.setDurability(entityId);
        return spawner;
	}
	
}
package rush.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class PlayerHead {

	public static ItemStack get(String dono) {
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setOwner(dono);
		skull.setItemMeta(meta);
		return skull;
	}
	
	public static ItemStack get(Player player) {
		return get(player.getName());
	}
	
}
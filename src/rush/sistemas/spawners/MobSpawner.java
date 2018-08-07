package rush.sistemas.spawners;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import rush.configuracoes.Mensagens;

public class MobSpawner {
	
	public static ItemStack get(String type, int amount) {
		ItemStack spawner = new ItemStack(Material.MOB_SPAWNER);
		ItemMeta meta = spawner.getItemMeta();
        meta.setDisplayName(Mensagens.Nome_Do_MobSpawner);
        meta.setLore(Arrays.asList(type));
        spawner.setItemMeta(meta);
        spawner.setAmount(amount);
        return spawner;
	}

}

package rush.sistemas.gerais;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import rush.configuracoes.Settings;

@SuppressWarnings("all")
public class DroparCabecaAoMorrer implements Listener {

	private static Random rnd = new Random();
	
	@EventHandler
	public void aoMorrerDroparCabeca(PlayerDeathEvent e) {
		int aleatorio = rnd.nextInt(100);
		int chance = Settings.Chance_De_Dropar_Cabeca_Ao_Morrer;
		if (aleatorio < chance) {
			Player p = e.getEntity();
			ItemStack skull = getSkull(p.getName());
			p.getWorld().dropItem(p.getLocation(), skull);
		}
	}

	private ItemStack getSkull(String player) {
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setOwner(player);
		skull.setItemMeta(meta);
		return skull;
	}
}
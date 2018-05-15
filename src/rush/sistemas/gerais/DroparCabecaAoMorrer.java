package rush.sistemas.gerais;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import rush.utils.ConfigManager;

public class DroparCabecaAoMorrer implements Listener {
	
	@EventHandler
	public void aoMorrer(PlayerDeathEvent e) {
        Player p = e.getEntity();
        Random rnd = new Random();
        int n = rnd.nextInt(100);
        int chance = ConfigManager.getConfig("settings").getInt("Chance-De-Dropar-Cabeca-Ao-Morrer");
        if (n < chance) {
        	ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
        	SkullMeta meta = (SkullMeta)skull.getItemMeta();
 	       	meta.setOwner(p.getName());
 	       	skull.setItemMeta(meta);
        	p.getWorld().dropItem(p.getLocation(),skull);
        }
	}
}
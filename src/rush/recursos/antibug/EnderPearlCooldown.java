package rush.recursos.antibug;

import java.sql.Timestamp;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;

import rush.Main;

public class EnderPearlCooldown  implements Listener {
	
    HashMap<Player, Timestamp> cooldown = new HashMap<Player, Timestamp>();

@EventHandler
public void aoJogarEnder(ProjectileLaunchEvent e) {
	if (!(e.getEntity().getShooter() instanceof Player)) {
		return;
	}
	Player p = (Player) e.getEntity().getShooter();
	
	if (e.getEntityType() != EntityType.ENDER_PEARL) {
		return;
	}
	
	if (cooldown.containsKey(p) && cooldown.get(p).after(new Timestamp(System.currentTimeMillis()))) {
		e.setCancelled(true);
		p.sendMessage(Main.aqui.getMensagens().getString("Aguarde-EnderPearl-Cooldown").replaceAll("&", "§").replaceAll("%tempo%", String.valueOf(Main.aqui.getConfig().getInt("EnderPearl-Cooldown.Cooldown"))));
		p.getInventory().addItem(new ItemStack[] { new ItemStack(Material.ENDER_PEARL) });
		return;
	} else {
		cooldown.remove(p);
	}
	
	e.setCancelled(false);
	
	cooldown.put(p, new Timestamp(System.currentTimeMillis()+1000*(Main.aqui.getConfig().getInt("EnderPearl-Cooldown.Cooldown"))));
}
}
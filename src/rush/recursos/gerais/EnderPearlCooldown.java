package rush.recursos.gerais;

import java.sql.Timestamp;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;

import rush.configuracoes.Mensagens;
import rush.configuracoes.Settings;

@SuppressWarnings("all")
public class EnderPearlCooldown implements Listener {

	HashMap<Player, Timestamp> cooldown = new HashMap<Player, Timestamp>();

	@EventHandler
	public void aoJogarEnder(ProjectileLaunchEvent e) {
		if (!(e.getEntity().getShooter() instanceof Player)) return;
		if (e.getEntityType() != EntityType.ENDER_PEARL) return;

		Player p = (Player) e.getEntity().getShooter();
		if (cooldown.containsKey(p) && cooldown.get(p).after(new Timestamp(System.currentTimeMillis()))) {
			e.setCancelled(true);
			p.sendMessage(Mensagens.Aguarde_EnderPearl_Cooldown);
			p.getInventory().addItem(new ItemStack(Material.ENDER_PEARL));
			return;
		} else {
			cooldown.remove(p);
		}
		cooldown.put(p, new Timestamp(System.currentTimeMillis() + (1000 * Settings.EnderPearl_Cooldown_Cooldown)));
	}
}
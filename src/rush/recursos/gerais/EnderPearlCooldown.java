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

	private static HashMap<Player, Timestamp> COOLDOWN = new HashMap<Player, Timestamp>();

	@EventHandler(ignoreCancelled = true)
	public void aoJogarEnder(ProjectileLaunchEvent e) {
		if (!(e.getEntity().getShooter() instanceof Player)) return;
		if (e.getEntityType() != EntityType.ENDER_PEARL) return;

		Player p = (Player) e.getEntity().getShooter();
		if (COOLDOWN.containsKey(p) && COOLDOWN.get(p).after(new Timestamp(System.currentTimeMillis()))) {
			e.setCancelled(true);
			p.sendMessage(Mensagens.Aguarde_EnderPearl_Cooldown);
			p.getInventory().addItem(new ItemStack(Material.ENDER_PEARL));
			return;
		}
		COOLDOWN.put(p, new Timestamp(System.currentTimeMillis() + (1000L * Settings.EnderPearl_Cooldown_Cooldown)));
	}
	
}
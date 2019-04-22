package rush.recursos.bloqueadores;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.ItemStack;

import rush.configuracoes.Mensagens;

public class BloquearPassarDaBorda implements Listener {

	/**
	 * Código criado por Oscazz
	 * Link: https://www.spigotmc.org/threads/worldborder-enderpearl-block.236808/
	 */

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void aoLancarEnderPearl(PlayerTeleportEvent e) {
		Player p = e.getPlayer();
		if (e.getCause() == TeleportCause.ENDER_PEARL && p.getWorld().getWorldBorder() != null) {
			double worldborder = p.getWorld().getWorldBorder().getSize() / 2.0D; 
			Location center = p.getWorld().getWorldBorder().getCenter();
			Location to = e.getTo();
			if (center.getX() + worldborder < to.getX()) {
				p.getInventory().addItem(new ItemStack(Material.ENDER_PEARL, 1));
				p.sendMessage(Mensagens.Tentou_Passar_Da_Borda);
				e.setCancelled(true);
			} else if (center.getX() - worldborder > to.getX()) {
				p.getInventory().addItem(new ItemStack(Material.ENDER_PEARL, 1));
				p.sendMessage(Mensagens.Tentou_Passar_Da_Borda);
				e.setCancelled(true);
			} else if (center.getZ() + worldborder < to.getZ()) {
				p.getInventory().addItem(new ItemStack(Material.ENDER_PEARL, 1));
				p.sendMessage(Mensagens.Tentou_Passar_Da_Borda);
				e.setCancelled(true);
			} else if (center.getZ() - worldborder > to.getZ()) {
				p.getInventory().addItem(new ItemStack(Material.ENDER_PEARL, 1));
				p.sendMessage(Mensagens.Tentou_Passar_Da_Borda);
				e.setCancelled(true);
			}
		}
	}
	
}
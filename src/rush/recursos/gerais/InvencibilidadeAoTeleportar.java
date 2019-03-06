package rush.recursos.gerais;

import java.util.HashSet;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.scheduler.BukkitRunnable;

import rush.Main;
import rush.configuracoes.Settings;

public class InvencibilidadeAoTeleportar implements Listener {

	private HashSet<String> protegidos = new HashSet<>();

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void aoTeleportar(PlayerTeleportEvent e) {
		if (e.getCause() == TeleportCause.COMMAND || e.getCause() == TeleportCause.PLUGIN) {
			protegidos.add(e.getPlayer().getName());
			new BukkitRunnable() {
				@Override
				public void run() {
					protegidos.remove(e.getPlayer().getName());
				}
			}.runTaskLater(Main.get(), 20 * Settings.Tempo_De_Invencibilidade_Ao_Teleportar);
		}
	}

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void aoTomarDano(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (protegidos.contains(p.getName())) {
				e.setCancelled(true);
			}
		}
	}
	
}
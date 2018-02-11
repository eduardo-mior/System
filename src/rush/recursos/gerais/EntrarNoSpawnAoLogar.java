package rush.recursos.gerais;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import rush.Main;

public class EntrarNoSpawnAoLogar implements Listener {

	   @EventHandler (priority=EventPriority.LOW)
	    public void aoRenascer(PlayerRespawnEvent e) {
	      Player p = e.getPlayer();
	      if (Main.aqui.getConfig().getString("Spawn.world") != null) {
	        e.setRespawnLocation(Main.loc);
	      } else {
		    p.sendMessage(Main.aqui.getMensagens().getString("Spawn-Nao-Configurado").replaceAll("&", "§"));
	      }
	    }
	    
	   @EventHandler (priority=EventPriority.LOW)
	    public void aoLogar(PlayerJoinEvent e) {
	      Player p = e.getPlayer();
	      if (Main.aqui.getConfig().getString("Spawn.world") != null) {
	        p.teleport(Main.loc);
	      } else {
	        p.sendMessage(Main.aqui.getMensagens().getString("Spawn-Nao-Configurado").replaceAll("&", "§"));
	      }
	    }
}

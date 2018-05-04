package rush.recursos.gerais;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;

public class DesativarCicloDoDia implements Listener {

	@EventHandler
	public void aoCarregarMundo(WorldLoadEvent e) {
		for (World w : Bukkit.getWorlds()) {
            w.setGameRuleValue("doDaylightCycle", "false");
			w.setTime(6000);
		}
	}
}

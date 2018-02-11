package rush.recursos.gerais;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class DesativarFomeNosMundos implements Listener {

	@EventHandler
	  public void aoAlterarNivelDaFome(FoodLevelChangeEvent e) {
		e.setFoodLevel(40);
	    e.setCancelled(true);
	  }
}

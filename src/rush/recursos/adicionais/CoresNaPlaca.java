package rush.recursos.adicionais;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class CoresNaPlaca implements Listener {

	  @EventHandler
	  public void aoUsarPlaca(SignChangeEvent event)
	  {
	    if ((event.getPlayer().hasPermission("system.cornaplaca")) || (event.getPlayer().isOp())) {
	      for (int i = 0; i < 4; i++) {
	        event.setLine(i, ChatColor.translateAlternateColorCodes('&', event.getLines()[i]));
	      }
	    }
	  }
	
}

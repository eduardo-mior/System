package rush.recursos.antibug;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.event.entity.EntityPortalExitEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import rush.Main;

public class Outros implements Listener {

    @EventHandler
    public void aoTentarVender(PlayerCommandPreprocessEvent e) {
       Player p = e.getPlayer();
       if (e.getMessage().contains("mercado vender -") || e.getMessage().contains("market vender -")) {
          e.setCancelled(true);
             p.sendMessage(Main.aqui.getMensagens().getString("Numero-Invalido").replaceAll("&", "§"));
       	}
    }
    
	@EventHandler
	public void onCommandEvent(PlayerCommandPreprocessEvent e) {
				
	Player p = e.getPlayer();
	String cmd = e.getMessage().toLowerCase();
    String[] arg1 = null;
	arg1 = cmd.split(" ");
	
		for (String cmdmoney: Main.aqui.getConfig().getStringList("Comandos-Que-Envolvem-Dinheiro"))
		if (arg1[0].contains(cmdmoney)) {
			e.setCancelled(true);
			p.sendMessage(Main.aqui.getMensagens().getString("Numero-Invalido").replaceAll("&", "§"));
		}
	}
    
	@EventHandler
	public void morreuAoEntrar(EntityPortalEnterEvent e) {
		Entity entity = e.getEntity();
		if (entity.isDead()) {
			entity.remove();
		}
	}

	@EventHandler
	public void mooreuAoSair(EntityPortalExitEvent e) {
		Entity entity = e.getEntity();
		if (entity.isDead()) {
			entity.remove();
		}
	}
}

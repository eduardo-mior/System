package rush.recursos.antibug;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.event.entity.EntityPortalExitEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import rush.utils.ConfigManager;

public class Outros implements Listener {

    @EventHandler
    public void aoTentarVender(PlayerCommandPreprocessEvent e) {
       Player p = e.getPlayer();
       if (e.getMessage().contains("mercado vender -") || e.getMessage().contains("market vender -")) {
          e.setCancelled(true);
             p.sendMessage(ConfigManager.getConfig("mensagens").getString("Numero-Invalido").replaceAll("&", "§"));
       	}
    }
    
	@EventHandler
	public void onCommandEvent(PlayerCommandPreprocessEvent e) {
				
	Player p = e.getPlayer();
	String cmd = e.getMessage().toLowerCase();
    String[] arg;
	arg = cmd.split(" ");
	
	for (String cmdmoney: ConfigManager.getConfig("settings").getStringList("Comandos-Que-Envolvem-Dinheiro")) {
		if (arg[0].contains(cmdmoney)) {
				if (cmd.contains(" null") || cmd.contains(" nan")) {
					e.setCancelled(true);
					p.sendMessage(ConfigManager.getConfig("mensagens").getString("Money-Null").replaceAll("&", "§"));
				}
			}
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

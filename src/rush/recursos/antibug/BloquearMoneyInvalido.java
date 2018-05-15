package rush.recursos.antibug;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import rush.utils.ConfigManager;

public class BloquearMoneyInvalido implements Listener {

    @EventHandler
    public void aoTentarVender(PlayerCommandPreprocessEvent e) {
       Player p = e.getPlayer();
       if (e.getMessage().contains("mercado vender -") || e.getMessage().contains("market vender -")) {
    	   e.setCancelled(true);
             p.sendMessage(ConfigManager.getConfig("mensagens").getString("Numero-Invalido").replace("&", "§"));
       	}
    }
    
	@EventHandler
	public void onCommandEvent(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		String cmd = e.getMessage().toLowerCase();
	    String[] arg;
		arg = cmd.split(" ");
		
		for (String cmdmoney: ConfigManager.getConfig("settings").getStringList("Comandos-Que-Envolvem-Money")) {
			if (arg[0].contains(cmdmoney)) {
					if (cmd.contains(" null") || cmd.contains(" nan")|| cmd.contains(" -nan")|| cmd.contains(" nan")) {
						e.setCancelled(true);
						p.sendMessage(ConfigManager.getConfig("mensagens").getString("Money-Null").replace("&", "§"));
						return;
					}
				}
			}
	}
	
}

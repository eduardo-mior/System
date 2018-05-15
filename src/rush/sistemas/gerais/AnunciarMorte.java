package rush.sistemas.gerais;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import rush.utils.ConfigManager;

public class AnunciarMorte implements Listener {

	@EventHandler
	public void aoSerMorto(PlayerDeathEvent e) {
        Player p = e.getEntity();
        Entity k = p.getKiller();
		if (k instanceof Player) {
	        String killer = p.getKiller().getPlayer().getName();
			
		    if (ConfigManager.getConfig("settings").getBoolean("Anuncios.Mostrar-Para-Quem-Matou")) {
		    	k.sendMessage(ConfigManager.getConfig("settings").getString("Anuncios.Mensagem-Para-Matador").replace("%playerMorreu%", e.getEntity().getName()).replace("&", "§"));
			}
		    
		    if (ConfigManager.getConfig("settings").getBoolean("Anuncios.Mostrar-Para-Quem-Morreu")) {
		    	p.sendMessage(ConfigManager.getConfig("settings").getString("Anuncios.Mensagem-Para-Defunto").replace("%playerMatou%", killer).replace("&", "§"));
			}
		    
		    if (ConfigManager.getConfig("settings").getBoolean("Anuncios.Mostrar-Para-Todo-Servidor")) {
		    	Bukkit.broadcastMessage(ConfigManager.getConfig("settings").getString("Anuncios.Mensagem-Para-Todos").replace("%playerMatou%", killer).replace("%playerMorreu%", e.getEntity().getName()).replace("&", "§"));
            }
		}
	}
}

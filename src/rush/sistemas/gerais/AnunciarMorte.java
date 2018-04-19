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
        Player player = e.getEntity();
        Entity entidade = player.getKiller();
		if (entidade instanceof Player) {
	        String killer = player.getKiller().getPlayer().getName();
			
		    if (ConfigManager.getConfig("settings").getBoolean("Anuncios.Mostrar-Para-Quem-Matou"))
		    {
		    entidade.sendMessage(ConfigManager.getConfig("settings").getString("Anuncios.Mensagem-Para-Matador").replace("%playerMorreu%", e.getEntity().getName()).replace("&", "§"));
			}
		    
		    if (ConfigManager.getConfig("settings").getBoolean("Anuncios.Mostrar-Para-Quem-Morreu"))
		    {
			player.sendMessage(ConfigManager.getConfig("settings").getString("Anuncios.Mensagem-Para-Defunto").replace("%playerMatou%", killer).replace("&", "§"));
			}
		    
		    if (ConfigManager.getConfig("settings").getBoolean("Anuncios.Mostrar-Para-Todo-Servidor"))
		    {
            Bukkit.broadcastMessage(ConfigManager.getConfig("settings").getString("Anuncios.Mensagem-Para-Todos").replace("%playerMatou%", killer).replace("%playerMorreu%", e.getEntity().getName()).replace("&", "§"));
            }
		}
	}
}

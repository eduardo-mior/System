package rush.sistemas.gerais;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import rush.configuracoes.Settings;

public class AnunciarMorte implements Listener {

	@EventHandler
	public void aoMorrerAnunciarMorte(PlayerDeathEvent e) {
		
        Player p = e.getEntity();
        Entity k = p.getKiller();
        
		if (k instanceof Player) {
			
	        String killer = p.getKiller().getPlayer().getName();
			
		    if (Settings.Anuncios_Mostrar_Para_Quem_Matou) {
		    	k.sendMessage(Settings.Anuncios_Mensagem_Para_Matador.replace("%playerMorreu%", e.getEntity().getName()));
			}
		    
		    if (Settings.Anuncios_Mostrar_Para_Quem_Morreu) {
		    	p.sendMessage(Settings.Anuncios_Mensagem_Para_Defunto.replace("%playerMatou%", killer));
			}
		    
		    if (Settings.Anuncios_Mostrar_Para_Todo_Servidor) {
		    	Bukkit.broadcastMessage(Settings.Anuncios_Mensagem_Para_Todos.replace("%playerMatou%", killer).replace("%playerMorreu%", e.getEntity().getName()));
            }
		}
	}
}

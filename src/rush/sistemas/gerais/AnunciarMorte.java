package rush.sistemas.gerais;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import rush.configuracoes.Settings;

public class AnunciarMorte implements Listener {

	@EventHandler
	public void aoMorrerAnunciarMorte(PlayerDeathEvent e) {
		
        Player p = e.getEntity();
        
		if (p.getKiller() instanceof Player) {
			
			Player k = (Player) p.getKiller();
			
		    if (Settings.Anuncios_Mostrar_Para_Quem_Matou) {
		    	k.sendMessage(Settings.Anuncios_Mensagem_Para_Matador.replace("%playerMorreu%", e.getEntity().getName()));
			}
		    
		    if (Settings.Anuncios_Mostrar_Para_Quem_Morreu) {
		    	p.sendMessage(Settings.Anuncios_Mensagem_Para_Defunto.replace("%playerMatou%", k.getName()));
			}
		    
		    if (Settings.Anuncios_Mostrar_Para_Todo_Servidor) {
		    	Bukkit.broadcastMessage(Settings.Anuncios_Mensagem_Para_Todos.replace("%playerMatou%", k.getName()).replace("%playerMorreu%", p.getName()));
            }
		}
	}
}

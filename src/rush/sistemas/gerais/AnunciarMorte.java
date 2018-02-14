package rush.sistemas.gerais;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import rush.Main;

public class AnunciarMorte implements Listener {

	@EventHandler
	public void aoSerMorto(PlayerDeathEvent e) {
        Player player = e.getEntity();
        Entity killer = player.getKiller();
        String nome = killer.getName();
		if (killer instanceof Player) {
			
		    if (Main.aqui.getConfig().getBoolean("Anuncios.Mostrar-Para-Quem-Matou"))
		    {
			killer.sendMessage(Main.aqui.getConfig().getString("Anuncios.Mensagem-Para-Matador").replace("%playerMorreu%", e.getEntity().getName()).replace("&", "§"));
			}
		    
		    if (Main.aqui.getConfig().getBoolean("Anuncios.Mostrar-Para-Quem-Morreu"))
		    {
			player.sendMessage(Main.aqui.getConfig().getString("Anuncios.Mensagem-Para-Defunto").replace("%playerMatou%", nome).replace("&", "§"));
			}
		    
		    if (Main.aqui.getConfig().getBoolean("Anuncios.Mostrar-Para-Todo-Servidor"))
		    {
            Bukkit.broadcastMessage(Main.aqui.getConfig().getString("Anuncios.Mensagem-Para-Todos").replace("%playerMatou%", nome).replace("%playerMorreu%", e.getEntity().getName()).replace("&", "§"));
            }
	}
  }
}

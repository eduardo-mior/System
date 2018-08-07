package rush.sistemas.gerais;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import rush.api.TablistAPI;
import rush.configuracoes.Settings;

public class Tablist implements Listener {
	
	private String header = Settings.Parte_De_Cima;
	private String footer = Settings.Parte_De_Baixo;
   
	@EventHandler
   	public void aoEntrarTablist(PlayerJoinEvent e) {
   		TablistAPI.sendTabList(e.getPlayer(), header, footer);	   
   	}
}
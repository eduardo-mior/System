package rush.recursos.gerais;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import rush.apis.TitleAPI;
import rush.configuracoes.Settings;

public class TitleDeBoasVindas implements Listener {
	
	@EventHandler
	public void aoEntrar(PlayerJoinEvent e) {
		String nome = e.getPlayer().getName();
		TitleAPI.sendTitle(e.getPlayer(), 20, 60, 20,
		Settings.Title_De_Boas_Vindas_Titulo.replace("%player%", nome),
		Settings.Title_De_Boas_Vindas_Subtitulo.replace("%player%", nome));
	}
}

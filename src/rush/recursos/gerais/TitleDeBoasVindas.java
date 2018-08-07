package rush.recursos.gerais;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import rush.configuracoes.Settings;

public class TitleDeBoasVindas implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void aoEntrar(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		String nome = p.getName();
		p.sendTitle(
		Settings.Title_De_Boas_Vindas_Titulo.replace("%player%", nome),
		Settings.Title_De_Boas_Vindas_Subtitulo.replace("%player%", nome));
	}
}

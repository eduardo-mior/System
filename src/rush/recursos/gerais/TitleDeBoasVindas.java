package rush.recursos.gerais;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import rush.Main;

public class TitleDeBoasVindas implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void aoEntrar(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		String nome = p.getName();
		p.sendTitle(
		Main.aqui.getConfig().getString("Title-De-Boas-Vindas.Titulo").replace("&", "§").replace("%player%", nome),
		Main.aqui.getConfig().getString("Title-De-Boas-Vindas.Subtitulo").replace("&", "§").replace("%player%", nome));
	}
}

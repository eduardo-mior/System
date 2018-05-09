package rush.recursos.gerais;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import rush.utils.ConfigManager;

public class MensagemDeBoasVindas implements Listener {
	
	@EventHandler
	public void aoEntrar(PlayerJoinEvent e) {
		for (String mensagem : ConfigManager.getConfig("settings").getStringList("Mensagem-De-Boas-Vindas.Mensagem")) {
			Player p = e.getPlayer();
			String nome = p.getName();
			p.sendMessage((mensagem).replace("&", "§").replace("%player%", nome));
		}
	}
}
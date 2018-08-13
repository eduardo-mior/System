package rush.recursos.gerais;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import rush.configuracoes.Settings;

public class MensagemDeBoasVindas implements Listener {

	@EventHandler
	public void aoEntrar(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		for (String mensagem : Settings.Mensagem_De_Boas_Vindas_Mensagem) {
			p.sendMessage(mensagem.replace('&', '§').replace("%player%", p.getName()));
		}
	}
}
package rush.recursos.gerais;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.scheduler.BukkitRunnable;

import rush.Main;
import rush.configuracoes.Mensagens;
import rush.configuracoes.Settings;

public class LimiteDePlayers implements Listener {
	
	@EventHandler
	public void aoVerMotd(ServerListPingEvent e) {
		e.setMaxPlayers(Settings.Limite_De_Players);
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void aoEntrar(PlayerLoginEvent e) {
		int playersOnline = Bukkit.getOnlinePlayers().size();
		int limite = Settings.Limite_De_Players;
		if (playersOnline >= limite) {
			if (!e.getPlayer().hasPermission("system.lotado.entrar")) {
				e.setResult(Result.KICK_FULL);
				e.setKickMessage(Mensagens.Servidor_Lotado);
			} else if (Settings.Kickar_Sem_Vip) {
				Player[] players = Bukkit.getOnlinePlayers().toArray(new Player[playersOnline]);
				int cont = 0;
				while(cont < 4) {
					int random = new Random().nextInt(playersOnline);
					Player p = players[random];
					if (p.hasPermission("system.lotado.entrar")) {
						cont++;
						continue;
					} else {
						kickPlayer(p);
						return;
					}
				}
			}
		}
	}
	
	private void kickPlayer(Player p) {
		p.sendMessage(Mensagens.Aviso_Dar_Lugar_Ao_Vip);
		new BukkitRunnable() {
			@Override
			public void run() {
				if (p != null)
					p.kickPlayer(Mensagens.Kick_Dar_Lugar_Ao_Vip);	
			}
		}.runTaskLater(Main.get(), 20L * Settings.Tempo_Para_Ser_Kick);
	}
	
}
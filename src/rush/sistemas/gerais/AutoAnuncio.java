package rush.sistemas.gerais;

import java.util.List;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import rush.Main;
import rush.apis.OnlinePlayersAPI;
import rush.configuracoes.Settings;

public class AutoAnuncio {

	public static BukkitTask XTask;
	private static int nMsg = 0;
	private static int size = Settings.Lista_De_Anuncios.size() - 1;
	private static List<String> mensagens = Settings.Lista_De_Anuncios;
	private static Sound som = Settings.Som_Do_Anuncio;

	public static void runMensagens() {
		XTask = new BukkitRunnable() {
			@Override
			public void run() {
				String msg = (mensagens.get(nMsg));
				for (Player p : OnlinePlayersAPI.getOnlinePlayers()) {
					if (Settings.Destacar_Anuncio) p.sendMessage(" ");
					p.sendMessage(msg);
					if (Settings.Destacar_Anuncio) p.sendMessage(" ");
					if (Settings.Reproduzir_Som_No_Anuncio)	p.playSound(p.getLocation(), som, 1, 1);
				}
				nMsg = nMsg == size ? 0 : nMsg + 1;
			}
		}.runTaskTimerAsynchronously(Main.get(), 60L, Settings.Delay_Entre_Anuncios * 20L);
	}
	
}
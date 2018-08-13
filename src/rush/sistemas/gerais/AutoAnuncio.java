package rush.sistemas.gerais;

import java.util.List;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import rush.Main;
import rush.configuracoes.Settings;

public class AutoAnuncio implements Listener {

	public static BukkitTask XTask;
	private static List<String> mensagens = Settings.Lista_De_Anuncios;

	public static void runMensagens() {
		int nmsg = mensagens.size();
		Random rnd = new Random();

		XTask = (new BukkitRunnable() {
			public void run() {
				int intmsg = rnd.nextInt(nmsg);
				for (Player p : Bukkit.getOnlinePlayers()) {
					if (Settings.Destacar_Anuncio) p.sendMessage(" ");
					p.sendMessage(mensagens.get(intmsg).replace('&', '§'));
					if (Settings.Destacar_Anuncio) p.sendMessage(" ");
					if (Settings.Reproduzir_Som_No_Anuncio)	p.playSound(p.getLocation(), Sound.valueOf(Settings.Som_Do_Anuncio), 1, 1);
				}
			}
		}).runTaskTimerAsynchronously((Plugin) Main.get(), 60L, (long) Settings.Delay_Entre_Anuncios * 20L);
	}
}

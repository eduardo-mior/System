package rush.sistemas.gerais;

import java.util.List;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import rush.Main;
import rush.configuracoes.Settings;

public class AutoAnuncioOLD {

	public static BukkitTask XTask;
	private static List<String> mensagens = Settings.Lista_De_Anuncios;

	public static void runMensagens() {
		int nmsg = mensagens.size();
		Random rnd = new Random();

		XTask = (new BukkitRunnable() {
			@Override
			public void run() {
				int intmsg = rnd.nextInt(nmsg);
				if (Settings.Destacar_Anuncio) Bukkit.broadcastMessage(" ");
				Bukkit.broadcastMessage(mensagens.get(intmsg).replace('&', '§'));
				if (Settings.Destacar_Anuncio) Bukkit.broadcastMessage(" ");	 
			}
		}).runTaskTimerAsynchronously(Main.get(), 60L, Settings.Delay_Entre_Anuncios * 20L);
	}
}

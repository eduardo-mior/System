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
	private static int ultimo = 0;
	private static List<String> mensagens = Settings.Lista_De_Anuncios;
	
	public static void runMensagens() {
		XTask = (new BukkitRunnable() {
		public void run() {
			for (Player todos: Bukkit.getOnlinePlayers()) {
				if(!mensagens.isEmpty()) {
					
					int nmsg = mensagens.size();
					Random rn = new Random();
					int intmsg = rn.nextInt(nmsg);
					while(intmsg == ultimo) {
					intmsg = rn.nextInt(nmsg); }
					ultimo  = intmsg;
					
					if (Settings.Destacar_Anuncio) todos.sendMessage(" ");
					todos.sendMessage(mensagens.get(intmsg).replaceAll("&", "§"));
					if (Settings.Destacar_Anuncio) todos.sendMessage(" ");
					if (Settings.Reproduzir_Som_No_Anuncio)	todos.playSound(todos.getLocation(), Sound.valueOf(Settings.Som_Do_Anuncio), 1, 1);
					
				}
			}
		}
		}).runTaskTimerAsynchronously((Plugin) Main.aqui, 60L, (long)Settings.Delay_Entre_Anuncios * 20L);
	}
}

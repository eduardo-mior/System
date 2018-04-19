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
import rush.utils.ConfigManager;

public class AutoAnuncio implements Listener {
	
	public static BukkitTask XTask;
	private static int ultimo = 0;
	private static List<String> mensagens = ConfigManager.getConfig("settings").getStringList("Lista-De-Anuncios");
	
	public static void runMensagens() {
		XTask = (new BukkitRunnable() {
		public void run() {
			for(Player todos: Bukkit.getOnlinePlayers()){
				if(!mensagens.isEmpty()){
					int nmsg = mensagens.size();
					Random rn = new Random();
					int intmsg = rn.nextInt(nmsg);
					while(intmsg == ultimo) {
					intmsg = rn.nextInt(nmsg); }
					ultimo  = intmsg;
						if (ConfigManager.getConfig("settings").getBoolean("Destacar-Anuncio")){
							todos.sendMessage("");}
							todos.sendMessage(mensagens.get(intmsg).replace("&", "§"));
						if (ConfigManager.getConfig("settings").getBoolean("Destacar-Anuncio")){
							todos.sendMessage("");}
						if (ConfigManager.getConfig("settings").getBoolean("Reproduzir-Som-No-Anuncio")){
							todos.playSound(todos.getLocation(), Sound.valueOf(ConfigManager.getConfig("settings").getString("Som-Do-Anuncio")), 1, 1);
						}	
				}
			}
		}
		}).runTaskTimerAsynchronously((Plugin) Main.aqui, 60L, (long)ConfigManager.getConfig("settings").getInt("Delay-Entre-Anuncios") * 20L);
	}
}

package rush.addons.legendchat;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;
import rush.Main;
import rush.addons.Vault;
import rush.configuracoes.Settings;

public class MagnataTag implements Listener {

	public static BukkitTask MTask;
	
	private static String playerMagnata = "";
	private static double balanceMagnata = 0;

	@EventHandler(ignoreCancelled = true)
	public void aoEnviarMenssagem(ChatMessageEvent e) {
		if (e.getSender().getName().equals(playerMagnata) && e.getTags().contains("magnata")) {
			e.setTagValue("magnata", Settings.magnataTag_Tag);
		}
	}

	public static void checkMagnata() {
		MTask = new BukkitRunnable() {
			@Override
			public void run() {
				for (OfflinePlayer off : Bukkit.getServer().getOfflinePlayers()) {
					double balance = Vault.getBalance(off);
					if (balance > balanceMagnata) {
						playerMagnata = off.getName(); 
						balanceMagnata = balance;
					}
				}
			}
		}.runTaskTimerAsynchronously(Main.get(), 60L, Settings.magnataTag_Tempo_De_Checagem * 20L);
	}

}
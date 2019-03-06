package rush.addons.legendchat;

import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import com.gmail.nossr50.database.DatabaseManagerFactory;
import com.gmail.nossr50.datatypes.database.PlayerStat;
import com.gmail.nossr50.datatypes.skills.SkillType;

import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;

import rush.Main;
import rush.configuracoes.Settings;

public class McTopTag implements Listener {

	public static BukkitTask TTask;
	
	private static String playerTopOne = "";

	@EventHandler(ignoreCancelled = true)
	public void aoEnviarMenssagem(ChatMessageEvent e) {
		if (e.getSender().getName().equals(playerTopOne) && e.getTags().contains("mctop")) {
			e.setTagValue("mctop", Settings.mcTopTag_Tag);
		}
	}

	public static void checkMCTop() {
		TTask = new BukkitRunnable() {
			@Override
			public void run() {
				List<PlayerStat> tops = DatabaseManagerFactory.getDatabaseManager().readLeaderboard((SkillType) null, 1, 1);
				if (!tops.isEmpty()) {
					playerTopOne = ((PlayerStat) tops.get(0)).name;
				}
			}
		}.runTaskTimerAsynchronously(Main.get(), 60L, Settings.mcTopTag_Tempo_De_Checagem * 20L);
	}

}
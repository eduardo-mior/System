package rush.addons;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import com.gmail.nossr50.database.DatabaseManagerFactory;
import com.gmail.nossr50.datatypes.database.PlayerStat;
import com.gmail.nossr50.datatypes.player.PlayerProfile;
import com.gmail.nossr50.datatypes.skills.SkillType;
import com.gmail.nossr50.events.experience.McMMOPlayerLevelUpEvent;
import com.gmail.nossr50.events.experience.McMMOPlayerXpGainEvent;
import com.gmail.nossr50.util.player.UserManager;

import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;
import rush.Main;
import rush.apis.ActionBarAPI;
import rush.configuracoes.Mensagens;
import rush.configuracoes.Settings;

public class McMMO implements Listener {
	
	public static BukkitTask TTask;
	private static String playerTopOne;
	   
	@EventHandler
	public void aoEnviarMenssagem(ChatMessageEvent e) {
	   if (playerTopOne != null && playerTopOne.equalsIgnoreCase(e.getSender().getName()) && e.getTags().contains("mctop")) {
		   e.setTagValue("mctop", Settings.mcTopTag_Tag);
	     }
	}

	public static void checkMCTop() {
		TTask = (new BukkitRunnable() {
			public void run() {
				List<PlayerStat> tops = DatabaseManagerFactory.getDatabaseManager().readLeaderboard((SkillType)null, 1, 1);
				if (!tops.isEmpty()) {
					playerTopOne = ((PlayerStat)tops.get(0)).name;
				}
			}
		}).runTaskTimerAsynchronously((Plugin) Main.get(), 60L, (long)Settings.mcTopTag_Tempo_De_Checagem * 20L);
	}
	   
	@EventHandler
	public void onPlayerUp(McMMOPlayerLevelUpEvent e) {
		Player p = e.getPlayer();
		if (e.getSkillLevel() % 100 == 0){
			Bukkit.broadcastMessage(Mensagens.mcMMO_Upou_100_Niveis
				.replace("%skill%", e.getSkill().name())
            	.replace("%player%", p.getName())
            	.replace("%level%", String.valueOf(e.getSkillLevel()))
            	.replace('&', '§'));
            p.getWorld().strikeLightningEffect(p.getLocation());
            p.getWorld().strikeLightningEffect(p.getLocation());
		}
	}
	  
	@SuppressWarnings("deprecation")
	@EventHandler
	public static void aoGanharXp(McMMOPlayerXpGainEvent e) {
		PlayerProfile pro = UserManager.getPlayer(e.getPlayer()).getProfile();
	    
		Player p = e.getPlayer();
		String skill = e.getSkill().getName();
		int lvl = pro.getSkillLevel(e.getSkill());
		int xp = pro.getSkillXpLevel(e.getSkill());
		int dxp = pro.getXpToLevel(e.getSkill());
		int gn = Math.round(e.getXpGained());
	    
		ActionBarAPI.sendActionBar(p, "§a" + skill + ": " + lvl + " (" + xp + "/" + dxp + ") +" + gn + "XP");
	}
}
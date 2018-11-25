package rush.addons;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.gmail.nossr50.datatypes.player.PlayerProfile;
import com.gmail.nossr50.datatypes.skills.SkillType;
import com.gmail.nossr50.events.experience.McMMOPlayerLevelUpEvent;
import com.gmail.nossr50.events.experience.McMMOPlayerXpGainEvent;
import com.gmail.nossr50.util.player.UserManager;

import rush.apis.ActionBarAPI;
import rush.configuracoes.Mensagens;

public class Mcmmo implements Listener {
	   
	@EventHandler(ignoreCancelled = true)
	public void onPlayerUp(McMMOPlayerLevelUpEvent e) {
		Player p = e.getPlayer();
		if (e.getSkillLevel() % 100 == 0){
			Bukkit.broadcastMessage(Mensagens.mcMMO_Upou_100_Niveis
            	.replace("%level%", String.valueOf(e.getSkillLevel()))
				.replace("%skill%", e.getSkill().getName())
            	.replace("%player%", p.getName()));

            p.getWorld().strikeLightningEffect(p.getLocation());
            p.getWorld().strikeLightningEffect(p.getLocation());
		}
	}
	  
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onGainXp(McMMOPlayerXpGainEvent e) {
		PlayerProfile pro = UserManager.getPlayer(e.getPlayer()).getProfile();
		SkillType skillType = e.getSkill();
		String skillName = skillType.getName();
		int lvl = pro.getSkillLevel(skillType);
		int xp = pro.getSkillXpLevel(skillType);
		int dxp = pro.getXpToLevel(skillType);
		int gn = Math.round(e.getXpGained());
		ActionBarAPI.sendActionBar(e.getPlayer(), "§a" + skillName + ": " + lvl + " (" + xp + "/" + dxp + ") +" + gn + "XP");
	}
}
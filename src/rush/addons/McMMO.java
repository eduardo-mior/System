package rush.addons;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.gmail.nossr50.datatypes.player.PlayerProfile;
import com.gmail.nossr50.events.experience.McMMOPlayerLevelUpEvent;
import com.gmail.nossr50.events.experience.McMMOPlayerXpGainEvent;
import com.gmail.nossr50.util.player.UserManager;

import rush.apis.ActionBarAPI;
import rush.configuracoes.Mensagens;

public class McMMO implements Listener {
	   
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
	@EventHandler(ignoreCancelled = true)
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
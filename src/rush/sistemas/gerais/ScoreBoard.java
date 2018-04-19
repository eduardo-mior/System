package rush.sistemas.gerais;

import java.util.List;
import java.util.stream.IntStream;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import rush.utils.ConfigManager;

public class ScoreBoard implements Listener {

    private String titulo = ConfigManager.getConfig("settings").getString("ScoreBoard.Titulo").replaceAll("&", "§");
	
	@EventHandler
	public void pj(PlayerJoinEvent e) {
	    Scoreboard scoreboard = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
	    Objective o = scoreboard.registerNewObjective((titulo.length() > 16 ? titulo.substring(0, 15) : titulo), "dummy");
        o.setDisplayName(titulo);
	    o.setDisplaySlot(DisplaySlot.SIDEBAR);
	    
	    Player p = e.getPlayer();
        final List<String> stringList = (List<String>)ConfigManager.getConfig("settings").getStringList("ScoreBoard.Linhas");
        IntStream.range(0, stringList.size()).forEach(i -> {
            Score score2 = o.getScore((String)stringList.get(i).replaceAll("%player%", p.getDisplayName()).replaceAll("&", "§"));
            score2.setScore(stringList.size() - i);
	    p.setScoreboard(scoreboard);
	      });
        return;
	}
}
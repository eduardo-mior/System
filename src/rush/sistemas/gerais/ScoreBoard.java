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

import rush.configuracoes.Settings;

public class ScoreBoard implements Listener {

    String titulo = Settings.ScoreBoard_Titulo;
	
	@EventHandler
	public void aoEntrarScoreboard(PlayerJoinEvent e) {
	    Scoreboard scoreboard = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
	    Objective o = scoreboard.registerNewObjective((titulo.length() > 16 ? titulo.substring(0, 15) : titulo), "dummy");
        o.setDisplayName(titulo);
	    o.setDisplaySlot(DisplaySlot.SIDEBAR);
	    
	    Player p = e.getPlayer();
	    List<String> linhas = Settings.ScoreBoard_Linhas;
	    IntStream.range(0, linhas.size()).forEach(i -> {
	    	Score score2 = o.getScore(linhas.get(i).replace("%player%", p.getName()).replace("&", "§"));
	    	score2.setScore(linhas.size() - i);
	    	p.setScoreboard(scoreboard);
	    });
	    return;
	}
}
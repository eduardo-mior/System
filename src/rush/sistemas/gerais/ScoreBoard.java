 package rush.sistemas.gerais;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import rush.configuracoes.Settings;

public class ScoreBoard implements Listener {

    private static Scoreboard scoreboard;
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void aoEntrarScoreboard(PlayerJoinEvent e) {
    	e.getPlayer().setScoreboard(scoreboard);
	}
	
	public static void loadScoreBoard() {
		String titulo = Settings.ScoreBoard_Titulo;
	    List<String> linhas = Settings.ScoreBoard_Linhas;
		
	    scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective o = scoreboard.registerNewObjective("system.score", "dummy");
        o.setDisplayName(titulo.length() > 32 ? titulo.substring(0, 32) : titulo);
	    o.setDisplaySlot(DisplaySlot.SIDEBAR);
	    
	    for (int i = 0; i < linhas.size(); i++) {
	    	String linha = linhas.get(i).length() > 40 ? linhas.get(i).substring(0, 40) : linhas.get(i);
	    	Score score = o.getScore(linha);
	    	score.setScore(linhas.size() - i);
	    }
	}
}
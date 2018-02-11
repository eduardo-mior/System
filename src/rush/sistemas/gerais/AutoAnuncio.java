package rush.sistemas.gerais;

import java.util.List;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import rush.Main;

public class AutoAnuncio extends BukkitRunnable {
	
	private List<String> mensagens = null;
	private int ultimo = 0;
	public AutoAnuncio(List<String> m){
		this.mensagens = m;
	}
	
	@Override
	public void run() {
        for(Player todos: Bukkit.getOnlinePlayers()){
		if(!mensagens.isEmpty()){
			int nmsg = mensagens.size();
			Random rn = new Random();
			int intmsg = rn.nextInt(nmsg);
			while(intmsg == ultimo){
				intmsg = rn.nextInt(nmsg);
			}
			this.ultimo = intmsg;
		    if (Main.aqui.getConfig().getBoolean("Destacar-Anuncio")){
			Bukkit.broadcastMessage("");}
			Bukkit.broadcastMessage(mensagens.get(intmsg).replace("&", "§"));
		    if (Main.aqui.getConfig().getBoolean("Destacar-Anuncio")){
			Bukkit.broadcastMessage("");}
	        if (Main.aqui.getConfig().getBoolean("Reproduzir-Som-No-Anuncio")){
                todos.playSound(todos.getLocation(), Sound.LEVEL_UP, 1, 1);
              }
		   }
		}
	}
}

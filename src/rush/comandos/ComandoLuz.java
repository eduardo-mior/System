package rush.comandos;

import java.util.ArrayList;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import rush.utils.ConfigManager;

public class ComandoLuz implements Listener, CommandExecutor {
	
   public ArrayList<String> luz = new ArrayList<String>();

	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("luz")) {
			
	      	if (!(s instanceof Player)) {
	      		s.sendMessage(ConfigManager.getConfig("mensagens").getString("Console-Nao-Pode").replace("&", "§"));
	      		return false;
	      	}
	       
      		Player p = (Player)s;
      		if (luz.contains(p.getName())) {
	            luz.remove(p.getName());
	            p.removePotionEffect(PotionEffectType.NIGHT_VISION);
	            p.sendMessage(ConfigManager.getConfig("mensagens").getString("Luz.Desativada").replace("&", "§"));
      		}
      		
      		else {
	            luz.add(p.getName());
	            p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 98999901, 5, true));
	            p.sendMessage(ConfigManager.getConfig("mensagens").getString("Luz.Ativada").replace("&", "§"));
      		}
	   }
	return false;
	}
}

package rush.comandos;

import java.util.ArrayList;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import rush.Main;

public class ComandoLuz implements Listener, CommandExecutor {
	
   public ArrayList<String> luz = new ArrayList<String>();

   public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
      if (cmd.getName().equalsIgnoreCase("luz")) {
	       if (!(sender instanceof Player)) {
	          sender.sendMessage(Main.aqui.getMensagens().getString("Console-Nao-Pode").replaceAll("&", "§"));
	          return true;}
         }
      		Player p = (Player)sender;
      		if (luz.contains(p.getName())) {
            p.removePotionEffect(PotionEffectType.NIGHT_VISION);
            luz.remove(p.getName());
            p.sendMessage(Main.aqui.getMensagens().getString("Luz.Desativada").replaceAll("&", "§"));
         } else {
            p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 98999901, 5, true));
            luz.add(p.getName());
            p.sendMessage(Main.aqui.getMensagens().getString("Luz.Ativada").replaceAll("&", "§"));
         }
      return false;
   }
}

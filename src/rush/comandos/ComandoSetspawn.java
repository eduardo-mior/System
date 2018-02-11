package rush.comandos;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import rush.Main;

public class ComandoSetspawn implements Listener, CommandExecutor {

	 public boolean onCommand(final CommandSender s, Command cmd, String lbl, String[] args) {
		 if (cmd.getName().equalsIgnoreCase("setspawn")) {
		      if (!s.hasPermission("system.setspawn")) {
		          s.sendMessage(Main.aqui.getMensagens().getString("Sem-Permissao").replaceAll("&", "§")); }
		      else if (!(s instanceof Player)) {
		          s.sendMessage(Main.aqui.getMensagens().getString("Console-Nao-Pode").replaceAll("&", "§")); }
		      else {
		                Player p = (Player)s;
		                Main.loc = p.getLocation();
		                saveLoc(Main.loc);
		                s.sendMessage(Main.aqui.getMensagens().getString("Spawn-Definido").replaceAll("&", "§"));
		      			}      
		 			}
		          return true;
		       }

	     public static void saveLoc(Location loc) {
	     Main.aqui.getConfig().set("Spawn", loc);
	     Main.aqui.getConfig().set("Spawn.world", loc.getWorld().getName());
	     Main.aqui.getConfig().set("Spawn.x", Double.valueOf(loc.getX()));
	     Main.aqui.getConfig().set("Spawn.y", Double.valueOf(loc.getY()));
	     Main.aqui.getConfig().set("Spawn.z", Double.valueOf(loc.getZ()));
	     Main.aqui.getConfig().set("Spawn.yaw", Float.valueOf(loc.getYaw()));
	     Main.aqui.getConfig().set("Spawn.pitch", Float.valueOf(loc.getPitch()));
	     Main.aqui.saveConfig(); }
}

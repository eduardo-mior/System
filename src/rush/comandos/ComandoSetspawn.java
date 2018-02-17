package rush.comandos;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import rush.Main;
import rush.utils.Locations;

public class ComandoSetspawn implements Listener, CommandExecutor {

	 public boolean onCommand(final CommandSender s, Command cmd, String lbl, String[] args) {
		 if (cmd.getName().equalsIgnoreCase("setspawn")) {
		      if (!s.hasPermission("system.setspawn")) {
		          s.sendMessage(Main.aqui.getMensagens().getString("Sem-Permissao").replaceAll("&", "§")); }
		      else if (!(s instanceof Player)) {
		          s.sendMessage(Main.aqui.getMensagens().getString("Console-Nao-Pode").replaceAll("&", "§")); }
		      else {
		                Player p = (Player)s;
		                Locations.spawn = p.getLocation();
		                saveSpawn(Locations.spawn);
		                s.sendMessage(Main.aqui.getMensagens().getString("Spawn-Definido").replaceAll("&", "§"));
		      			}      
		 			}
		          return true;
		       }

	     public static void saveSpawn(Location spawn) {
	     Main.aqui.getConfig().set("Spawn", spawn);
	     Main.aqui.getConfig().set("Spawn.world", spawn.getWorld().getName());
	     Main.aqui.getConfig().set("Spawn.x", Double.valueOf(spawn.getX()));
	     Main.aqui.getConfig().set("Spawn.y", Double.valueOf(spawn.getY()));
	     Main.aqui.getConfig().set("Spawn.z", Double.valueOf(spawn.getZ()));
	     Main.aqui.getConfig().set("Spawn.yaw", Float.valueOf(spawn.getYaw()));
	     Main.aqui.getConfig().set("Spawn.pitch", Float.valueOf(spawn.getPitch()));
	     Main.aqui.saveConfig(); }
}

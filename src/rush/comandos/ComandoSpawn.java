package rush.comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import rush.Main;
import rush.utils.Locations;

public class ComandoSpawn implements Listener, CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
	      if (cmd.getName().equalsIgnoreCase("spawn")) {
		       if (!(sender instanceof Player)) {
			          sender.sendMessage(Main.aqui.getMensagens().getString("Console-Nao-Pode").replaceAll("&", "§"));
			          return true; 
			          }
		       
		       Player p = (Player) sender;
		       if (!p.hasPermission("system.semdelay")) {
		       p.sendMessage(Main.aqui.getMensagens().getString("Iniciando-Teleporte-Spawn").replaceAll("&", "§").replace("%tempo%", String.valueOf(Main.aqui.getConfig().getInt("Delay-Para-Teleportar-Comandos"))));
		        new BukkitRunnable() {
		            @Override
		            public void run() {
		 		       p.teleport(Locations.spawn);
		            }
		        }.runTaskLater(Main.aqui, 20 * Main.aqui.getConfig().getInt("Delay-Para-Teleportar-Comandos"));
		        return false;
		       }
		       p.sendMessage(Main.aqui.getMensagens().getString("Teleportado-Com-Sucesso-Spawn").replace("&", "§"));
 		       p.teleport(Locations.spawn);
	      }
		return false;
	}
}

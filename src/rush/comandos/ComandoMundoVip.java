package rush.comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import rush.Main;
import rush.utils.ConfigManager;
import rush.utils.Locations;

public class ComandoMundoVip implements Listener, CommandExecutor {
	
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("mundovip")) {
			
			if (!(s instanceof Player)) {
				s.sendMessage(ConfigManager.getConfig("mensagens").getString("Console-Nao-Pode").replace("&", "§"));
				return false; 
			}
			
			Player p = (Player) s;
			if (!s.hasPermission("system.vip")) {
		   	    if (ConfigManager.getConfig("settings").getBoolean("Ativar-Camarote-Para-Os-Sem-Vip")) {
		   	    	s.sendMessage(ConfigManager.getConfig("mensagens").getString("Iniciando-Teleporte-Vip").replace("&", "§").replace("%tempo%", String.valueOf(ConfigManager.getConfig("settings").getInt("Delay-Para-Teleportar-Comandos"))));
		   	    	new BukkitRunnable() {
		   	    		@Override
		   	    		public void run() {
		   	    			p.teleport(Locations.areaNaoVip);
		   	    			s.sendMessage("§f ");
		   	    			s.sendMessage(ConfigManager.getConfig("mensagens").getString("Teleportado-Com-Sucesso-Sem-Vip").replace("&", "§"));
		   	    			s.sendMessage("§f ");
		   	    		}
		   	    	}.runTaskLater(Main.aqui, 20 * ConfigManager.getConfig("settings").getInt("Delay-Para-Teleportar-Comandos"));
		   	    	return false;
		   	    }
		   	    s.sendMessage(ConfigManager.getConfig("mensagens").getString("Sem-Permissao").replace("&", "§"));
		   	    return false;
			}
		    s.sendMessage(ConfigManager.getConfig("mensagens").getString("Teleportado-Com-Sucesso-Vip").replace("&", "§"));
		    p.teleport(Locations.areaVip);     
		}
		return false;
	}
	
}

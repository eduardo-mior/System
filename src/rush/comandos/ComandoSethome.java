package rush.comandos;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import rush.utils.ConfigManager;
import rush.utils.DataManager;

public class ComandoSethome implements Listener, CommandExecutor {
	
	@Override
	 public boolean onCommand(final CommandSender s, Command cmd, String lbl, String[] args) {
		 if (cmd.getName().equalsIgnoreCase("sethome")) {
				 if (s instanceof Player) {
					 
					 Player p = (Player) s;
					 
				     if (args.length == 0) {
				          s.sendMessage(ConfigManager.getConfig("mensagens").getString("SetHome-Comando-Incorreto").replaceAll("&", "§"));
				          return false;
				     }
				     
				     if (args.length > 1) {
				          s.sendMessage(ConfigManager.getConfig("mensagens").getString("SetHome-Comando-Incorreto").replaceAll("&", "§"));
				          return false;
				     }
				     
				     else {
				    	String home = args[0];
				        File file = DataManager.getFile(p.getName().toLowerCase(), "playerdata");
				        FileConfiguration config = DataManager.getConfiguration(file);
				        Set<String> KEYS = config.getConfigurationSection("Homes").getKeys(false);
				        int homes = KEYS.size();
				        int limite = getHomesLimit(p);
						if (homes < limite) {
							Location location = p.getLocation();
							String locationSerialized = location.getWorld().getName() + "," + location.getX() + "," + location.getY() + "," + location.getZ() + "," + location.getYaw() + "," + location.getPitch();
							config.set("Homes." + home + ".Localizacao" , locationSerialized);
							config.set("Homes." + home + ".Publica" , false);
							try {
								config.save(file);
								s.sendMessage(ConfigManager.getConfig("mensagens").getString("Home-Definida").replace("&", "§").replace("%home%", home));
							} catch (IOException e) {
								Bukkit.getConsoleSender().sendMessage(ConfigManager.getConfig("mensagens").getString("Falha-Ao-Salvar").replace("&", "§").replace("%arquivo%", file.getName()));
							}
						} else {
							s.sendMessage(ConfigManager.getConfig("mensagens").getString("Limite-De-Homes-Atingido").replace("&", "§").replace("%limite%", String.valueOf(limite)));
				        }
				     }
					 return false;
				 }
				 s.sendMessage(ConfigManager.getConfig("mensagens").getString("Console-Nao-Pode").replace("&", "§"));
				 return false;
			 }
		return false;
	}
	
	/*
	 * Powered by kickpost;
	 */
	
    public static int getHomesLimit(Player p) {
    	try {
    		return Integer.parseInt(p.getEffectivePermissions().stream()
    			   .filter(r -> r.getPermission().toLowerCase().startsWith("system.homes."))
    			   .findFirst().get().getPermission().replace("system.homes.", "").trim());
    	} catch (Exception e) {
    		return 1;
    	}
	}
}

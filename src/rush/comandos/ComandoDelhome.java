package rush.comandos;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import rush.utils.ConfigManager;
import rush.utils.DataManager;

public class ComandoDelhome implements Listener, CommandExecutor {
	
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("delhome")) {
			 
			if (!(s instanceof Player)) {
				s.sendMessage(ConfigManager.getConfig("mensagens").getString("Console-Nao-Pode").replace("&", "§"));
				return false;
			}
			 
			if (args.length != 1) {
				s.sendMessage(ConfigManager.getConfig("mensagens").getString("DelHome-Comando-Incorreto").replace("&", "§"));
				return false;
			} 
		     
			String home = args[0];
		   	File file = DataManager.getFile(s.getName().toLowerCase(), "playerdata");
		   	FileConfiguration config = DataManager.getConfiguration(file);
		   	Set<String> KEYS = config.getConfigurationSection("Homes").getKeys(false);
		   	if (!KEYS.contains(home)) {
		   		s.sendMessage(ConfigManager.getConfig("mensagens").getString("Home-Nao-Existe").replace("&", "§").replace("%home%", home));
		   		ComandoHomes.ListHomes(s, s.getName());
		   		return false;
		   	}
		   	
		   	config.set("Homes." + home, null);
		   	s.sendMessage(ConfigManager.getConfig("mensagens").getString("Home-Deletada").replace("&", "§").replace("%home%", home));
		   	try {
		   		config.save(file);
		   	} catch (IOException e) {
		   		Bukkit.getConsoleSender().sendMessage(ConfigManager.getConfig("mensagens").getString("Falha-Ao-Salvar").replace("&", "§").replace("%arquivo%", file.getName()));
		   	}
		 }
		 return false;
	}
}

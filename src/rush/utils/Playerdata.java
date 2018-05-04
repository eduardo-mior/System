package rush.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPreLoginEvent;
import org.bukkit.event.player.PlayerPreLoginEvent.Result;

@SuppressWarnings({ "deprecation"})
public class Playerdata implements Listener {
	
	@EventHandler
	public void aoEntrar(PlayerPreLoginEvent e) {
		String NewPlayer = e.getName();
        File file = DataManager.getFile(NewPlayer.toLowerCase(), "playerdata");
        FileConfiguration config = DataManager.getConfiguration(file);
        
        if (file.exists()) {
        	String OldPlayer = config.getString("Nick");
        	if (!NewPlayer.equals(OldPlayer)) {
				e.setKickMessage(ConfigManager.getConfig("mensagens").getString("Nick-Similar").replaceAll("&", "§").replaceAll("%antigo%", OldPlayer).replace("%novo%", NewPlayer));
				e.setResult(Result.KICK_OTHER);
        	}
        } else {
        	DataManager.createFile(file);
        	config.set("Nick", NewPlayer);
        	config.createSection("Homes");
        	config.createSection("Kits");
			try {
				config.save(file);
			} catch (IOException ex) {
				Bukkit.getConsoleSender().sendMessage(ConfigManager.getConfig("mensagens").getString("Falha-Ao-Salvar").replace("&", "§").replace("%arquivo%", file.getName()));
			}
        }
	}
}

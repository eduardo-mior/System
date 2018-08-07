package rush.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;

import rush.configuracoes.Mensagens;

public class PlayerData implements Listener {
	
	@EventHandler
	public void aoEntrar(AsyncPlayerPreLoginEvent e) {
		String NewPlayer = e.getName();
        File file = DataManager.getFile(NewPlayer.toLowerCase(), "playerdata");
        FileConfiguration config = DataManager.getConfiguration(file);
        
        if (file.exists()) {
        	String OldPlayer = config.getString("Nick");
        	if (!NewPlayer.equals(OldPlayer)) {
				e.setKickMessage(Mensagens.Nick_Similar.replace("%antigo%", OldPlayer).replace("%novo%", NewPlayer));
				e.setLoginResult(Result.KICK_OTHER);
        	}
        } else {
        	DataManager.createFile(file);
        	config.set("Nick", NewPlayer);
        	config.createSection("Homes");
        	config.createSection("Kits");
			try {
				config.save(file);
			} catch (IOException ex) {
				Bukkit.getConsoleSender().sendMessage(Mensagens.Falha_Ao_Salvar.replace("%arquivo%", file.getName()));
			}
        }
	}
}

package rush.sistemas.gerais;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import rush.configuracoes.Mensagens;
import rush.utils.DataManager;

public class PlayerData implements Listener {
	
	@EventHandler
	public void aoEntrar(PlayerLoginEvent e) {
		String newPlayer = e.getPlayer().getName();
        File file = DataManager.getFile(newPlayer.toLowerCase(), "playerdata");
        FileConfiguration config = DataManager.getConfiguration(file);
        
        if (file.exists()) {
        	String oldPlayer = config.getString("Nick");
        	if (!newPlayer.equals(oldPlayer)) {
				e.setResult(Result.KICK_OTHER);
				e.disallow(Result.KICK_OTHER, Mensagens.Nick_Similar.replace("%antigo%", oldPlayer).replace("%novo%", newPlayer));
				e.setKickMessage(Mensagens.Nick_Similar.replace("%antigo%", oldPlayer).replace("%novo%", newPlayer));
        	}
        } else {
        	if (e.getResult() == Result.ALLOWED) {
            	DataManager.createFile(file);
            	config.set("Nick", newPlayer);
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
}

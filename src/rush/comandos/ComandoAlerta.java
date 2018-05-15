package rush.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import rush.utils.ConfigManager;

public class ComandoAlerta implements Listener, CommandExecutor {

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("alerta")) {		 
	    			
			if (args.length < 1) { 
				s.sendMessage(ConfigManager.getConfig("mensagens").getString("Alerta-Comando-Incorreto").replace("&", "§"));
				return false;
			} 

			String nome = s.getName();
			String msg = "";
			for (int i = 0; i < args.length; i++) {	msg += args[i] + " "; }
		    for (Player todos : Bukkit.getOnlinePlayers()) {	
		    	todos.sendTitle(
    			ConfigManager.getConfig("mensagens").getString("Alerta-Title").replace("&", "§").replace("%alerta%", msg).replace("%player%", nome),
    			ConfigManager.getConfig("mensagens").getString("Alerta-SubTitle").replace("&", "§").replace("%alerta%", msg).replace("%player%", nome));
    			Bukkit.broadcastMessage("");
    			Bukkit.broadcastMessage(ConfigManager.getConfig("mensagens").getString("Alerta-Chat").replace("%alerta%", msg).replace("&", "§").replace("%player%", nome));
    			Bukkit.broadcastMessage("");
	    	}
	    }
	    return false;
	}
}
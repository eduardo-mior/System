package rush.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import rush.utils.ConfigManager;

public class ComandoAlerta implements CommandExecutor, Listener {

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] args) {
		String nome = Sender.getName();
	    for (Player todos : Bukkit.getOnlinePlayers()) {
	    	if (Cmd.getName().equalsIgnoreCase("alerta")) {
	    			if (!Sender.hasPermission("system.alerta")) {
	    				Sender.sendMessage(ConfigManager.getConfig("mensagens").getString("Sem-Permissao"));
	    				return false;
	    			} 
	    			
	    			if (args.length < 1) { 
	    				Sender.sendMessage(ConfigManager.getConfig("mensagens").getString("Alerta-Comando-Incorreto").replaceAll("&", "§"));
	    				return false;
	    			} 
	    			
	    			else {
	    				String msg = "";
	    				for (int i = 0; i < args.length; i++) {	msg += args[i] + " "; }
	    				todos.sendTitle(
	    				ConfigManager.getConfig("mensagens").getString("Alerta-Title").replace("&", "§").replaceAll("%alerta%", msg).replaceAll("%player%", nome),
	    				ConfigManager.getConfig("mensagens").getString("Alerta-SubTitle").replace("&", "§").replaceAll("%alerta%", msg).replaceAll("%player%", nome));
	    				Bukkit.broadcastMessage("");
	    				Bukkit.broadcastMessage(ConfigManager.getConfig("mensagens").getString("Alerta-Chat").replaceAll("%alerta%", msg).replaceAll("&", "§").replaceAll("%player%", nome));
	    				Bukkit.broadcastMessage("");
	    		}
	    	}
	    }
	    return false;
	}
}
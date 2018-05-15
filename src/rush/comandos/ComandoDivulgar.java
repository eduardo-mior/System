package rush.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import rush.utils.ConfigManager;

public class ComandoDivulgar implements Listener, CommandExecutor {
		
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
	    if (cmd.getName().equalsIgnoreCase("divulgar")) {
	        	        
	        if (args.length != 2) { 
	        	s.sendMessage(ConfigManager.getConfig("mensagens").getString("Divulgar-Comando-Incorreto").replace("&", "§"));
		        return false;
	        }
	        
		    for (Player todos : Bukkit.getOnlinePlayers()) {
	        String nome = s.getName();
	        if (args[0].equalsIgnoreCase("live") || args[0].equalsIgnoreCase("video") || args[0].equalsIgnoreCase("outro")) {
	        
		        if 
		        (args[1].contains("http") || 
		        (args[1].contains("www")  || 
		        (args[1].contains("com")) || 
		        (args[1].contains("br"))  || 
		        (args[1].contains("net")) ||
		        (args[1].contains("org")) ||
		        (args[1].contains("ly"))  ||
		        (args[1].contains("sc"))  ||
		        (args[1].contains("me"))  || 
		        (args[1].contains("tk")))) {
	        	
			        if (args[0].equalsIgnoreCase("live")) {
			            todos.sendTitle(
			            ConfigManager.getConfig("mensagens").getString("Divulgando-Title").replace("&", "§").replace("%link%", args[1]).replace("_", " ").replace("%player%", nome),
			            ConfigManager.getConfig("mensagens").getString("Divulgando-SubTitle").replace("&", "§").replace("%link%", args[1]).replace("_", " ").replace("%player%", nome));
			            Bukkit.broadcastMessage("");
				        Bukkit.broadcastMessage(ConfigManager.getConfig("mensagens").getString("Divulgando-Live").replace("&", "§").replace("%player%", nome));
			            Bukkit.broadcastMessage(ConfigManager.getConfig("mensagens").getString("Link").replace("&", "§").replace("%link%", args[1]).replaceFirst("live", ""));
			            Bukkit.broadcastMessage("");
			       		return false;
			        }
			        
			        if (args[0].equalsIgnoreCase("video")) {
			            todos.sendTitle(
			            ConfigManager.getConfig("mensagens").getString("Divulgando-Title").replace("&", "§").replace("%link%", args[1]).replace("_", " ").replace("%player%", nome),
			            ConfigManager.getConfig("mensagens").getString("Divulgando-SubTitle").replace("&", "§").replace("%link%", args[1]).replace("_", " ").replace("%player%", nome));
			            Bukkit.broadcastMessage("");
			            Bukkit.broadcastMessage(ConfigManager.getConfig("mensagens").getString("Divulgando-Video").replace("&", "§").replace("%player%", nome));
			            Bukkit.broadcastMessage(ConfigManager.getConfig("mensagens").getString("Link").replace("&", "§").replace("%link%", args[1]).replaceFirst("video", ""));
			            Bukkit.broadcastMessage("");
		       			return false;
			        }
			        
			        if (args[0].equalsIgnoreCase("outro")) {
			            todos.sendTitle(
			            ConfigManager.getConfig("mensagens").getString("Divulgando-Title").replace("&", "§").replace("%link%", args[1]).replace("_", " ").replace("%player%", nome),
			            ConfigManager.getConfig("mensagens").getString("Divulgando-SubTitle").replace("&", "§").replace("%link%", args[1]).replace("_", " ").replace("%player%", nome));
			            Bukkit.broadcastMessage("");
			            Bukkit.broadcastMessage(ConfigManager.getConfig("mensagens").getString("Divulgando-Outro").replace("&", "§").replace("%player%", nome));
			            Bukkit.broadcastMessage(ConfigManager.getConfig("mensagens").getString("Link").replace("&", "§").replace("%link%", args[1]).replaceFirst("outro", ""));
			            Bukkit.broadcastMessage("");
		       			return false;
			        }
		        }
		        s.sendMessage(ConfigManager.getConfig("mensagens").getString("Link-Invalido").replace("&", "§").replace("%link%", args[1]));
		        return false;
	        }
        	s.sendMessage(ConfigManager.getConfig("mensagens").getString("Divulgar-Comando-Incorreto").replace("&", "§"));
	        return false;
	    }
	    }
	    return false;
	}
}

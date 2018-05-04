package rush.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import rush.utils.ConfigManager;

public class ComandoTitle implements Listener, CommandExecutor {
	
	@SuppressWarnings({ "deprecation" })
	public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] args) {
	    if (Cmd.getName().equalsIgnoreCase("title")) {
		    for (Player todos : Bukkit.getOnlinePlayers()) {
	    	if (!Sender.hasPermission("system.title")) {
		          Sender.sendMessage(ConfigManager.getConfig("mensagens").getString("Sem-Permissao"));
		          return true;
		        }
		        
		        else if (args.length < 1) {
		        	Sender.sendMessage(ConfigManager.getConfig("mensagens").getString("Title-Comando-Incorreto").replaceAll("&", "§"));
		        }
		        
		        else {
		        	String msg = "";
		            String msg2 = "";
		            String[] msg3 = null;
		            for (int i = 0; i < args.length; i++) {
		            msg = msg + args[i] + " ";
		            
		            msg2 = (msg).replaceAll(" ", "¨");
		            
		            msg3 = msg2.split("<nl>");
		            
		            }
		            
		            if (!(msg2.contains("<nl>"))) {
			            todos.sendTitle(msg.replaceAll("&", "§"), "§r");
			            Sender.sendMessage(ConfigManager.getConfig("mensagens").getString("Tile-Enviado").replaceAll("&", "§"));
		            	return true;
		            }
		            else {
		             todos.sendTitle(msg3[0].replaceAll("&", "§").replaceAll("¨", " "), msg3[1].replaceAll("&", "§").replaceAll("¨", " "));
		            }
		        }
		    }
	    }
	    return false;
	}
}

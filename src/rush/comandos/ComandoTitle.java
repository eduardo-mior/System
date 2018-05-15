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
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("title")) {		    	
		        
	    	if (args.length < 1) {
	    		s.sendMessage(ConfigManager.getConfig("mensagens").getString("Title-Comando-Incorreto").replace("&", "§"));
	    		return false;
	    	}
		       
	    	String msg = "";
	    	String msg2 = "";
	    	String[] msg3 = null;
	    	for (int i = 0; i < args.length; i++) {
	    		msg = msg + args[i] + " ";
	    		msg2 = (msg).replace(" ", "¨");
	    		msg3 = msg2.split("<nl>");     
	    	}
		            	
	    	for (Player todos : Bukkit.getOnlinePlayers()) {
	    		if (!(msg2.contains("<nl>"))) {
	    			todos.sendTitle(msg.replace("&", "§"), "§r");
	    			s.sendMessage(ConfigManager.getConfig("mensagens").getString("Tile-Enviado").replace("&", "§"));
	    		} else {
	    			todos.sendTitle(msg3[0].replace("&", "§").replace("¨", " "), msg3[1].replace("&", "§").replace("¨", " "));
	    		}
	    	}
	    }
	    return false;
	}
}

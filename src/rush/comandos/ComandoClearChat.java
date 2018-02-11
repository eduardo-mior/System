package rush.comandos;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

import rush.Main;

public class ComandoClearChat implements Listener, CommandExecutor {
	
	String chat = " §c \n §c ";
	String limparchat = StringUtils.repeat(chat, 100);

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandlabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("clearchat")) {
	         if (!sender.hasPermission("system.clearchat")) {
	             sender.sendMessage(Main.aqui.getMensagens().getString("Sem-Permissao").replaceAll("&", "§")); }
	         else {
	    		  Bukkit.broadcastMessage(limparchat);
	    		 if (Main.aqui.getMensagens().getBoolean("Avisar-Que-O-Chat-Foi-Limpo")) {
	    			 Bukkit.broadcastMessage(Main.aqui.getMensagens().getString("Aviso-Que-O-Chat-Limpo").replaceAll("&", "§").replaceAll("%player%", sender.getName()));
	    		 }
	         }
	       }
		return false;
	}
	
}

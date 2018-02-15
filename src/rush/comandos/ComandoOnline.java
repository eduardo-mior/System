package rush.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

import rush.Main;

public class ComandoOnline implements Listener, CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandlabel, String[] args) {
	      if (cmd.getName().equalsIgnoreCase("online")) {
                  sender.sendMessage(Main.aqui.getMensagens().getString("Players-Online").replace("&", "§").replace("%online%", String.valueOf(Bukkit.getOnlinePlayers().size())));
	    	}
		return false;
	}
}
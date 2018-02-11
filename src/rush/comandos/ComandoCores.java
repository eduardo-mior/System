package rush.comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

import rush.Main;

public class ComandoCores implements Listener, CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandlabel, String[] args) {
	      if (cmd.getName().equalsIgnoreCase("cores")) {
	    		for (String list : Main.aqui.getMensagens().getStringList("Tabela-De-Cores")) {;
                    sender.sendMessage(list);
                }
	    	}
		return false;
	}
}
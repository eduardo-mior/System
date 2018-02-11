package rush.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import rush.Main;

public class ComandoAlerta implements CommandExecutor, Listener {

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] args)
	  {
		String nome = Sender.getName();
	    for (Player todos : Bukkit.getOnlinePlayers()) {
	    if (Cmd.getName().equalsIgnoreCase("alerta"))
	    {
	        if (!Sender.hasPermission("system.alerta"))
	        {
	        	Sender.sendMessage(Main.aqui.getMensagens().getString("Sem-Permissao"));
	          return true;
	        }
	        if (args.length < 1)
	        { 
	        	Sender.sendMessage(Main.aqui.getMensagens().getString("Alerta-Comando-Incorreto").replaceAll("&", "§"));
	        }
	        else {
	        	String msg = "";
	            for (int i = 0; i < args.length; i++) {
	              msg = msg + args[i] + " ";
	            }
	              todos.sendTitle(
	              Main.aqui.getMensagens().getString("Alerta-Title").replace("&", "§").replaceAll("%alerta%", msg).replace("_", " ").replaceAll("%player%", nome),
	              Main.aqui.getMensagens().getString("Alerta-SubTitle").replace("&", "§").replaceAll("%alerta%", msg).replace("_", " ").replaceAll("%player%", nome));
	              Bukkit.broadcastMessage("");
		          Bukkit.broadcastMessage(Main.aqui.getMensagens().getString("Alerta-Chat").replaceAll("%alerta%", msg).replaceAll("&", "§").replaceAll("%player%", nome));
	              Bukkit.broadcastMessage("");
	            }
	    	 }
	      }
		return false;
	  }
	    
}
package rush.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

import rush.Main;

public class ComandoDivulgar implements Listener, CommandExecutor {
	
	String[] link = {"http", "https", "com", "br", "me", "sc", "net", "ly", "org", "www", "tk", "ts"};
	
	  public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] args)
	  {
        for (String check: link) {
        String nome = Sender.getName();
	    if (Cmd.getName().equalsIgnoreCase("divulgar"))
	    {
	        if (!Sender.hasPermission("system.divulgar"))
	        {
	        	Sender.sendMessage(Main.aqui.getMensagens().getString("Sem-Permissao"));
	          return true;
	        }
	        
	        if (args.length < 2 || args.length > 2)
	        { 
	        	Sender.sendMessage(Main.aqui.getMensagens().getString("Divulgar-Comando-Incorreto").replaceAll("&", "§"));
	        	return false;
	        }
	        
	       	if (args[1].equalsIgnoreCase(check) || args[1].contains(check))  {
	        if (args[0].equalsIgnoreCase("live")) {
	              Bukkit.broadcastMessage("");
		          Bukkit.broadcastMessage(Main.aqui.getMensagens().getString("Divulgando-Live").replaceAll("&", "§").replaceAll("%player%", nome));
	              Bukkit.broadcastMessage(Main.aqui.getMensagens().getString("Link").replaceAll("&", "§").replaceAll("%link%", args[1]).replaceFirst("live", ""));
	              Bukkit.broadcastMessage("");
	            }
	        
	        if (args[0].equalsIgnoreCase("video")) {
	            Bukkit.broadcastMessage("");
	            Bukkit.broadcastMessage(Main.aqui.getMensagens().getString("Divulgando-Video").replaceAll("&", "§").replaceAll("%player%", nome));
	            Bukkit.broadcastMessage(Main.aqui.getMensagens().getString("Link").replaceAll("&", "§").replaceAll("%link%", args[1]).replaceFirst("video", ""));
	            Bukkit.broadcastMessage("");
	          }
	        if (args[0].equalsIgnoreCase("outro")) {
	            Bukkit.broadcastMessage("");
	            Bukkit.broadcastMessage(Main.aqui.getMensagens().getString("Divulgando-Outro").replaceAll("&", "§").replaceAll("%player%", nome));
	            Bukkit.broadcastMessage(Main.aqui.getMensagens().getString("Link").replaceAll("&", "§").replaceAll("%link%", args[1]).replaceFirst("outro", ""));
	            Bukkit.broadcastMessage("");
	        		}
            		Sender.sendMessage(Main.aqui.getMensagens().getString("Link-Invalido").replaceAll("&", "§").replaceAll("%link%", args[1]));
	       			}
	    		}
	    	}
		return false;
	  }
}

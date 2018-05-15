package rush.comandos;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import rush.utils.ConfigManager;

public class ComandoGamemode implements Listener, CommandExecutor {
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
	    if (cmd.getName().equalsIgnoreCase("gamemode")) {
	    	if (!(s instanceof Player)) {
	    		
	    		if(args.length != 2) {
	    	       s.sendMessage(ConfigManager.getConfig("mensagens").getString("Gamemode-Comando-Incorreto").replace("&", "§"));
		    	   return false;
	    		}
	    		  
	    		Player p = Bukkit.getPlayer(args[1]);
	    		if (p == null) {
	    			s.sendMessage(ConfigManager.getConfig("mensagens").getString("Player-Offline").replace("&", "§"));
	    			return false;
	    		}
	    		  
	    		GameMode novo = getGameMode(args[0]);
	    		if (novo == null) {
	    			s.sendMessage(ConfigManager.getConfig("mensagens").getString("Gamemode-Invalido").replace("&", "§").replace("%gm%", args[0]));
	    			return false;
	    		}	
	    		  
	    		GameMode esta = p.getGameMode();
	    		if (esta.equals(novo)) {
	    			s.sendMessage(ConfigManager.getConfig("mensagens").getString("Gamemode-Ja-Esta-Outro").replace("&", "§").replace("%gm%", novo.name()).replace("%player%", p.getName()));
	    			return false;
	    		}
	    		
	    		p.setGameMode(novo);
	    		s.sendMessage(ConfigManager.getConfig("mensagens").getString("Gamemode-Definido-Outro").replace("&", "§").replace("%gm%", novo.name()).replace("%player%", p.getName()));
	    		return false;
	    	}
	    	  
	    	if(args.length > 2 || args.length < 1) {
	    	   s.sendMessage(ConfigManager.getConfig("mensagens").getString("Gamemode-Comando-Incorreto").replace("&", "§"));
	    	   return false;
	    	}
	    	
	    	if (args.length == 2) {
	    		if(!s.hasPermission("system.gamemode.outros")) {
	    		   s.sendMessage(ConfigManager.getConfig("mensagens").getString("Gamemode-Outro-Sem-Permissao").replace("&", "§"));
		    	   return false;
	    		}
	    		
	    		Player p = Bukkit.getPlayer(args[1]);
	    		if (p == null) {
	    			s.sendMessage(ConfigManager.getConfig("mensagens").getString("Player-Offline").replace("&", "§"));
	    			return false;
	    		}
	    		  
	    		GameMode novo = getGameMode(args[0]);
	    		GameMode esta = p.getGameMode();
	    		if (novo == null) {
	    			s.sendMessage(ConfigManager.getConfig("mensagens").getString("Gamemode-Invalido").replace("&", "§").replace("%gm%", args[0]));
	    			return false;
	    		}	
	    		  
	    		if (esta.equals(novo)) {
	    			s.sendMessage(ConfigManager.getConfig("mensagens").getString("Gamemode-Ja-Esta-Outro").replace("&", "§").replace("%gm%", novo.name()).replace("%player%", p.getName()));
	    			return false;
	    		}
	    		
	    		p.setGameMode(novo);
	    		s.sendMessage(ConfigManager.getConfig("mensagens").getString("Gamemode-Definido-Outro").replace("&", "§").replace("%gm%", novo.name()).replace("%player%", p.getName()));
	    		return false;
	    	}
	    	
  		  
	    	GameMode novo = getGameMode(args[0]);
	    	if (novo == null) {
	    		s.sendMessage(ConfigManager.getConfig("mensagens").getString("Gamemode-Invalido").replace("&", "§").replace("%gm%", args[0]));
	    		return false;
	    	}
	    		
	    	Player p = (Player)s;
	    	GameMode esta = p.getGameMode();
	    	if (esta.equals(novo)) {
	    		s.sendMessage(ConfigManager.getConfig("mensagens").getString("Gamemode-Ja-Esta-Voce").replace("&", "§").replace("%gm%", novo.name()));
	    		return false;
	    	}
	    		
	    	boolean possuiPerm = hasPermission(novo, p);
	    	if (possuiPerm == false) {
	    		s.sendMessage(ConfigManager.getConfig("mensagens").getString("Gamemode-Sem-Permissao-Tipo").replace("&", "§").replace("%gm%", novo.name()));
	    		return false;
	    	}

	    	p.setGameMode(novo);
	    	s.sendMessage(ConfigManager.getConfig("mensagens").getString("Gamemode-Definido-Voce").replace("&", "§").replace("%gm%", novo.name()));
	    }
	    return false;
	}
	
	public boolean hasPermission(GameMode gm, Player p) {
		switch (gm) {
			case SURVIVAL:
				if(p.hasPermission("system.gamemode.survival")) 
					return true; else return false;
			case CREATIVE:
				if(p.hasPermission("system.gamemode.survival")) 
					return true; else return false;
			case ADVENTURE:
				if(p.hasPermission("system.gamemode.adventure")) 
					return true; else return false;
			case SPECTATOR:
				if(p.hasPermission("system.gamemode.spectator")) 
					return true; else return false; 
			default:
				return false;
		}
	}
	
	public GameMode getGameMode(String valor) {
		try {
			int gm = Integer.valueOf(valor);
			switch (gm) {
				case 0:
					return GameMode.SURVIVAL;
				case 1:
					return GameMode.CREATIVE;
				case 2:
					return GameMode.ADVENTURE;
				case 3:
					return GameMode.SPECTATOR;
				default:
					return null;
			}
		}
		catch (NumberFormatException e) {
			String gm = valor.toLowerCase();
			switch (gm) {
				case "survival":
					return GameMode.SURVIVAL;
				case "creative" :
					return GameMode.CREATIVE;
				case "adventure":
					return GameMode.ADVENTURE;
				case "spectator":
					return GameMode.SPECTATOR;
				default:
					return null;
			}
		}
	}
}
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
	public boolean onCommand(CommandSender s, Command cmd, String commandlabel, String[] args) {
	    if (cmd.getName().equalsIgnoreCase("gamemode")) {
	    	if (!(s instanceof Player)) {
	    		if(args.length != 2) {
	    	       s.sendMessage(ConfigManager.getConfig("mensagens").getString("Gamemode-Comando-Incorreto").replaceAll("&", "§"));
		    	   return false;
	    		}
	    		  
	    		Player p = Bukkit.getPlayer(args[1]);
	    		if (p == null) {
	    			s.sendMessage(ConfigManager.getConfig("mensagens").getString("Player-Offline").replaceAll("&", "§"));
	    			return false;
	    		}
	    		  
	    		GameMode novo = getGameMode(args[0]);
	    		if (novo == null) {
	    			s.sendMessage(ConfigManager.getConfig("mensagens").getString("Gamemode-Invalido").replaceAll("&", "§").replace("%gm%", args[0]));
	    			return false;
	    		}	
	    		  
	    		GameMode esta = p.getGameMode();
	    		if (esta.equals(novo)) {
	    			s.sendMessage(ConfigManager.getConfig("mensagens").getString("Gamemode-Ja-Esta-Outro").replaceAll("&", "§").replace("%gm%", novo.name()).replace("%player%", p.getName()));
	    			return false;
	    		}
	    		
	    		p.setGameMode(novo);
	    		s.sendMessage(ConfigManager.getConfig("mensagens").getString("Gamemode-Definido-Outro").replaceAll("&", "§").replace("%gm%", novo.name()).replace("%player%", p.getName()));
	    		return false;
	    	}
	    	
	    	if (!(s.hasPermission("system.gamemode"))) {
				s.sendMessage(ConfigManager.getConfig("mensagens").getString("Sem-Permissao").replace("&", "§"));
	    		return false;
	    	}
	    	  
	    	if(args.length > 2 || args.length < 1) {
	    	   s.sendMessage(ConfigManager.getConfig("mensagens").getString("Gamemode-Comando-Incorreto").replaceAll("&", "§"));
	    	   return false;
	    	}
    		  
	    	if (args.length == 1) {
	    		GameMode novo = getGameMode(args[0]);
	    		if (novo == null) {
	    			s.sendMessage(ConfigManager.getConfig("mensagens").getString("Gamemode-Invalido").replaceAll("&", "§").replace("%gm%", args[0]));
	    			return false;
	    		}
	    		
	    		Player p = (Player)s;
	    		GameMode esta = p.getGameMode();
	    		if (esta.equals(novo)) {
	    			s.sendMessage(ConfigManager.getConfig("mensagens").getString("Gamemode-Ja-Esta-Voce").replaceAll("&", "§").replace("%gm%", novo.name()));
	    			return false;
	    		}
	    		
	    		boolean possuiPerm = hasPermission(novo, p);
	    		if (possuiPerm == false) {
	    			s.sendMessage(ConfigManager.getConfig("mensagens").getString("Gamemode-Sem-Permissao-Tipo").replaceAll("&", "§").replace("%gm%", novo.name()));
	    			return false;
	    		}

	    		p.setGameMode(novo);
	    		s.sendMessage(ConfigManager.getConfig("mensagens").getString("Gamemode-Definido-Voce").replaceAll("&", "§").replace("%gm%", novo.name()));
	    		return false;
	    	}
	    	
	    	if (args.length == 2) {
	    		if(!s.hasPermission("system.gamemode.outros")) {
	    		   s.sendMessage(ConfigManager.getConfig("mensagens").getString("Gamemode-Outro-Sem-Permissao").replace("&", "§"));
		    	   return false;
	    		}
	    		
	    		Player p = Bukkit.getPlayer(args[1]);
	    		if (p == null) {
	    			s.sendMessage(ConfigManager.getConfig("mensagens").getString("Player-Offline").replaceAll("&", "§"));
	    			return false;
	    		}
	    		  
	    		GameMode novo = getGameMode(args[0]);
	    		GameMode esta = p.getGameMode();
	    		if (novo == null) {
	    			s.sendMessage(ConfigManager.getConfig("mensagens").getString("Gamemode-Invalido").replaceAll("&", "§").replace("%gm%", args[0]));
	    			return false;
	    		}	
	    		  
	    		if (esta.equals(novo)) {
	    			s.sendMessage(ConfigManager.getConfig("mensagens").getString("Gamemode-Ja-Esta-Outro").replaceAll("&", "§").replace("%gm%", novo.name()).replace("%player%", p.getName()));
	    			return false;
	    		}
	    		
	    		p.setGameMode(novo);
	    		s.sendMessage(ConfigManager.getConfig("mensagens").getString("Gamemode-Definido-Outro").replaceAll("&", "§").replace("%gm%", novo.name()).replace("%player%", p.getName()));
	    		return false;
	    	}
	    }
	    return false;
	}
	
	public boolean hasPermission(GameMode gm, Player p) {
		if (gm.equals(GameMode.SURVIVAL))
			if(p.hasPermission("system.gamemode.survival")) 
				return true; else return false;
		if (gm.equals(GameMode.CREATIVE))
			if(p.hasPermission("system.gamemode.creative")) 
				return true; else return false;
		if (gm.equals(GameMode.ADVENTURE))
			if(p.hasPermission("system.gamemode.adventure")) 
				return true; else return false;
		if (gm.equals(GameMode.SPECTATOR))
			if(p.hasPermission("system.gamemode.spectator")) 
				return true; else return false; 
		else
			return false;
	}
	
	public GameMode getGameMode(String valor) {
		try {
			int gm = Integer.valueOf(valor);
			if (gm == 0) return GameMode.SURVIVAL;
			if (gm == 1) return GameMode.CREATIVE;
			if (gm == 2) return GameMode.ADVENTURE;
			if (gm == 3) return GameMode.SPECTATOR;
			else return null;
		}
		catch (NumberFormatException e) {
			String gm = valor.toLowerCase();
			if (gm.equals("survival")) return GameMode.SURVIVAL;
			if (gm.equals("adventure")) return GameMode.ADVENTURE;
			if (gm.equals("creative") || gm.equals("criativo")) return GameMode.CREATIVE;
			if (gm.equals("spectator") || gm.equals("espectador")) return GameMode.ADVENTURE;
			else return null;
		}
	}
}
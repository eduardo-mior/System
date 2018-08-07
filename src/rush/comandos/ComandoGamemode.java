package rush.comandos;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rush.configuracoes.Mensagens;

public class ComandoGamemode implements CommandExecutor {
	
	@SuppressWarnings("deprecation")	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("gamemode")) {
	    	
			// Verificando se o sender é um player
	    	if (!(s instanceof Player)) {
	    		
				// Verificando se o sender digitou o número de argumentos correto
	    		if(args.length != 2) {
	    	       s.sendMessage(Mensagens.Gamemode_Comando_Incorreto);
		    	   return false;
	    		}
	    		  
				// Pegando o player e verificando se ele esta online
	    		Player p = Bukkit.getPlayer(args[1]);
	    		if (p == null) {
	    			s.sendMessage(Mensagens.Player_Offline);
	    			return false;
	    		}
	    		  
	    		// Pegando o novo gamemode a partir do argumento digitado e verificando se é valido
	    		GameMode novo = getNewGameMode(args[0]);
	    		if (novo == null) {
	    			s.sendMessage(Mensagens.Gamemode_Invalido.replace("%gm%", args[0]));
	    			return false;
	    		}	
	    		  
	    		// Pegando o gamemode atual do player e verificando se o gamemode atual é o mesmo que o gamemode novo
	    		GameMode esta = p.getGameMode();
	    		if (esta.equals(novo)) {
	    			s.sendMessage(Mensagens.Gamemode_Ja_Esta_Outro.replace("%gm%", novo.name()).replace("%player%", p.getName()));
	    			return false;
	    		}
	    		
	    		// Setando o gamemode novo para o player
	    		p.setGameMode(novo);
	    		s.sendMessage(Mensagens.Gamemode_Definido_Outro.replace("%gm%", novo.name()).replace("%player%", p.getName()));
	    		return false;
	    	}
	    	  
			// Verificando se o player digitou o número de argumentos correto
	    	if(args.length > 2 || args.length < 1) {
	    	   s.sendMessage(Mensagens.Gamemode_Comando_Incorreto);
	    	   return false;
	    	}
	    	
	    	// Caso ele digite dois argumentos entende-se que ele quer alterar o gamemode de outro player
	    	if (args.length == 2) {
	    		
	    		// Verificando se o player possui permissão para isso
	    		if(!s.hasPermission("system.gamemode.outros")) {
	    		   s.sendMessage(Mensagens.Gamemode_Outro_Sem_Permissao);
		    	   return false;
	    		}
	    		
	    		// Pegando o novo gamemode a partir do argumento digitado e verificando se é valido
	    		GameMode novo = getNewGameMode(args[0]);
	    		if (novo == null) {
	    			s.sendMessage(Mensagens.Gamemode_Invalido.replace("%gm%", args[0]));
	    			return false;
	    		}	
	    		
	    		// Pegando o player e verificando se ele esta online
	    		Player p = Bukkit.getPlayer(args[1]);
	    		if (p == null) {
	    			s.sendMessage(Mensagens.Player_Offline);
	    			return false;
	    		}
	    		  
	    		// Pegando o gamemode atual do player e verificando se o gamemode atual é o mesmo que o gamemode novo
	    		GameMode esta = p.getGameMode();
	    		if (esta.equals(novo)) {
	    			s.sendMessage(Mensagens.Gamemode_Ja_Esta_Outro.replace("%gm%", novo.name()).replace("%player%", p.getName()));
	    			return false;
	    		}
	    		
	    		// Setando o gamemode novo para o player
	    		p.setGameMode(novo);
	    		s.sendMessage(Mensagens.Gamemode_Definido_Outro.replace("%gm%", novo.name()).replace("%player%", p.getName()));
	    		return false;
	    	}
	    	
	    	/** Caso o player informe a penas um argumento o código abaixo sera executado */
	    	
    		// Pegando o novo gamemode a partir do argumento digitado e verificando se é valido
	    	GameMode novo = getNewGameMode(args[0]);
	    	if (novo == null) {
	    		s.sendMessage(Mensagens.Gamemode_Invalido.replace("%gm%", args[0]));
	    		return false;
	    	}
	    	
    		// Pegando o player, o gamemode atual do player e verificando se o gamemode atual é o mesmo que o gamemode novo
	    	Player p = (Player)s;
	    	GameMode esta = p.getGameMode();
	    	if (esta.equals(novo)) {
	    		s.sendMessage(Mensagens.Gamemode_Ja_Esta_Voce.replace("%gm%", novo.name()));
	    		return false;
	    	}
	    	
    		// Verificando se o player possui permissão para entrar nesse gamemode
	    	boolean possuiPerm = hasPermission(novo, p);
	    	if (!possuiPerm) {
	    		s.sendMessage(Mensagens.Gamemode_Sem_Permissao_Tipo.replace("%gm%", novo.name()));
	    		return false;
	    	}

	    	p.setGameMode(novo);
	    	s.sendMessage(Mensagens.Gamemode_Definido_Voce.replace("%gm%", novo.name()));
		}
		return false;
	}
	
	// Método para verificar se o player possui permissão para entrar no gamemode especifico
	private boolean hasPermission(GameMode gm, Player p) {
		switch (gm) {
			case SURVIVAL:
				if (p.hasPermission("system.gamemode.survival")) 
					return true; else return false;
			case CREATIVE:
				if (p.hasPermission("system.gamemode.survival")) 
					return true; else return false;
			case ADVENTURE:
				if (p.hasPermission("system.gamemode.adventure")) 
					return true; else return false;
			case SPECTATOR:
				if (p.hasPermission("system.gamemode.spectator")) 
					return true; else return false; 
			default:
				return false;
		}
	}
	
	// Método para pegar o gamemode que o player quer entrar a partir de um valor especifico
	private GameMode getNewGameMode(String gamemode) {
		try {
			int gm = Integer.valueOf(gamemode);
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
			String gm = gamemode.toLowerCase();
			switch (gm) {
				case "survival":
					return GameMode.SURVIVAL;
				case "creative":
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
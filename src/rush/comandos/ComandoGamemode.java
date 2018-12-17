package rush.comandos;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rush.configuracoes.Mensagens;
import rush.enums.GameModeName;

@SuppressWarnings("all")
public class ComandoGamemode implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
	    	
		// Verificando se o sender é um player
    	if (!(s instanceof Player)) {
    		
			// Verificando se o sender digitou o número de argumentos correto
    		if(args.length != 2) {
    	       s.sendMessage(Mensagens.Gamemode_Comando_Incorreto);
    	       return true;
    		}
    		  
			// Pegando o player e verificando se ele esta online
    		Player p = Bukkit.getPlayer(args[1]);
    		if (p == null) {
    			s.sendMessage(Mensagens.Player_Offline);
    			return true;
    		}
    		  
    		// Pegando o novo gamemode a partir do argumento digitado e verificando se é valido
    		GameMode novo = getNewGameMode(args[0]);
    		if (novo == null) {
    			s.sendMessage(Mensagens.Gamemode_Invalido.replace("%gm%", args[0]));
    			return true;
    		}	
    		  
    		// Pegando o gamemode atual do player e verificando se o gamemode atual é o mesmo que o gamemode novo
    		GameMode esta = p.getGameMode();
    		if (esta.equals(novo)) {
    			s.sendMessage(Mensagens.Gamemode_Ja_Esta_Outro.replace("%gm%", GameModeName.valueOf(novo).getName()).replace("%player%", p.getName()));
    			return true;
    		}
    		
    		// Setando o gamemode novo para o player
    		p.setGameMode(novo);
    		s.sendMessage(Mensagens.Gamemode_Definido_Outro.replace("%gm%", GameModeName.valueOf(novo).getName()).replace("%player%", p.getName()));
			return true;
    	}
    	  
		// Verificando se o player digitou o número de argumentos correto
    	if(args.length > 2 || args.length < 1) {
    	   s.sendMessage(Mensagens.Gamemode_Comando_Incorreto);
			return true;
    	}
    	
    	// Caso ele digite dois argumentos entende-se que ele quer alterar o gamemode de outro player
    	if (args.length == 2) {
    		
    		// Verificando se o player possui permissão para isso
    		if(!s.hasPermission("system.gamemode.outros")) {
    		   s.sendMessage(Mensagens.Gamemode_Outro_Sem_Permissao);
    		   return true;
    		}
    		
    		// Pegando o novo gamemode a partir do argumento digitado e verificando se é valido
    		GameMode novo = getNewGameMode(args[0]);
    		if (novo == null) {
    			s.sendMessage(Mensagens.Gamemode_Invalido.replace("%gm%", args[0]));
    			return true;
    		}	
    		
    		// Pegando o player e verificando se ele esta online
    		Player p = Bukkit.getPlayer(args[1]);
    		if (p == null) {
    			s.sendMessage(Mensagens.Player_Offline);
    			return true;
    		}
    		  
    		// Pegando o gamemode atual do player e verificando se o gamemode atual é o mesmo que o gamemode novo
    		GameMode esta = p.getGameMode();
    		if (esta.equals(novo)) {
    			s.sendMessage(Mensagens.Gamemode_Ja_Esta_Outro.replace("%gm%", GameModeName.valueOf(novo).getName()).replace("%player%", p.getName()));
    			return true;
    		}
    		
    		// Setando o gamemode novo para o player
    		p.setGameMode(novo);
    		s.sendMessage(Mensagens.Gamemode_Definido_Outro.replace("%gm%", GameModeName.valueOf(novo).getName()).replace("%player%", p.getName()));
			return true;
    	}
    	
    	/** Caso o player informe a penas um argumento o código abaixo sera executado */
    	
		// Pegando o novo gamemode a partir do argumento digitado e verificando se é valido
    	GameMode novo = getNewGameMode(args[0]);
    	if (novo == null) {
    		s.sendMessage(Mensagens.Gamemode_Invalido.replace("%gm%", args[0]));
			return true;
    	}
    	
		// Pegando o player, o gamemode atual do player e verificando se o gamemode atual é o mesmo que o gamemode novo
    	Player p = (Player)s;
    	GameMode esta = p.getGameMode();
    	if (esta.equals(novo)) {
    		s.sendMessage(Mensagens.Gamemode_Ja_Esta_Voce.replace("%gm%", GameModeName.valueOf(novo).getName()));
			return true;
    	}
    	
		// Verificando se o player possui permissão para entrar nesse gamemode
    	boolean possuiPerm = hasPermission(novo, p);
    	if (!possuiPerm) {
    		s.sendMessage(Mensagens.Gamemode_Sem_Permissao_Tipo.replace("%gm%", GameModeName.valueOf(novo).getName()));
			return true;
    	}

    	// Setando o novo gamemode
    	p.setGameMode(novo);
    	s.sendMessage(Mensagens.Gamemode_Definido_Voce.replace("%gm%", GameModeName.valueOf(novo).getName()));
		return true;
	}
	
	// Método para verificar se o player possui permissão para entrar no gamemode especifico
	private boolean hasPermission(GameMode gm, Player p) {
		String perm = "system.gamemode." + gm.name().toLowerCase();
		if (p.hasPermission(perm)) return true;
		else return false;
	}
	
	// Método para pegar o gamemode que o player quer entrar a partir de um valor especifico
	private GameMode getNewGameMode(String gamemode) {
		
		// Tentando retornar um gamemode por número
		try {
			int gm = Integer.parseInt(gamemode);
			try {return GameMode.getByValue(gm);} catch (Exception | Error e) {return null;}
		}
		
		// Tentando retornar um gamemode por nome
		catch (NumberFormatException ex) {
			String gm = gamemode.toUpperCase();
			try {return GameMode.valueOf(gm);} catch (Exception | Error e) {return null;}
		}
	}
}
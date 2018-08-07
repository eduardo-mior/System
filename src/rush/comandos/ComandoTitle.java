package rush.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rush.configuracoes.Mensagens;

public class ComandoTitle implements CommandExecutor {
	
	@SuppressWarnings("deprecation")	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("title")) {		    	
		        
			// Verificando se o player digitou o número de argumentos corretos
			if (args.length < 1) {
				s.sendMessage(Mensagens.Title_Comando_Incorreto);
				return false;
			}
			
			// Pegando a mensagem do title
			String msg = "";
			for (int i = 0; i < args.length; i++) {
				msg += args[i] + " "; 
			}
		            	
			// Colocando cores na mensagem e divindo a mensagem em title e subtitle
	    	String msg2 = msg.replace(" ", "¨").replace("&", "§");
	    	String[] msg3 = msg2.split("<nl>");    
    		
	    	// Enviando o title para todos os players do serivdor
	    	for (Player todos : Bukkit.getOnlinePlayers()) {
	    		
	    		// Caso a mensagem não contenha "<nl>" então apenas o title é enviado
	    		if (!(msg2.contains("<nl>"))) {
	    			todos.sendTitle(msg, "");
	    			
	    		// Caso a mensagem conter "<nl>" então o title e o subtitle é enviado
	    		} else {
	    			todos.sendTitle(msg3[0].replace("¨", " "), msg3[1].replace("¨", " "));
	    		}
	    	}
	    	
	    	// Informando que o title foi enviado
			s.sendMessage(Mensagens.Title_Enviado);
	    }
	    return false;
	}
}

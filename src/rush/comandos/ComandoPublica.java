package rush.comandos;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import rush.configuracoes.Mensagens;
import rush.utils.DataManager;

public class ComandoPublica implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		 
		// Verificando se o sender é um player
		if (!(s instanceof Player)) {
			s.sendMessage(Mensagens.Console_Nao_Pode); 
			return true;
		}
		
		// Verificando se o player digitou o número de argumentos corretos
		if (args.length != 1) {
			s.sendMessage(Mensagens.Publica_Comando_Incorreto);
			return true;
		} 
		
		// Pegando a home, o player o arquivo do player e as homes do player
	    String home = args[0];
  		String player = s.getName().toLowerCase();
        File file = DataManager.getFile(player.toLowerCase(), "playerdata");
        FileConfiguration config = DataManager.getConfiguration(file);
        Set<String> KEYS = config.getConfigurationSection("Homes").getKeys(false);
	   		
        // Verificando se a home existe
        if (!KEYS.contains(home)) {
        	s.sendMessage(Mensagens.Home_Nao_Existe.replace("%home%", home));
   			ComandoHomes.ListHomes(s, player);
			return true;
   		}
   		
        // Verificando se a home já é publica
        boolean isPublic = config.getBoolean("Homes." + home + ".Publica");
        if (isPublic) {
        	s.sendMessage(Mensagens.Home_Ja_Publica.replace("%home%", home));
			return true;
        }
        
	    // Setando a home como publica salvando no arquivo
    	config.set("Homes." + home + ".Publica" , true);
    	try {
    		config.save(file);
    		s.sendMessage(Mensagens.Tornou_Home_Publica.replace("%home%", home));
    	} catch (IOException e) {
    		Bukkit.getConsoleSender().sendMessage(Mensagens.Falha_Ao_Salvar.replace("%arquivo%", file.getName()));
    	}
		return true;
	}
}
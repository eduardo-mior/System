package rush.comandos;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import rush.configuracoes.Mensagens;
import rush.utils.DataManager;

public class ComandoDelhome implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
			
		// Verificando se o player digitou o número de argumentos corretos
		if (args.length != 1) {
			s.sendMessage(Mensagens.DelHome_Comando_Incorreto);
			return true;
		} 
		     
		// Pegando a home, o arquivo da database do player e a lista de homes
		String home = args[0];
		File file = DataManager.getFile(s.getName().toLowerCase(), "playerdata");
		FileConfiguration config = DataManager.getConfiguration(file);
		Set<String> HOMES = config.getConfigurationSection("Homes").getKeys(false);
		   	
		// Verificando se o player possui a home
		if (!HOMES.contains(home)) {
			s.sendMessage(Mensagens.Home_Nao_Existe.replace("%home%", home));
			ComandoHomes.ListHomes(s, s.getName());
			return true;
		}
		   	
		// Deletando a home e salvando e salvando a config
		config.set("Homes." + home, null);
		s.sendMessage(Mensagens.Home_Deletada.replace("%home%", home));
		try {
			config.save(file);
		} catch (IOException e) {
			Bukkit.getConsoleSender().sendMessage(Mensagens.Falha_Ao_Salvar.replace('&', '§').replace("%arquivo%", file.getName()));
		}
		return true;
	}
}

package rush.comandos;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import rush.configuracoes.Mensagens;
import rush.utils.DataManager;

public class ComandoHomes implements CommandExecutor {
	
	@Override
	public boolean onCommand(final CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("homes")) {
		     
			// Verificando se o player digitou o número de argumentos corretos
			if (args.length > 1) {
				s.sendMessage(Mensagens.Homes_Comando_Incorreto);
				return false;
			}
			 
			// Verificando se o player que listar as suas homes ou as homes de outras pessoas
			String player;
			if (args.length == 1) {
				player = args[0].replace(":", "");
			} else {
				player = s.getName();
			}
			 
			// Chamando o método para listar suas homes
			ListHomes(s, player);
		}
		return false;
	}
	
	// Método para listar as homes de um player
	public static void ListHomes(CommandSender s, String player) {
	
		// Verificando se o player existe
		File file = DataManager.getFile(player.toLowerCase(), "playerdata");
		if (!file.exists()) {
			s.sendMessage(Mensagens.Player_Nao_Existe.replace("%player%", player));
			return;
		}
		
		// Pegando as homes do player
		String separador = Mensagens.Separador_De_Listas;
		FileConfiguration config = DataManager.getConfiguration(file);
		List<String> homesParticulares = new ArrayList<>();
		List<String> homesPublicas = new ArrayList<>();
		Set<String> KEYS = config.getConfigurationSection("Homes").getKeys(false);
		Iterator<String> it = KEYS.iterator();
		while (it.hasNext()) {
			String home = it.next();
			if (config.getBoolean("Homes." + home + ".Publica"))  homesPublicas.add(home);
			else homesParticulares.add(home);	 
		}
		
		// Verificando se o player possui homes e formatando a mensagem
		String particulares = homesParticulares.size() == 0 ? "[§8§oNenhuma]" : homesParticulares.toString();
		String publicas = homesPublicas.size() == 0 ? "[§8§oNenhuma]" : homesPublicas.toString();
		s.sendMessage(Mensagens.Homes_Publicas.replace("%homes%", publicas.substring(1, publicas.length() -1).replace(",", separador)));
		if (player.equals(s.getName()) || s.hasPermission("system.home.admin")) {
			s.sendMessage(Mensagens.Homes_Particulares.replace("%homes%", particulares.substring(1, particulares.length() -1).replace(",", separador)));
		}	
	}
}
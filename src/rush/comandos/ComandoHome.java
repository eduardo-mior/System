package rush.comandos;

import java.io.File;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import rush.Main;
import rush.configuracoes.Mensagens;
import rush.configuracoes.Settings;
import rush.utils.DataManager;

public class ComandoHome implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("home")) {
			 
			// Verificando se o sender é um player
			if (!(s instanceof Player)) {
				s.sendMessage(Mensagens.Console_Nao_Pode);
				return false;
			}
		     
			// Verificando se o player digitou o número de argumentos corretos
			if (args.length != 1) {
				s.sendMessage(Mensagens.Home_Comando_Incorreto);
				return false;
			}
		     
			// Pegando o delay para se teleportar, o nome da home e o Player
			int delay = Settings.Delay_Para_Teleportar_Comandos;
			String home = args[0];
		    Player p = (Player)s;
		    
		    // Verificando se o player que se teleportar para uma home public
		   	if (home.contains(":")) {
		   		String[] homeSplit = home.split(":");
		   		
		   		// Verificando se os argumentos do comando para ir para uma home publica estão corretos
		        if (homeSplit.length < 1 || homeSplit.length > 3) {
		        	s.sendMessage(Mensagens.Home_Outro_Comando_Incorreto);
		        	return false;
		        }
		        
		        // Pegando o nome do dono da home publica e verificando se ele existe
		   		String player = homeSplit[0];
		        File file = DataManager.getFile(player.toLowerCase(), "playerdata");
		        if (!file.exists()) {
		        	s.sendMessage(Mensagens.Player_Nao_Existe.replace("%player%", homeSplit[0]));
		        	return false;
		        }
		        
		        // Caso o player informe apenas o nome do player + ':' significa que ele quer ver a lista de homes
		        if (homeSplit.length == 1) {
		   			ComandoHomes.ListHomes(s, player);
		        	return false;
		        }
		        
		        // Pegando as homes do player e verificando se a home existe
		        FileConfiguration config = DataManager.getConfiguration(file);
		        Set<String> KEYS = config.getConfigurationSection("Homes").getKeys(false);
		        if (!KEYS.contains(homeSplit[1])) {
		        	s.sendMessage(Mensagens.Player_Home_Nao_Existe.replace("%player%", homeSplit[0]).replace("%home%", homeSplit[1]));
		   			ComandoHomes.ListHomes(s, player);
		        	return false;
		       	}
		       	
		        // Verificando se a home é publica e verificando se o player não possui permissão de admin
		        if (!config.getBoolean("Homes." + homeSplit[1] + ".Publica")) {
		        	if (!s.hasPermission("system.home.admin")) {
		        		s.sendMessage(Mensagens.Player_Home_Nao_Publica.replace("%player%", homeSplit[0]).replace("%home%", homeSplit[1]));
		        		return false;
		        	}
		       	}
		        
		        // Pegando a localização da home
				String[] locationSplitted = config.getString("Homes." + homeSplit[1] + ".Localizacao").split(",");
			    Location location = new Location(
			    		Bukkit.getWorld(locationSplitted[0]),
			    		Double.parseDouble(locationSplitted[1]),
			    		Double.parseDouble(locationSplitted[2]),
			    		Double.parseDouble(locationSplitted[3]),
			    		Float.parseFloat(locationSplitted[4]),
			    		Float.parseFloat(locationSplitted[5]));
				  
			    // Teleportando o player para a home
		    	if (!s.hasPermission("system.semdelay")) {
		        	s.sendMessage(Mensagens.Home_Publica_Iniciando_Teleporte.replace("%home%", homeSplit[1]).replace("%tempo%", String.valueOf(delay)).replace("%player%", player));
		    		new BukkitRunnable() {
		    			@Override
		    			public void run() {
				        	s.sendMessage(Mensagens.Home_Publica_Teleportado_Sucesso.replace("%home%", homeSplit[1]).replace("%player%", player));
		    				p.teleport(location);
		    			}
		    		}.runTaskLater(Main.aqui, 20 * delay);
		    		return false;
		    	}
			    	
		    	s.sendMessage(Mensagens.Home_Publica_Teleportado_Sucesso.replace("%home%", homeSplit[1]).replace("%player%", player));
		    	p.teleport(location);
		    	return false;
		    	
		   	}
		   	
		   	/** Caso o argumento digitado não contenha ':' então significa que o player que se teleportar uma home privada */
		   	
		   	// Pegando o nome do player, a sua file, a sua config e suas homes
		   	String player = s.getName();
		   	File file = DataManager.getFile(player.toLowerCase(), "playerdata");
		   	FileConfiguration config = DataManager.getConfiguration(file);
		   	Set<String> KEYS = config.getConfigurationSection("Homes").getKeys(false);
		   	
		   	// Verificando se a home existe
		   	if (!KEYS.contains(home)) {
		   		s.sendMessage(Mensagens.Home_Nao_Existe.replace("%home%", home));
		   		ComandoHomes.ListHomes(s, player);
		   		return false;
		   	}
		   		
		   	// Pegando a localização da home
		   	String[] locationSplitted = config.getString("Homes." + home + ".Localizacao").split(",");
		   	Location location = new Location(
		   			Bukkit.getWorld(locationSplitted[0]),
		   			Double.parseDouble(locationSplitted[1]),
		   			Double.parseDouble(locationSplitted[2]),
		   			Double.parseDouble(locationSplitted[3]),
		   			Float.parseFloat(locationSplitted[4]),
		   			Float.parseFloat(locationSplitted[5]));
		   	
		   	// Teleportando o player para a home
		   	if (!s.hasPermission("system.semdelay")) {
		   		s.sendMessage(Mensagens.Home_Privada_Iniciando_Teleporte.replace("%home%", home).replace("%tempo%", String.valueOf(delay)));
		   		new BukkitRunnable() {
		   			@Override
		   			public void run() {
		   				s.sendMessage(Mensagens.Home_Privada_Teleportado_Sucesso.replace("%home%", home));
		   				p.teleport(location);
		   			}
		   		}.runTaskLater(Main.aqui, 20 * delay);
		   		return false;
		   	}
			    	
		   	s.sendMessage(Mensagens.Home_Privada_Teleportado_Sucesso.replace("%home%", home));
		   	p.teleport(location);
		}
		return false;
	}
}
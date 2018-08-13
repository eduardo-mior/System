package rush.comandos;

import java.io.File;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import rush.Main;
import rush.apis.TitleAPI;
import rush.configuracoes.Mensagens;
import rush.utils.DataManager;
import rush.utils.Serializer;

public class ComandoWarp implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("warp")) {
			
			// Verificando se o sender é um player
			if (!(s instanceof Player)) {
				s.sendMessage(Mensagens.Console_Nao_Pode); 
				return false;
			}
				     
			// Verificando se o sender digitou o número de argumentos correto
			if (args.length != 1) {
				s.sendMessage(Mensagens.Warp_Comando_Incorreto);
				return false;
			}
				     
			// Pegando a warp e verificando se ela existe
			String warp = args[0];
			File file = DataManager.getFile(warp, "warps");
			if (!file.exists()) {
				s.sendMessage(Mensagens.Warp_Nao_Existe.replace("%warp%", warp));
				ComandoWarps.ListWarps(s);
				return false;
			}
			
			// Pegando o player e a localização
			Player p = (Player) s;
			FileConfiguration config = DataManager.getConfiguration(file);
			String locationSplitted = config.getString("Localizacao");
			Location location = Serializer.deserializeLocation(locationSplitted);
			
			// Verificando se o player tem permissão para se teleportar a warp
			if (!s.hasPermission(config.getString("Permissao"))) {
				s.sendMessage(config.getString("MensagemSemPermissao").replace('&', '§'));
				return false;
			} 
				    	
			// Verificando se o player tem permissão para se teleportar sem delay
			if (!s.hasPermission("system.semdelay") || config.getBoolean("DelayParaVips") == true) {
				s.sendMessage(config.getString("MensagemInicio").replace('&', '§'));
				new BukkitRunnable() {
					@Override
					public void run() {
						s.sendMessage(config.getString("MensagemFinal").replace('&', '§'));
						p.teleport(location);
						if (config.getBoolean("EnviarTitle")) {
							TitleAPI.sendTitle(p, 20, 60, 20, config.getString("Title").replace('&', '§'), config.getString("SubTitle").replace('&', '§'));		
						}
					}
				}.runTaskLater(Main.get(), 20 * config.getInt("Delay"));
				return false;
			}
				    	
			// Caso o player tiver permissão para se teleportar sem delay então
			s.sendMessage(config.getString("MensagemFinal").replace('&', '§'));
			p.teleport(location);
			if (config.getBoolean("EnviarTitle")) {
				TitleAPI.sendTitle(p, 20, 60, 20, config.getString("Title").replace('&', '§'), config.getString("SubTitle").replace('&', '§'));
			}
		}
		return false;
	}
}
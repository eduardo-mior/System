package rush.comandos;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import rush.configuracoes.Locations;
import rush.configuracoes.Mensagens;
import rush.utils.manager.DataManager;

public class ComandoSetspawn implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {

		// Verificando se o sender é um player
		if (!(s instanceof Player)) {
			s.sendMessage(Mensagens.Console_Nao_Pode);
			return true;
		}
		
		// Verificando se o sender digitou o número de argumentos correto
		if (args.length != 1) {
			s.sendMessage(Mensagens.SetSpawn_Comando_Incorreto);
			return true;
		}

		// Pegando o player, a sua localização, o arquivo das locations e a config
		Player p = (Player) s;
		Location loc = p.getLocation();
		File file = DataManager.getFile("locations");
		FileConfiguration config = DataManager.getConfiguration(file);

		// Verificando se o player quer setar o spawn normal
		if (args[0].equals("normal")) {
			
			// Alterando a localização no cache serializando a localização e salvando na config
			Locations.spawn = loc;
			p.getWorld().setSpawnLocation(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
			config.set("Spawn", loc);
			config.set("Spawn.world", loc.getWorld().getName());
			config.set("Spawn.x", Double.valueOf(loc.getX()));
			config.set("Spawn.y", Double.valueOf(loc.getY()));
			config.set("Spawn.z", Double.valueOf(loc.getZ()));
			config.set("Spawn.yaw", Float.valueOf(loc.getYaw()));
			config.set("Spawn.pitch", Float.valueOf(loc.getPitch()));
			try {
				config.save(file);
				s.sendMessage(Mensagens.SetSpawn_Normal_Definido);
			} catch (IOException e) {
				Bukkit.getConsoleSender().sendMessage(Mensagens.Falha_Ao_Salvar.replace("%arquivo%", "locations.yml"));
			}
			return true;
		}

		// Verificando se o player quer setar o spawn normal
		if (args[0].equals("vip")) {
		
			// Alterando a localização no cache serializando a localização e salvando na config
			p.getWorld().setSpawnLocation(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
			Locations.spawnVip = loc;
			config.set("SpawnVip", loc);
			config.set("SpawnVip.world", loc.getWorld().getName());
			config.set("SpawnVip.x", Double.valueOf(loc.getX()));
			config.set("SpawnVip.y", Double.valueOf(loc.getY()));
			config.set("SpawnVip.z", Double.valueOf(loc.getZ()));
			config.set("SpawnVip.yaw", Float.valueOf(loc.getYaw()));
			config.set("SpawnVip.pitch", Float.valueOf(loc.getPitch()));
			try {
				config.save(file);
				s.sendMessage(Mensagens.SetSpawn_Vip_Definido);
			} catch (IOException e) {
				Bukkit.getConsoleSender().sendMessage(Mensagens.Falha_Ao_Salvar.replace("%arquivo%", "locations.yml"));
			}
			return true;
		}
		
		// Caso o argumento 1 não seja nem 'normal' nem 'vip' então é dado como comando incorreto
		s.sendMessage(Mensagens.SetSpawn_Comando_Incorreto);
		return true;
	}
}
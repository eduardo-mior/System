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
import rush.utils.DataManager;

public class ComandoSetspawn implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("setspawn")) {

			// Verificando se o sender é um player
			if (!(s instanceof Player)) {
				s.sendMessage(Mensagens.Console_Nao_Pode);
				return true;
			}

			// Pegando o player, a sua localização, o arquivo das locations e a config
			Player p = (Player) s;
			Location spawn = p.getLocation();
			File file = DataManager.getFile("locations");
			FileConfiguration config = DataManager.getConfiguration(file);
			
			// Alterando a localização no cache
			Locations.spawn = spawn;
			
			// Serializando a localização e salvando na config
			config.set("Spawn", spawn);
			config.set("Spawn.world", spawn.getWorld().getName());
			config.set("Spawn.x", Double.valueOf(spawn.getX()));
			config.set("Spawn.y", Double.valueOf(spawn.getY()));
			config.set("Spawn.z", Double.valueOf(spawn.getZ()));
			config.set("Spawn.yaw", Float.valueOf(spawn.getYaw()));
			config.set("Spawn.pitch", Float.valueOf(spawn.getPitch()));
			try {
				config.save(file);
				s.sendMessage(Mensagens.Spawn_Definido);
			} catch (IOException e) {
				Bukkit.getConsoleSender().sendMessage(Mensagens.Falha_Ao_Salvar.replace("%arquivo%", "locations.yml"));
			}
			return true;
		}
		return false;
	}
}
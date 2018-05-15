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
import org.bukkit.event.Listener;

import rush.utils.ConfigManager;
import rush.utils.DataManager;

public class ComandoSetwarp implements Listener, CommandExecutor {
	
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("setwarp")) {
			
			if (!(s instanceof Player)) {
				s.sendMessage(ConfigManager.getConfig("mensagens").getString("Console-Nao-Pode").replace("&", "§"));
				return false;
			}	 
					 
			if (args.length != 1) {
				s.sendMessage(ConfigManager.getConfig("mensagens").getString("SetWarp-Comando-Incorreto").replace("&", "§"));
				return false;
			}
				     
			String warp = args[0];
			File file = DataManager.getFile(warp, "warps");
			FileConfiguration config = DataManager.getConfiguration(file);
			if (file.exists()) {
				s.sendMessage(ConfigManager.getConfig("mensagens").getString("Warp-Ja-Existe").replace("&", "§").replace("%warp%", warp));
				return false;
			}
			
			Player p = (Player) s;
			DataManager.createFile(file);
			Location location = p.getLocation();
			String locationSerialized = location.getWorld().getName() + "," + location.getX() + "," + location.getY() + "," + location.getZ() + "," + location.getYaw() + "," + location.getPitch();
			config.set("Localizacao" , locationSerialized);
			config.set("Permissao" , "system.warp." + warp.toLowerCase());
			config.set("MensagemSemPermissao", "&cVocê não tem permissão para se teleportar para a warp " + warp + "!");
			config.set("Delay", 5);
			config.set("DelayParaVips", false);
			config.set("MensagemInicio" , "&aVocê sera teleportado para a warp " + warp + " em 5 segundos!");
			config.set("MensagemFinal" , "&aTeleportado com sucesso para a warp " + warp + "!");
			config.set("EnviarTitle" , true);
			config.set("Title" , "&aVOCÊ FOI TELEPORTADO");
			config.set("SubTitle" , "&ePARA A WARP " + warp.toUpperCase());
			try {
				config.save(file);
				s.sendMessage(ConfigManager.getConfig("mensagens").getString("Warp-Definida").replace("&", "§").replace("%warp%", warp));
			} catch (IOException e) {
				Bukkit.getConsoleSender().sendMessage(ConfigManager.getConfig("mensagens").getString("Falha-Ao-Salvar").replace("&", "§").replace("%arquivo%", file.getName()));
			}
		}
		return false;
	}
}

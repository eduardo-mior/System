package rush.utils.manager;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import rush.Main;

public class ConfigManager {

	public static void createConfig(String file) {
		if (!new File(Main.get().getDataFolder(), file + ".yml").exists()) {
			Main.get().saveResource(file + ".yml", false); 
		}
	}
	
	public static FileConfiguration getConfig(String file) {
      	File arquivo = new File(Main.get().getDataFolder() + File.separator + file + ".yml");
      	FileConfiguration config = (FileConfiguration)YamlConfiguration.loadConfiguration(arquivo);
      	return config;
	}
	
}
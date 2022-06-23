package rush.utils.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

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
      	try {
      		File arquivo = new File(Main.get().getDataFolder() + File.separator + file + ".yml");
			InputStreamReader arquivoStream = new InputStreamReader(new FileInputStream(arquivo), Charset.forName("UTF-8").name());
			FileConfiguration config = (FileConfiguration)YamlConfiguration.loadConfiguration(arquivoStream);
			return config;
		} catch (Throwable e) {
			e.printStackTrace();
		} 
      	return null;
	}
	
}
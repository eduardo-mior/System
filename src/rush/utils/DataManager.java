package rush.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import rush.Main;
import rush.configuracoes.Mensagens;

public class DataManager {

	public static void createFolder(String folder) {
		try {
			File pasta = new File(Main.aqui.getDataFolder() + File.separator + folder);
			if (!pasta.exists()) {
				pasta.mkdirs();
			}
		} catch (SecurityException e) {
			Bukkit.getConsoleSender().sendMessage(Mensagens.Falha_Ao_Criar_Pasta.replace("%pasta%", folder));
		}
	}

	public static void createFile(File file) {
		try {
			file.createNewFile();
		} catch (IOException e) {
			Bukkit.getConsoleSender().sendMessage(Mensagens.Falha_Ao_Criar_Arquivo.replace("%arquivo%", file.getName()));
		}
	}

	public static File getFolder(String folder) {
		File Arquivo = new File(Main.aqui.getDataFolder() + File.separator + folder);
		return Arquivo;
	}

	public static File getFile(String file, String folder) {
		File Arquivo = new File(Main.aqui.getDataFolder() + File.separator + folder, file + ".yml");
		return Arquivo;
	}

	public static File getFile(String file) {
		File Arquivo = new File(Main.aqui.getDataFolder() + "/" + file + ".yml");
		return Arquivo;
	}

	public static FileConfiguration getConfiguration(File file) {
		FileConfiguration config = (FileConfiguration) YamlConfiguration.loadConfiguration(file);
		return config;
	}

	public static void deleteFile(File file) {
		file.delete();
	}
}

package rush.entidades;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;

import org.bukkit.configuration.file.FileConfiguration;

import rush.utils.DataManager;

public class Kits {

	private static HashMap<String, Kit> KITS = new HashMap<>();
	
	public static Kit get(String kit) {
		return KITS.get(kit);
	}
	
	public static Collection<Kit> getAll() {
		return KITS.values();
	}
	
	public static void create(String nome, Kit kit) {
		KITS.put(nome, kit);
	}
	
	public static void delete(String nome) {
		File file = DataManager.getFile(nome, "kits");
		DataManager.deleteFile(file);
		KITS.remove(nome);
	}
	
	public static boolean contains(String nome) {
		return KITS.containsKey(nome);
	}
	
	public static void loadKits() {
		File folder = DataManager.getFolder("kits");
		File[] file = folder.listFiles();
		
		for (int i = 0; i < file.length; i++) {
			if (file[i].isFile()) {
				FileConfiguration configKit = DataManager.getConfiguration(file[i]);
				String nome = file[i].getName().replace(".yml", "");
				String permissao = configKit.getString("Permissao");
				long delay = configKit.getLong("Delay");
				String itens = configKit.getString("Itens");
				Kit kit = new Kit(nome, permissao, delay, itens);
				KITS.put(nome, kit);
			}
		}	
	}
}

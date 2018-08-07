package rush.entidades;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;

import org.bukkit.configuration.file.FileConfiguration;

import rush.utils.DataManager;

public class Kits {

	private static HashMap<String, Kit> kits = new HashMap<>();
	
	public static Kit get(String kit) {
		return kits.get(kit);
	}
	
	public static Collection<Kit> getAll() {
		return kits.values();
	}
	
	public static void create(String nome, Kit kit) {
		kits.put(nome, kit);
	}
	
	public static void delete(String nome) {
		File file = DataManager.getFile(nome, "kits");
		DataManager.deleteFile(file);
		kits.remove(nome);
	}
	
	public static boolean contains(String nome) {
		return kits.containsKey(nome);
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
				kits.put(nome, kit);
			}
		}	
	}
}

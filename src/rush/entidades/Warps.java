package rush.entidades;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;

import org.bukkit.configuration.file.FileConfiguration;

import rush.utils.DataManager;

public class Warps {

	private static HashMap<String, Warp> WARPS = new HashMap<>();
	
	public static Warp get(String warp) {
		return WARPS.get(warp);
	}
	
	public static Collection<Warp> getAll() {
		return WARPS.values();
	}
	
	public static void create(String nome, Warp warp) {
		WARPS.put(nome, warp);
	}
	
	public static void delete(String nome) {
		File file = DataManager.getFile(nome, "warps");
		DataManager.deleteFile(file);
		WARPS.remove(nome);
	}
	
	public static boolean contains(String nome) {
		return WARPS.containsKey(nome);
	}
	
	public static void loadWarps() {
		File folder = DataManager.getFolder("warps");
		File[] file = folder.listFiles();
		
		for (int i = 0; i < file.length; i++) {
			if (file[i].isFile()) {
				FileConfiguration configWarp = DataManager.getConfiguration(file[i]);
				String nome = file[i].getName().replace(".yml", "");
				String loc = configWarp.getString("Localizacao");
				String perm = configWarp.getString("Permissao");
				String semPerm = configWarp.getString("MensagemSemPermissao");
				int delay = configWarp.getInt("Delay");
				boolean delayVip = configWarp.getBoolean("DelayParaVips");
				String inicio = configWarp.getString("MensagemInicio");
				String fim = configWarp.getString("MensagemFinal");
				boolean enviar = configWarp.getBoolean("EnviarTitle");
				String title = configWarp.getString("Title");
				String subTitle = configWarp.getString("SubTitle");
				Warp warp = new Warp(nome, loc, perm, semPerm, delay, delayVip, inicio, fim, enviar, title, subTitle);
				WARPS.put(nome, warp);
			}
		}	
	}
}

package rush.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.PluginManager;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import rush.Main;

public class Utils {
	
	// ------------------------------
	// JAVA UTILS
	// ------------------------------
	
	public static String bytesToLegibleValue(double bytes) {
		if (bytes < 1024 * 1024)
			return String.format("%.2f KB", bytes);
		else if (bytes < Math.pow(2, 20) * 1024)
			return String.format("%.2f MB", bytes / Math.pow(2, 20));
		else if (bytes < Math.pow(2, 30) * 1024 )
			return String.format("%.2f GB", bytes / Math.pow(2, 30));
		else if (bytes < Math.pow(2, 40) * 1024)
			return String.format("%.2f TB", bytes / Math.pow(2, 40));
		else
			return "N/A (1TB?)";
	}
	
	public static <E extends Enum<E>> boolean isValidEnum(Class<E> enumClass, String enumName) {
		try {
			Enum.valueOf(enumClass, enumName);
			return true;
		} catch (Throwable ex) {
			return false;
		}
	}

	public static <E extends Enum<E>> List<E> getEnumList(Class<E> enumClass) {
		return new ArrayList<E>(Arrays.asList(enumClass.getEnumConstants()));
	}
	
	public static <E extends Enum<E>> List<E> listToListEnum(Class<E> enumClass, List<String> input) {
		List<E> output = new ArrayList<>();
		for (String element : input) {
			output.add(Enum.valueOf(enumClass, element.toUpperCase()));
		}
		return output;
	}
	
	public static List<String> listToUpperCase(List<String> input) {
		List<String> output = new ArrayList<>();
		for (String element : input) {
			output.add(element.toUpperCase());
		}
		return output;
	}
	
	public static List<String> listToLowerCase(List<String> input) {
		List<String> output = new ArrayList<>();
		for (String element : input) {
			output.add(element.toLowerCase());
		}
		return output;
	}
	
	public static Map<String, Long> listSplitToMapMillis(List<String> input, String split) {
		Map<String, Long> output = new HashMap<>();
		for (String element : input) {
			String[] entry = element.split(split);
			String key = entry[0];
			String value = entry[1];
			output.put(key, Long.parseLong(value));
		}
		return output;
	}
	
	public static <K, V> Map<String, String> mapToMapString(Map<K, V> input) {
		Map<String, String> output = new HashMap<>();
		for (Entry<K, V> entry : input.entrySet()) {
			output.put(entry.getKey().toString(), entry.getValue().toString());
		}
		return output;
	}
	
	public static <K, V> Map<String, Integer> mapToMapInt(Map<K, V> input) {
		Map<String, Integer> output = new HashMap<>();
		for (Entry<K, V> entry : input.entrySet()) {
			try {
				output.put(entry.getKey().toString(), Integer.parseInt(entry.getValue().toString()));
			} catch (Throwable e) {}
		}
		return output;
	}
	
	public static List<String> colorizeListString(List<String> input) {
		List<String> output = new ArrayList<>();
		for (int i = 0; i < input.size(); i++) {
			output.add(input.get(i).replace('&', '§'));
		}
		return output;
	}
	
	public static boolean stringContainsSpecialCharacters(String input) {
		return pattern.matcher(input).find();
	}
	
	public static String getSpecialCharacters(String input) {
		Matcher matcher = pattern.matcher(input);
		if (matcher.find()) {
			return matcher.group();
		} else {
			return "";
		}
	}
	
	// ------------------------------
	// BUKKIT UTILS
	// ------------------------------
	
	public static Sound tryLoadSound(String try__, String catch__) {
		try {
			return Sound.valueOf(try__.toUpperCase());
		} catch (Throwable e) {
			return Sound.valueOf(catch__.toUpperCase());
		}
	}
	
	public static boolean hasPotionEffect(Player p, PotionEffectType type, int amplifier) {
		for (PotionEffect effect : p.getActivePotionEffects()) {
			if (effect.getType().equals(type) && effect.getAmplifier() == amplifier) {
				return true;
			}
		}
		return false;
	}
	
    public static String serializeLocation(Location l) {
    	return l.getWorld().getName() + ',' 
    		 + l.getX()   + ','         
    		 + l.getY()   + ',' 
    		 + l.getZ()   + ',' 
    		 + l.getYaw() + ',' 
    		 + l.getPitch();
    }
	
    public static Location deserializeLocation(String s) {
    	String[] location = s.split(",");
		return new Location(
			   Bukkit.getWorld   (location[0]),
			   Double.parseDouble(location[1]),
			   Double.parseDouble(location[2]),
			   Double.parseDouble(location[3]),
			   Float .parseFloat (location[4]),
			   Float .parseFloat (location[5]))
		;
    }
    
	public static Plugin getPluginByName(String name) {
		PluginManager pm = Bukkit.getPluginManager();
		Plugin pl = pm.getPlugin(name);
		if (pl != null) 
			return pl;
		for (Plugin plugin : pm.getPlugins()) 
			if (plugin.getName().equalsIgnoreCase(name)) return plugin;
		return null;
	}

	public static File getPluginJar(String name) {
		for (File file : folder.listFiles())
		{
			if (file.getName().endsWith(".jar"))
			{
				try
				{
					String fileName = file.getName().replace(".jar", "");
					if (fileName.equalsIgnoreCase(name)) return file;
					
					String pluginName = loader.getPluginDescription(file).getName();
					if (pluginName.equalsIgnoreCase(name)) return file;
				}
				catch (Throwable e) {continue;}
			}
		}
		return null;
	}
	
	public static List<File> getAllPluginsJar() {
		List<File> jars = new ArrayList<>();
		for (File file : folder.listFiles())
		{
			if (file.getName().endsWith(".jar"))
			{
				jars.add(file);
			}
		}
		return jars;
	}
	
	// ------------------------------
	// STATIC FINAL FIELDS
	// ------------------------------
	
	private static final File folder = new File("plugins");
	private static final PluginLoader loader = Main.get().getPluginLoader();
	private static final Pattern pattern = Pattern.compile("[^A-zÀ-ü0-9@$]");
	
}
package rush.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Utils {
	
	// ------------------------------
	// JAVA UTILS
	// ------------------------------
	
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
	
	public static <K, V> Map<String, String> mapToMapString(Map<K, V> input) {
		Map<String, String> output = new HashMap<>();
		for (Entry<K, V> entry : input.entrySet()) {
			output.put(entry.getKey().toString(), entry.getValue().toString());
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
			   Bukkit.getWorld(   location[0]),
			   Double.parseDouble(location[1]),
			   Double.parseDouble(location[2]),
			   Double.parseDouble(location[3]),
			   Float.parseFloat(  location[4]),
			   Float.parseFloat(  location[5]))
		;
    }
	
	
}
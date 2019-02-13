package rush.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Sound;

public class Utils {
	
	public static Sound tryLoadSound(String try__, String catch__) {
		try {
			return Sound.valueOf(try__.toUpperCase());
		} catch (Throwable e) {
			return Sound.valueOf(catch__.toUpperCase());
		}
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
	
	public static List<String> colorizeListString(List<String> input) {
		List<String> output = new ArrayList<>();
		for (int i = 0; i < input.size(); i++) {
			output.add(input.get(i).replace('&', '§'));
		}
		return output;
	}
	
}
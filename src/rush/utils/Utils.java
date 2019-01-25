package rush.utils;

import org.bukkit.Sound;

public class Utils {
	
	public static Sound tryLoadSound(String try_, String catch__) {
		try {
			return Sound.valueOf(try_.toUpperCase());
		} catch (Throwable e) {
			return Sound.valueOf(catch__.toUpperCase());
		}
	}
	
}

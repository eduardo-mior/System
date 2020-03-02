package rush.enums;

import org.bukkit.Bukkit;

public enum Version {

	v1_16 (16),
	v1_15 (15),
	v1_14 (14),
	v1_13 (13),
	v1_12 (12),
	v1_11 (11),
	v1_10 (10),
	v1_9  (9),
	v1_8  (8),
	v1_7  (7),
	v1_6  (6),
	v1_5  (5),
	DESCONHECIDA (999);
	
	public static Version getServerVersion() {
		String ver = Bukkit.getVersion();
		if (ver.contains("1.16")) 
			return v1_16;
		else if (ver.contains("1.15")) 
			return v1_15;
		else if (ver.contains("1.14")) 
			return v1_14;
		else if (ver.contains("1.13"))
			return v1_13;
		else if (ver.contains("1.12"))
			return v1_12;
		else if (ver.contains("1.11"))
			return v1_11;
		else if (ver.contains("1.10"))
			return v1_10;
		else if (ver.contains("1.9"))
			return v1_9;
		else if (ver.contains("1.8"))
			return v1_8;
		else if (ver.contains("1.7"))
			return v1_7;
		else if (ver.contains("1.6"))
			return v1_6;
		else if (ver.contains("1.5"))
			return v1_5;
		else
			return DESCONHECIDA;
	}
	
	public int value;
	
	Version(int value) {
		this.value = value;
	}
	
}
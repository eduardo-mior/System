package rush.enums;

import org.bukkit.Bukkit;

public enum JarType {
	
	TACO_SPIGOT,
	PAPER_SPIGOT,
	SPIGOT,
	BUKKIT,
	TORCH,
	PURPUR,
	CAULDRON,
	DESCONHECIDA;
	
	public static JarType getJarType() {
		String ver = Bukkit.getVersion();
		if (ver.contains("-Torch"))
			return JarType.TORCH;
		else if (ver.contains("-Cauldrons"))
			return JarType.CAULDRON;
		else if (ver.contains("-Purpur"))
			return JarType.PURPUR;
		else if (ver.contains("-Taco"))
			return JarType.TACO_SPIGOT;
		else if (ver.contains("-Paper"))
			return JarType.PAPER_SPIGOT;
		else if (ver.contains("-Spigot"))
			return JarType.SPIGOT;
		else if (ver.contains("-Bukkit"))
			return JarType.BUKKIT;
		else
			return JarType.DESCONHECIDA;
	}

}
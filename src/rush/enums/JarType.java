package rush.enums;

import org.bukkit.Bukkit;

public enum JarType {
	
	TACO_SPIGOT,
	PAPER_SPIGOT,
	SPIGOT,
	BUKKIT,
	TORCH,
	CAULDRON,
	DESCONHECIDA;
	
	public static JarType getJarType() {
		String ver = Bukkit.getVersion();
		if (ver.contains("git-Torch"))
			return JarType.TORCH;
		else if (ver.contains("git-Taco"))
			return JarType.TACO_SPIGOT;
		else if (ver.contains("git-Paper"))
			return JarType.PAPER_SPIGOT;
		else if (ver.contains("git-Spigot"))
			return JarType.SPIGOT;
		else if (ver.contains("git-Bukkit"))
			return JarType.BUKKIT;
		else if (ver.contains("git-Cauldron"))
			return JarType.CAULDRON;
		else
			return JarType.DESCONHECIDA;
	}

}
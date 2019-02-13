package rush.enums;

import org.bukkit.GameMode;

public enum GameModeName {
	
	ADVENTURE("aventura"),
	CREATIVE("criativo"),
	SPECTATOR("espectador"),
	SURVIVAL("sobrevivência");
	
	private String name;

	GameModeName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	} 
	
	public static GameModeName valueOf(GameMode gamemode) {
		return valueOf(gamemode.name());
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
}
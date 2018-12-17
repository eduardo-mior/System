package rush.enums;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public enum PotionName {

	ABSORPTION("Absorção"),
	BLINDNESS("Cegueira"),
	CONDUIT_POWER("Proteção do mar"),
	CONFUSION("Náusea"),
	DAMAGE_RESISTANCE("Resistência"),
	DOLPHINS_GRACE("Graça do golfinho"),
	FAST_DIGGING("Pressa"), 
	FIRE_RESISTANCE("Resistência ao fogo"),
	GLOWING("Brilho"),
	HARM("Dano instantâneo"),
	HEAL("Vida instantânea"),
	HEALTH_BOOST("Vida extra"),
	HUNGER("Fome"),
	INCREASE_DAMAGE("Força"),
	INVISIBILITY("Invisibilidade"),
	JUMP("Super pulo"), 
	LEVITATION("Levitação"),
	LUCK("Sorte"),
	NIGHT_VISION("Visão norturna"),
	POISON("Veneno"),
	REGENERATION("Regeneração"),
	SATURATION("Saturação"),
	SLOW("Lentidão"),
	SLOW_DIGGING("Cansaço"),
	SLOW_FALLING("Queda lenta"),
	SPEED("Velocidade"),
	UNLUCK("Má sorte"),
	WATER_BREATHING("Respiração aquática"),
	WEAKNESS("Fraqueza"),
	WITHER("Decomposição");
	
	private String name;
	
	PotionName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public static PotionName valueOf(PotionEffect potionEffect) {
		return PotionName.valueOf(potionEffect.getType());
	}
	
	public static PotionName valueOf(PotionEffectType potionEffect) {
		return PotionName.valueOf(potionEffect.getName());
	}
	
}
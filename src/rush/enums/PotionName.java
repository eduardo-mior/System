package rush.enums;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public enum PotionName {

	ABSORPTION("Absor��o"),
	BAD_OMEN("Mau press�gio"),
	BLINDNESS("Cegueira"),
	CONDUIT_POWER("Prote��o do mar"),
	CONFUSION("N�usea"),
	DAMAGE_RESISTANCE("Resist�ncia"),
	DARKNESS("Escurid�o"),
	DOLPHINS_GRACE("Gra�a do golfinho"),
	FAST_DIGGING("Pressa"), 
	FIRE_RESISTANCE("Resist�ncia ao fogo"),
	GLOWING("Brilho"),
	HARM("Dano instant�neo"),
	HEAL("Vida instant�nea"),
	HEALTH_BOOST("Vida extra"),
	HERO_OF_THE_VILLAGE("Her�i da vila"),
	HUNGER("Fome"),
	INCREASE_DAMAGE("For�a"),
	INVISIBILITY("Invisibilidade"),
	JUMP("Super pulo"), 
	LEVITATION("Levita��o"),
	LUCK("Sorte"),
	NIGHT_VISION("Vis�o noturna"),
	POISON("Veneno"),
	REGENERATION("Regenera��o"),
	SATURATION("Satura��o"),
	SLOW("Lentid�o"),
	SLOW_DIGGING("Cansa�o"),
	SLOW_FALLING("Queda lenta"),
	SPEED("Velocidade"),
	UNLUCK("M� sorte"),
	WATER_BREATHING("Respira��o aqu�tica"),
	WEAKNESS("Fraqueza"),
	WITHER("Decomposi��o");
	
	private String name;
	
	PotionName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public static PotionName valueOf(PotionEffect potionEffect) {
		return valueOf(potionEffect.getType());
	}
	
	public static PotionName valueOf(PotionEffectType potionEffect) {
		return valueOf(potionEffect.getName());
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
}

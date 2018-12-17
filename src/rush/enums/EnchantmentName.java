package rush.enums;

import org.bukkit.enchantments.Enchantment;

public enum EnchantmentName {
	
	ARROW_DAMAGE("Força"),
	ARROW_FIRE("Chama"),
	ARROW_INFINITE("Infinidade"),
	ARROW_KNOCKBACK("Impacto"),
	BINDING_CURSE("Maldição da ligação"),
	CHANNELING("Condutividade"),
	DAMAGE_ALL("Afiação"),
	DAMAGE_ARTHROPODS("Ruína dos artrópodes"),
	DAMAGE_UNDEAD("Julgamento"),
	DEPTH_STRIDER("Passos profundos"),
	DIG_SPEED("Eficiência"),
	DURABILITY("Inquebrável"),
	FIRE_ASPECT("Aspecto flamejante"),
	FROST_WALKER("Passos gelados"),
	IMPALING("Perfuração"),
	KNOCKBACK("Repulsão"),
	LOOT_BONUS_BLOCKS("Fortuna"),
	LOOT_BONUS_MOBS("Pilhagem"),
	LOYALTY("Lealdade"),
	LUCK("Sorte do mar"),
	LURE("Isca"),
	MENDING("Remendo"),
	OXYGEN("Respiração"),
	PROTECTION_ENVIRONMENTAL("Proteção"),
	PROTECTION_EXPLOSIONS("Proteção contra explosões"),
	PROTECTION_FALL("Peso pena"),
	PROTECTION_FIRE("Proteção contra o fogo"),
	PROTECTION_PROJECTILE("Proteção contra projéteis"),
	RIPTIDE("Correnteza"),
	SILK_TOUCH("Toque suave"),
	SWEEPING_EDGE("Alcance"),
	THORNS("Espinhos"),
	VANISHING_CURSE("Maldição do desaparecimento"),
	WATER_WORKER("Afinidade aquática");

	private String name;
	
	EnchantmentName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public static EnchantmentName valueOf(Enchantment enchantment) {
		return EnchantmentName.valueOf(enchantment.getName());
	}
	
}
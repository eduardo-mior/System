package rush.enums;

import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public enum InventoryName {
	
	ANVIL("Bigorna"),
	BEACON("Sinalizador"),
	BREWING("Suporte de poções"),
	CHEST("Baú"),
	CRAFTING("Inventário"), // É aquela parte do inv com 4 slots que da pra craftar
	CREATIVE("Criativo"), // Não sei se é a melhor tradução
	DISPENSER("Ejetor"),
	DROPPER("Liberador"),
	ENCHANTING("Mesa de encantamentos"),
	ENDER_CHEST("Enderchest"),
	FURNACE("Fornalha"),
	HOPPER("Funil"),
	MERCHANT("Aldeão"), // Não sei se é a melhor tradução
	PLAYER("Inventário"),
	SHULKER_BOX("Caixa de Shulker"),
	WORKBENCH("Bancada de trabalho");
	
	private String name;

	InventoryName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	} 
	
	public static InventoryName valueOf(Inventory inventory) {
		return valueOf(inventory.getType());
	}
	
	public static InventoryName valueOf(InventoryType inventoryType) {
		return valueOf(inventoryType.name());
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
}
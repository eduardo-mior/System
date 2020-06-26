package rush.enums;

import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public enum InventoryName {
	
	ANVIL("Bigorna"),
	BARREL("Barril"),
	BEACON("Sinalizador"),
	BLAST_FURNACE("Alto-Forno"),
	BREWING("Suporte de poções"),
	CARTOGRAPHY("Bancada de cartografia"),
	CHEST("Baú"),
	CRAFTING("Inventário"), // É aquela parte do inv com 4 slots que da pra craftar
	CREATIVE("Criativo"), // Não sei se é a melhor tradução
	DISPENSER("Ejetor"),
	DROPPER("Liberador"),
	ENCHANTING("Mesa de encantamentos"),
	ENDER_CHEST("Enderchest"),
	FURNACE("Fornalha"),
	GRINDSTONE("Pedra de amolar"),
	HOPPER("Funil"),
	LECTERN("Bancada de livro"),
	LOOM("Tear"),
	MERCHANT("Aldeão"), // Não sei se é a melhor tradução
	PLAYER("Inventário"),
	SMITHING("Bancada de ferraria"),
	SMOKER("Defumador"),
	STONECUTTER("Cortador de pedras"),
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
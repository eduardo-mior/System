package rush.utils;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class GuiHolder implements InventoryHolder {

	private int id;
	private Map<String, ?> properties;
		
	public GuiHolder(int id) {
		this.id = id;
	}
	
	public GuiHolder(int id, Map<String, ?> properties) {
		this.id = id;
		this.properties = properties;
	}

	public int getId() {
		return id;
	}
	
	public Object getProperty(String property) {
		return properties.get(property);
	}
	
	public boolean hasProperty(String property) {
		return properties.containsKey(property);
	}
	
	@Override
	public Inventory getInventory() {
		return Bukkit.createInventory(this, 27);
	}

}
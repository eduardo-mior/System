package rush.recursos.adicionais;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

import rush.Main;

public class BloquearCrafts implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
    public void aoPrepararCraft(PrepareItemCraftEvent e){
      int itemType = e.getRecipe().getResult().getType().getId();
      if (Main.aqui.getConfig().getIntegerList("Lista-Dos-Crafts-Bloqueados").contains(Integer.valueOf(itemType))){
        e.getInventory().setResult(new ItemStack(Material.AIR));
          }
        }
}

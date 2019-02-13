package rush.sistemas.gerais;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import rush.configuracoes.Settings;
import rush.enums.ItemName;

public class AnunciarMorte implements Listener {

	@EventHandler
	public void aoMorrerAnunciarMorte(PlayerDeathEvent e) {
		
        Player p = e.getEntity();
        
		if (p.getKiller() instanceof Player) {
			
			Player k = (Player) p.getKiller();
			ItemStack item = k.getItemInHand();
			String itemName = "Maozinha";
			if (item != null && item.getType() != Material.AIR) {
				if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
					itemName = item.getItemMeta().getDisplayName();
				} else {
					itemName = ItemName.valueOf(item).getName();
				}
			}
			
		    if (Settings.Anuncios_Mostrar_Para_Quem_Matou) {
		    	k.sendMessage(Settings.Anuncios_Mensagem_Para_Matador.replace("%item%", itemName).replace("%playerMorreu%", e.getEntity().getName()));
			}
		    
		    if (Settings.Anuncios_Mostrar_Para_Quem_Morreu) {
		    	p.sendMessage(Settings.Anuncios_Mensagem_Para_Defunto.replace("%item%", itemName).replace("%playerMatou%", k.getName()));
			}
		    
		    if (Settings.Anuncios_Mostrar_Para_Todo_Servidor) {
		    	Bukkit.broadcastMessage(Settings.Anuncios_Mensagem_Para_Todos.replace("%item%", itemName).replace("%playerMatou%", k.getName()).replace("%playerMorreu%", p.getName()));
            }
		}
	}
}

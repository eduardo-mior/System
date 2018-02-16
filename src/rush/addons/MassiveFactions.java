package rush.addons;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;

public class MassiveFactions implements Listener {
	
	@EventHandler
	public void comandoClaim(PlayerCommandPreprocessEvent e) {
        final String cmd = e.getMessage().toLowerCase();
			if (cmd.contains("f claim") || cmd.contains("f proteger") || cmd.contains("f conquistar") || cmd.contains("f dominar")) { 
			e.setMessage("/f claim one");
			return;
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void comandoUnclaimAll(PlayerCommandPreprocessEvent e) {
        final String cmd = e.getMessage().toLowerCase();
        	Player p = e.getPlayer();
        	MPlayer mplayer = MPlayer.get(p);
        	Faction faction = mplayer.getFaction();
        	int terras = faction.getLandCount();
        	
			if (cmd.contains("f desproteger all") || cmd.contains("f abandonar all") || cmd.contains("f unclaim all")) {
				
				if (mplayer.hasFaction()) {
					
					if (mplayer.getRole() == Rel.LEADER || mplayer.getRole() == Rel.OFFICER) {
				
				Inventory inv = Bukkit.createInventory(null, 36, "§8Abandonar todas as terras");
				
				ItemStack confirmar = new ItemStack(Material.WOOL, 1, DyeColor.LIME.getWoolData());
				ItemMeta confirmarmeta = confirmar.getItemMeta();
				confirmarmeta.setDisplayName("§aConfirmar ação");
				ArrayList<String> confirmarlore = new ArrayList<>();
				confirmarlore.add("§7Clique para confirmar.");
				confirmarmeta.setLore(confirmarlore);
				confirmar.setItemMeta(confirmarmeta);
				
				ItemStack negar = new ItemStack(Material.WOOL, 1, DyeColor.RED.getWoolData());
				ItemMeta negarmeta = negar.getItemMeta();
				negarmeta.setDisplayName("§cCancelar ação");
				ArrayList<String> negarlore = new ArrayList<>();
				negarlore.add("§7Clique para cancelar.");
				negarmeta.setLore(negarlore);
				negar.setItemMeta(negarmeta);
				
				ItemStack informacoes = new ItemStack(Material.GRASS);
				ItemMeta informacoesmeta = informacoes.getItemMeta();
				informacoesmeta.setDisplayName("§7Informações");
				ArrayList<String> informacoeslore = new ArrayList<>();
				informacoeslore.add("§7Você esta prestes a abandonar " + terras + "§7 terras");
				informacoesmeta.setLore(informacoeslore);
				informacoes.setItemMeta(informacoesmeta);
				
				inv.setItem(13, informacoes);
				inv.setItem(20, confirmar);
				inv.setItem(24, negar);
				
				p.openInventory(inv);
				
				e.setCancelled(true);
				
			return;
				}
				p.sendMessage(faction + "não deixa você administrar os territórios.");
			}
			p.sendMessage("§cVocê precisa estar em alguma facção para poder utilizar este comando.");
		}
	}
	
	@EventHandler
	public void aoClickar(InventoryClickEvent e) {
		Player p = (Player)e.getWhoClicked();
    	MPlayer mplayer = MPlayer.get(p);
    	String faction = mplayer.getFaction().getName();
		if (e.getInventory().getName().equalsIgnoreCase("§8Abandonar todas as terras")) {
			e.setCancelled(true);
		
		if (e.getSlot() == 24) {
			p.sendMessage("§cAção cancelada com sucesso!");
			p.closeInventory();
			}
		
		if (e.getSlot() == 20) {
			p.performCommand("f unclaim all all" + faction);
			p.closeInventory();
			}
		}
	}
}

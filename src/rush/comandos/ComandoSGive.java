package rush.comandos;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import rush.utils.ConfigManager;

public class ComandoSGive implements Listener, CommandExecutor {	
	
	String[] mobs = {"Porco", "Galinha", "Ovelha", "Vaca", "Esqueleto", "Aranha_da_Caverna", "Aranha", "Creeper", "Coelho", "Iron_Golem", "Wither", "Slime", "Enderman", "Zumbi", "Zumbi_Pigman", "Blaze", "Cubo_De_Magma"};
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
        if (cmd.getName().equalsIgnoreCase("sgive")) {
        
        if (args.length > 3 || args.length < 2) {
        	s.sendMessage(ConfigManager.getConfig("mensagens").getString("Sgive-Comando-Incorreto").replace("&", "§"));
            return false;
        }
        
        if (args.length == 2) {
            final String nome = args[0];
            final String tipo = args[1];
            final Player beneficiado = Bukkit.getPlayer(nome);
            for (String type: mobs) {
            	if (tipo.equals(type)) {
            		if (beneficiado != null) {
		                ItemStack spawner = mobSpawner(0, tipo);
		                beneficiado.getInventory().addItem(spawner);
		                s.sendMessage(ConfigManager.getConfig("mensagens").getString("Spawner-Givado").replace("&", "§").replace("%tipo%", args[1].replace("_", " ")));
		                return false;
            		}
            		s.sendMessage(ConfigManager.getConfig("mensagens").getString("Player-Offline").replace("&", "§"));
            		return false;
            	}
            	s.sendMessage(ConfigManager.getConfig("mensagens").getString("Spawner-Desconhecido").replace("&", "§"));
            	return false;
            }
        }
        
        if (args.length == 3) {
            int quantidade;
            try {
                quantidade = Integer.valueOf(args[2]);
            }
            catch (NumberFormatException e) {
                s.sendMessage(ConfigManager.getConfig("mensagens").getString("Numero-Invalido").replace("&", "§"));
                return false;
            }
        	final String tipo = args[1];
            final String nome = args[0];
            final Player beneficiado = Bukkit.getPlayer(nome);
            	for (String type: mobs) {
            		if (tipo.equals(type)) {
            			if (beneficiado != null) {
			                ItemStack spawner = mobSpawner(quantidade, tipo);
			                beneficiado.getInventory().addItem(spawner);
			                s.sendMessage(ConfigManager.getConfig("mensagens").getString("Spawner-Givado").replace("&", "§").replace("%tipo%", args[1].replace("_", " ")));
			                return false;
            			}
            			s.sendMessage(ConfigManager.getConfig("mensagens").getString("Player-Offline").replace("&", "§"));
            			return false;
            		}
            		s.sendMessage(ConfigManager.getConfig("mensagens").getString("Spawner-Desconhecido").replace("&", "§"));
            	}
        	}
        }
        return false;
    }
	
	public ItemStack mobSpawner(int quantidade, String tipo) {
		 ItemStack mob = new ItemStack(Material.MOB_SPAWNER, quantidade);
         ItemMeta mobMeta = mob.getItemMeta();
         List<String> desc = new ArrayList<>();
         desc.add(ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner").replace("&", "§") + tipo.replace("_", " "));
         mobMeta.setLore(desc);
         mobMeta.setDisplayName(ConfigManager.getConfig("mensagens").getString("Nome-Do-MobSpawner").replace("&", "§"));
         mob.setItemMeta(mobMeta);
         return mob;
	}
}

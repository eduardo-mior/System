package rush.comandos;

import java.util.ArrayList;

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
	
    @SuppressWarnings({ "deprecation", "unchecked", "rawtypes" })
	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (cmd.getName().equalsIgnoreCase("sgive")) {
        if (!sender.hasPermission("system.sgive")) {
            sender.sendMessage(ConfigManager.getConfig("mensagens").getString("Sem-Permissao").replaceAll("&", "§"));
            return true;
        }
        if (args.length >= 4) {
        	sender.sendMessage(ConfigManager.getConfig("mensagens").getString("Sgive-Comando-Incorreto").replaceAll("&", "§"));
            return true;
        }
        if (args.length <= 1) {
        	sender.sendMessage(ConfigManager.getConfig("mensagens").getString("Sgive-Comando-Incorreto").replaceAll("&", "§"));
            return true;
        }
        
        if (args.length == 2) {
            final String nome = args[0];
            final String tipo = args[1];
            final Player beneficiado = Bukkit.getPlayer(nome);
            for (String type: mobs) {
            	if (tipo.equals(type)) {
            		if (beneficiado != null) {
            			final ItemStack mob = new ItemStack(Material.MOB_SPAWNER, 1);
            			ItemMeta mobMeta = mob.getItemMeta();
            			ArrayList<String> desc = new ArrayList();
		                desc.add(ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner").replaceAll("&", "§") + args[1].replaceAll("_", " "));
		                mobMeta.setLore(desc);
		                mobMeta.setDisplayName(ConfigManager.getConfig("mensagens").getString("Nome-Do-MobSpawner").replaceAll("&", "§"));
		                mob.setItemMeta(mobMeta);
		                beneficiado.getInventory().addItem(new ItemStack[] { mob });
		                sender.sendMessage(ConfigManager.getConfig("mensagens").getString("Spawner-Givado").replaceAll("&", "§").replaceAll("%tipo%", args[1].replaceAll("_", " ")));
		                return true;
            		}
            		sender.sendMessage(ConfigManager.getConfig("mensagens").getString("Player-Offline").replaceAll("&", "§"));
            		return false;
            	}
            	sender.sendMessage(ConfigManager.getConfig("mensagens").getString("Spawner-Desconhecido").replaceAll("&", "§"));
            	return false;
            }
        }
        
        if (args.length == 3) {
            int quantidade;
            try {
                quantidade = Integer.valueOf(args[2]);
            }
            catch (NumberFormatException e) {
                sender.sendMessage(ConfigManager.getConfig("mensagens").getString("Numero-Invalido").replaceAll("&", "§"));
                return true;
            }
        	final String tipo = args[1];
            final String nome = args[0];
            final Player beneficiado = Bukkit.getPlayer(nome);
            	for (String type: mobs) {
            		if (tipo.equals(type)) {
            			if (beneficiado != null) {
			                final ItemStack mob = new ItemStack(Material.MOB_SPAWNER, quantidade);
			                ItemMeta mobMeta = mob.getItemMeta();
			                ArrayList<String> desc = new ArrayList();
			                desc.add(ConfigManager.getConfig("mensagens").getString("Lore-Do-MobSpawner").replaceAll("&", "§") + args[1].replaceAll("_", " "));
			                mobMeta.setLore(desc);
			                mobMeta.setDisplayName(ConfigManager.getConfig("mensagens").getString("Nome-Do-MobSpawner").replaceAll("&", "§"));
			                mob.setItemMeta(mobMeta);
			                beneficiado.getInventory().addItem(new ItemStack[] { mob });
			                sender.sendMessage(ConfigManager.getConfig("mensagens").getString("Spawner-Givado").replaceAll("&", "§").replaceAll("%tipo%", args[1].replaceAll("_", " ")));
			                return true;
            			}
            			sender.sendMessage(ConfigManager.getConfig("mensagens").getString("Player-Offline").replaceAll("&", "§"));
            			return false;
            		}
            		sender.sendMessage(ConfigManager.getConfig("mensagens").getString("Spawner-Desconhecido").replaceAll("&", "§"));
            	}
        	}
        }
        return false;
    }
}

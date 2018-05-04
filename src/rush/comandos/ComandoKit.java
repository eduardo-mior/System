package rush.comandos;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import rush.utils.ConfigManager;
import rush.utils.DataManager;
import rush.utils.TimeFormatter;

public class ComandoKit implements Listener, CommandExecutor {
	
     @Override
	 public boolean onCommand(final CommandSender s, Command cmd, String lbl, String[] args) {
		 if (cmd.getName().equalsIgnoreCase("kit")) {
			 
			 if (!(s instanceof Player)) {
			 	 s.sendMessage(ConfigManager.getConfig("mensagens").getString("Console-Nao-Pode").replace("&", "§"));
			 	 return false;
			 } 
			 				     
			 if (args.length > 1 || args.length < 1) {
				 s.sendMessage(ConfigManager.getConfig("mensagens").getString("Kit-Comando-Incorreto").replaceAll("&", "§"));
				 return false;
			 }
			
	 		 Player p = (Player)s;
			 PlayerInventory inv = p.getInventory();
			 String kit = args[0].toLowerCase();
			 File filek = DataManager.getFile(kit, "kits");
			 File filep = DataManager.getFile(p.getName().toLowerCase(), "playerdata");
			
			 if (!filek.exists()) {
		         s.sendMessage(ConfigManager.getConfig("mensagens").getString("Kit-Nao-Existe").replaceAll("&", "§").replace("%kit%", kit));
		         ComandoKits.ListKits(s);
		         return false;
			 }
			
			 FileConfiguration configk = DataManager.getConfiguration(filek);
			 String perm = configk.getString("Permissao");
			 
			 if (!p.hasPermission(perm)) {
		         s.sendMessage(ConfigManager.getConfig("mensagens").getString("Kit-Sem-Permissao").replaceAll("&", "§").replace("%kit%", kit));
		         return false;
			 }
			 
			 FileConfiguration configp = DataManager.getConfiguration(filep);
		     Set<String> KEYS = configp.getConfigurationSection("Kits").getKeys(false);
	    	 Set<String> ITENS = configk.getConfigurationSection("Itens").getKeys(false);
		     long tempok = System.currentTimeMillis() + (configk.getInt("Delay") * 60000);
	    	 int n = ITENS.size();
		     
		     if (!KEYS.contains(kit)) {
		    	 configp.set("Kits." + kit, tempok);
		    	 for (int i=0; n > i; i++) {
		    		   ItemStack item = configk.getItemStack("Itens." + i);
		    		   inv.addItem(item);
		    	 }
		    	 try {
		    		 configp.save(filep);
		    		 s.sendMessage(ConfigManager.getConfig("mensagens").getString("Kit-Pego").replace("&", "§").replace("%kit%", kit));
		    	 } catch (IOException e) {
		    		 Bukkit.getConsoleSender().sendMessage(ConfigManager.getConfig("mensagens").getString("Falha-Ao-Salvar").replace("&", "§").replace("%arquivo%", filep.getName()));
		    	 }
		    	 return false;
		     }
		    
		     long tempop = configp.getLong("Kits." + kit);
		     if (tempop > System.currentTimeMillis() && !p.hasPermission("system.bypass.delaykit")) {
		    	 long millis = tempop - System.currentTimeMillis();
		    	 String tempo = TimeFormatter.format(millis);
				 s.sendMessage(ConfigManager.getConfig("mensagens").getString("Kit-Aguarde").replace("&", "§").replace("%kit%", kit).replace("%tempo%", tempo));
		     	 return false;
		     }
		     
		     if (tempop < System.currentTimeMillis()) {
			   	 configp.set("Kits." + kit, tempok);
				 for (int i=0; n > i; i++) {
					 ItemStack item = configk.getItemStack("Itens." + i);
				 	inv.addItem(item);
				 }
				 try {
					 configp.save(filep);
					 s.sendMessage(ConfigManager.getConfig("mensagens").getString("Kit-Pego").replace("&", "§").replace("%kit%", kit));
				 } catch (IOException e) {
					 Bukkit.getConsoleSender().sendMessage(ConfigManager.getConfig("mensagens").getString("Falha-Ao-Salvar").replace("&", "§").replace("%arquivo%", filep.getName()));
				 }
				 return false;
		     }
		 }
		 return false;
	}
}
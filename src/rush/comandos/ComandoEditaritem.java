package rush.comandos;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import rush.utils.ConfigManager;

public class ComandoEditaritem implements Listener, CommandExecutor {
	
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("editaritem")) {
			if (!(s instanceof Player)) {
				s.sendMessage(ConfigManager.getConfig("mensagens").getString("Console-Nao-Pode").replace("&", "§"));
				return false;
			}
			
			if (args.length < 1) {
				s.sendMessage(ConfigManager.getConfig("mensagens").getString("Editar-Item-Comando-Incorreto").replace("&", "§"));
				return false;
			}
			
			Player p = (Player)s;
			ItemStack item = p.getItemInHand();
			if (item == null) {
				s.sendMessage(ConfigManager.getConfig("mensagens").getString("Editar-Item-Invalido").replace("&", "§"));
				return false;
			}
			
			ItemMeta meta = item.getItemMeta();
			
			if (args[0].equalsIgnoreCase("renomear")) {
				String nome = "";
				for (int i=1; i < args.length; i++) { nome += args[i] + " "; }
				meta.setDisplayName(nome.replace("&", "§"));
				item.setItemMeta(meta);
				s.sendMessage(ConfigManager.getConfig("mensagens").getString("Editar-Item-Com-Sucesso").replace("&", "§"));
				return false;
			}
			
			if (args[0].equalsIgnoreCase("flags")) {
				meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				meta.addItemFlags(ItemFlag.HIDE_DESTROYS);
				meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
				meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
				meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
				item.setItemMeta(meta);
				s.sendMessage(ConfigManager.getConfig("mensagens").getString("Editar-Item-Com-Sucesso").replace("&", "§"));
				return false;
			}
			
			if (args[0].equalsIgnoreCase("glow")) {
				meta.addEnchant(Enchantment.LUCK, 1, true);
				meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				item.setItemMeta(meta);
				s.sendMessage(ConfigManager.getConfig("mensagens").getString("Editar-Item-Com-Sucesso").replace("&", "§"));
				return false;
			}
			
			if (args[0].equalsIgnoreCase("removelore")) {
				meta.setLore(null);
				item.setItemMeta(meta);
				s.sendMessage(ConfigManager.getConfig("mensagens").getString("Editar-Item-Com-Sucesso").replace("&", "§"));
				return false;
			}
			
			if (args[0].equalsIgnoreCase("addlore")) {
				List<String> lore = new ArrayList<>();
				if (meta.hasLore()) lore.addAll(meta.getLore());
				String novaLinha = "";
				for (int i=1; i < args.length; i++) { novaLinha += args[i] + " "; }
				lore.add(novaLinha.replace("&", "§"));
				meta.setLore(lore);
				item.setItemMeta(meta);
				s.sendMessage(ConfigManager.getConfig("mensagens").getString("Editar-Item-Com-Sucesso").replace("&", "§"));
				return false;
			}
			
			s.sendMessage(ConfigManager.getConfig("mensagens").getString("Editar-Item-Comando-Incorreto").replace("&", "§"));
		}
		return false;
	}
}
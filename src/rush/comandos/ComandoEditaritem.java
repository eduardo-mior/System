package rush.comandos;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import rush.configuracoes.Mensagens;

@SuppressWarnings("all")
public class ComandoEditaritem implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("editaritem")) {
			
			// Verificando se o sender é um player
			if (!(s instanceof Player)) {
				s.sendMessage(Mensagens.Console_Nao_Pode);
				return true;
			}
			
			// Verificando se o player digitou o número minimo de argumentos
			if (args.length < 1) {
				s.sendMessage(Mensagens.Editar_Item_Comando_Incorreto);
				return true;
			}
			
			// Pegando o player e o item que esta na sua mão
			Player p = (Player)s;
			ItemStack item = p.getInventory().getItemInHand();
			
			// Verificando se o item é valido
			if (item == null || item.getType() == Material.AIR) {
				s.sendMessage(Mensagens.Editar_Item_Invalido);
				return true;
			}
			
			// Pegando a ItemMeta do item para podermos edita-la
			ItemMeta meta = item.getItemMeta();
			
			// Verificando se o player quer renomear o item
			if (args[0].equalsIgnoreCase("renomear")) {
				String nome = "";
				for (int i = 1; i < args.length; i++) {nome += args[i] + " ";}
				meta.setDisplayName(nome.substring(0, nome.length()).replace('&', '§'));
				item.setItemMeta(meta);
				s.sendMessage(Mensagens.Editar_Item_Com_Sucesso);
				return true;
			}
			
			// Verificando se o player quer adicionar flags no item
			if (args[0].equalsIgnoreCase("addflags")) {
				meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				meta.addItemFlags(ItemFlag.HIDE_DESTROYS);
				meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
				meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
				meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
				item.setItemMeta(meta);
				s.sendMessage(Mensagens.Editar_Item_Com_Sucesso);
				return true;
			}
			
			// Verificando se o player quer remover flags no item
			if (args[0].equalsIgnoreCase("removeflags")) {
				meta.removeItemFlags(ItemFlag.HIDE_ATTRIBUTES);
				meta.removeItemFlags(ItemFlag.HIDE_DESTROYS);
				meta.removeItemFlags(ItemFlag.HIDE_ENCHANTS);
				meta.removeItemFlags(ItemFlag.HIDE_PLACED_ON);
				meta.removeItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
				meta.removeItemFlags(ItemFlag.HIDE_UNBREAKABLE);
				item.setItemMeta(meta);
				s.sendMessage(Mensagens.Editar_Item_Com_Sucesso);
				return true;
			}
			
			// Verificando se o player quer adiconar glow no item
			if (args[0].equalsIgnoreCase("glow")) {
				meta.addEnchant(Enchantment.DURABILITY, 1, true);
				meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				item.setItemMeta(meta);
				s.sendMessage(Mensagens.Editar_Item_Com_Sucesso);
				return true;
			}
			
			// Verificando se o player quer remover a lore
			if (args[0].equalsIgnoreCase("removelore")) {
				meta.setLore(null);
				item.setItemMeta(meta);
				s.sendMessage(Mensagens.Editar_Item_Com_Sucesso);
				return true;
			}
			
			// Verificando se o player quer adicionar uma lore
			if (args[0].equalsIgnoreCase("addlore")) {
				List<String> lore = new ArrayList<>();
				if (meta.hasLore()) lore.addAll(meta.getLore());
				String novaLinha = "";
				for (int i = 1; i < args.length; i++) {novaLinha  += args[i] + " ";}
				lore.add(novaLinha.replace('&', '§'));
				meta.setLore(lore);
				item.setItemMeta(meta);
				s.sendMessage(Mensagens.Editar_Item_Com_Sucesso);
				return true;
			}
			
			// Verificando se o player quer adiconar 'bugar' o item
			if (args[0].equalsIgnoreCase("bugar")) {
				item.setDurability(Short.MAX_VALUE);
				s.sendMessage(Mensagens.Editar_Item_Com_Sucesso);
				return true;
			}
			
			// Verificando se o player que deixar o item negativo
			if (args[0].equalsIgnoreCase("negativo")) {
				item.setAmount(Short.MAX_VALUE);
				s.sendMessage(Mensagens.Editar_Item_Com_Sucesso);
				return true;
			}
			
			// Caso nenhuma das opção acima for aceita sera dado como comando incorreto
			s.sendMessage(Mensagens.Editar_Item_Comando_Incorreto);
			return true;
		}
		return false;
	}
}
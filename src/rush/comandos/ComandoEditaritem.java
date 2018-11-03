package rush.comandos;

import java.util.ArrayList;
import java.util.Arrays;
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

import rush.apis.ItemAPI;
import rush.configuracoes.Mensagens;

public class ComandoEditaritem implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
			
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
		ItemStack item = p.getItemInHand();
		
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
			meta.setDisplayName(nome.replace('&', '§').trim());
			item.setItemMeta(meta);
			s.sendMessage(Mensagens.Editar_Item_Com_Sucesso);
			return true;
		}
			
		// Verificando se o player quer adicionar flags no item
		if (args[0].equalsIgnoreCase("addflags")) {
			meta.addItemFlags(ItemFlag.values());
			item.setItemMeta(meta);
			s.sendMessage(Mensagens.Editar_Item_Com_Sucesso);
			return true;
		}
			
		// Verificando se o player quer remover flags no item
		if (args[0].equalsIgnoreCase("removeflags")) {
			meta.removeItemFlags(ItemFlag.values());
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
		
		// Verificando se o player quer deixar o item inquebravel
		if (args[0].equalsIgnoreCase("inquebravel")) {
			// Setando o item como inquebravel e setando o item na mão do player
			p.setItemInHand(ItemAPI.setUnbreakable(item, true));
			s.sendMessage(Mensagens.Editar_Item_Com_Sucesso);
			return true;
		}
		
		// Verificando se o player que adicionar algum atributo ao item
		if (args[0].equalsIgnoreCase("atributo")) {
			
			// Pegando o atributo que o player quer setar
			double value;
			int operation;
			String attribute;
			try {
				attribute = Attribute.valueOf(args[1].toUpperCase()).getAttribute();
				value = Double.parseDouble(args[2]);
				operation = Integer.parseInt(args[3]);
			} catch (NumberFormatException e) {
				s.sendMessage(Mensagens.Numero_Invalido.replace("%numero%", e.getMessage().split("\"")[1]));
				return true;
			} catch (ArrayIndexOutOfBoundsException e) {
				s.sendMessage(Mensagens.Editar_Item_Comando_Incorreto);
				return true;
			} catch (IllegalArgumentException e) {
				s.sendMessage(Mensagens.Editar_Item_Atributo_Invalido.replace("%lista%", getEnumList(Attribute.class).toString()));
				return true;
			}			
			
			// Verificando se a operação é uma operação valida
			if (operation < 0 || operation > 2) {
				s.sendMessage(Mensagens.Editar_Item_Numero_Operacao_Invalido);
				return true;
			}
			
			// Setando o atributo no item e setando o item na mão do player
			p.setItemInHand(ItemAPI.setAttributeNBT(item, attribute, value, operation));
			s.sendMessage(Mensagens.Editar_Item_Com_Sucesso);
			return true;
		}
		
		// Caso nenhuma das opção acima for aceita sera dado como comando incorreto
		s.sendMessage(Mensagens.Editar_Item_Comando_Incorreto);
		return true;
	}
	
	private <E extends Enum<E>> List<E> getEnumList(Class<E> enumClass) {
		return new ArrayList<E>(Arrays.asList(enumClass.getEnumConstants()));
	}
	
}

enum Attribute {
	
	DAMAGE("generic.attackDamage"),
	KNOCKBACKRESISTANCE("generic.knockbackResistance"),
	FOLLOWRANGE("generic.followRange"),
	MAXHEALTH("generic.maxHealth"),
	SPEED("generic.movementSpeed");
	
	private String attribute;
	
	Attribute(String attribute) {
		this.attribute = attribute;
	}
	
	public String getAttribute() {
		return this.attribute;
	}
}
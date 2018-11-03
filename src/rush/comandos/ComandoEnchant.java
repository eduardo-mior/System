package rush.comandos;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import rush.configuracoes.Mensagens;

public class ComandoEnchant implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {

		// Verificando se o sender é um player
		if (!(s instanceof Player)) {
			s.sendMessage(Mensagens.Console_Nao_Pode);
			return true;
		}

		// Se o sender for o console ele precisa especificar um player
		if (args.length != 2) {
			s.sendMessage(Mensagens.Enchant_Comando_Incorreto);
			return true;
		}
			
		// Pegando o encantamento e verificando se é 1 encantamento valido
		String en = args[0].toUpperCase();
		Enchantment ench = getEnchantment(en);
		if (ench == null) {
			s.sendMessage(Mensagens.Enchant_Encantamento_Invalido.replace("%enchant%", en).replace("%lista%", getEnchants()));
			return true;
		}
			
		// Pegando o level e verificando se é 1 level valido
		int level;
		try {
			level = Integer.parseInt(args[1]);
		} catch (NumberFormatException e) {
			s.sendMessage(Mensagens.Numero_Invalido.replace("%numero%", e.getMessage().split("\"")[1]));
			return true;
		}
			
		// Pegando o player o item na sua mão e verificando se é valido
		Player p = (Player) s;
		ItemStack hand = p.getItemInHand();
		if (hand == null || hand.getType() == Material.AIR) {
			s.sendMessage(Mensagens.Enchant_Item_Invalido);
			return true;
		}
			
		// Adicionando o encantamento no item
		hand.addUnsafeEnchantment(ench, level);
		s.sendMessage(Mensagens.Enchant_Encantado_Com_Sucesso);
		return true;
	}
	
	// Método para pegar o encantamento
	private Enchantment getEnchantment(String enchant) {
		try {
			return Enchantment.getByName(enchant);
		} catch (Exception e) {
			return null;
		}
	}
	
	// Método para pegar a lista de encantamentos 
	private String getEnchants() {
		List<String> enchants = new ArrayList<>();
		for (Enchantment e : Enchantment.values()) {
			if (e != null) enchants.add(e.getName());
		}
		return enchants.toString();
	}
}
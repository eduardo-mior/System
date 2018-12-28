package rush.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import rush.configuracoes.Mensagens;
import rush.entidades.Kit;
import rush.entidades.Kits;

public class ComandoVerkit implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {

		// Verificando se o player digitou o número de argumentos correto
		if (args.length != 1) {
			s.sendMessage(Mensagens.VerKit_Comando_Incorreto);
			return true;
		}

		// Pegando o argumento e verificando se o kit existe
		String nome = args[0].toLowerCase();
		if (!Kits.contains(nome)) {
			s.sendMessage(Mensagens.Kit_Nao_Existe.replace("%kit-id%", nome));
			ComandoKits.ListKits(s);
			return true;
		}

		// Pegando o kit e a config do Kit
		Kit kit = Kits.get(nome);

		// Verificando se o sender não é o console
		if (!(s instanceof Player)) {
			s.sendMessage(Mensagens.Console_Nao_Pode);
			return true;
		}

		// Pegando o player e abrindo um inventarios com os itens, o resto é feito pela classe KitsListener
		Player p = (Player) s;
		Inventory inv = Bukkit.getServer().createInventory(p, 36, "Visualizando Kit§f§o §1");
		for (ItemStack item : kit.getItens()) {
			if (item != null) inv.addItem(item);
		}
		p.openInventory(inv);
		return true;
	}
}
package rush.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import rush.configuracoes.Mensagens;
import rush.entidades.Kits;

public class ComandoCriarkit implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("criarkit")) {
			
			// Verificando se o sender é um player
			if (!(s instanceof Player)) {
				s.sendMessage(Mensagens.Console_Nao_Pode); 
			    return false;
			}
			
			// Verificando se o player digitou o número de argumentos corretos
			if (args.length != 1) {
		        s.sendMessage(Mensagens.CriarKit_Comando_Incorreto);
		        return false;
			}
			 
			// Pegando o argumento e verificando se o kit já existe
			String kit = args[0].toLowerCase();
			if (Kits.contains(kit)) {
		        s.sendMessage(Mensagens.Kit_Ja_Existe.replace("%kit%", kit));
		        return false;
			}
			
			// Pegando o player abrindo um inventario... o resto do processo é feito pela classe KitsListener
			Player p = (Player)s;
			Inventory inv = Bukkit.getServer().createInventory(p, 36, "§0Criar Kit §n" + kit);
	        p.openInventory(inv);
		}
		return false;
	}
}
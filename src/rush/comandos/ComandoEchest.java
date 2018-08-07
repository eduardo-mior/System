package rush.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import rush.configuracoes.Mensagens;

public class ComandoEchest implements CommandExecutor {

	@SuppressWarnings("deprecation")	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("echest")) {
			
			// Verificando se o sender é um player
			if (!(s instanceof Player)) {
				s.sendMessage(Mensagens.Console_Nao_Pode);
				return false;
			}
			 
			// Pegando o player
			Player sender = (Player)s;
			 
			// Verificando se o player quer abrir o inventario de outra pessoa e possui permissão
			if (args.length != 0 && (s.hasPermission("system.echest.mod") || s.hasPermission("system.echest.admin"))) {
				 	
				// Pegando o player e verificando se ele esta online
				Player target = Bukkit.getPlayer(args[0]);
				if (target == null) {
					s.sendMessage(Mensagens.Player_Offline);
					return false;
				}
				 
				// Pegando o enderchest do player e abrindo
				Inventory i = target.getEnderChest();
				sender.openInventory(i);
				return false;
			}
				 
			// Caso a verificação acima não for valida o enderchest do player sera aberto
			Inventory i = sender.getEnderChest();
			sender.openInventory(i);
		}
		return false;
	}
}
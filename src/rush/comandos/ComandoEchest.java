package rush.comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import rush.apis.OfflinePlayerAPI;
import rush.configuracoes.Mensagens;
import rush.sistemas.comandos.EnderChestListener;

@SuppressWarnings("all")
public class ComandoEchest implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {

		// Verificando se o sender é um player
		if (!(s instanceof Player)) {
			s.sendMessage(Mensagens.Console_Nao_Pode);
			return true;
		}

		// Pegando o player
		Player sender = (Player) s;

		// Verificando se o player quer abrir o inventario de outra pessoa e possui permissão
		if (args.length != 0 && (s.hasPermission("system.echest.mod") || s.hasPermission("system.echest.admin"))) {
			
			// Pegando o player e verificando se o ele esta online, caso ele não estiver tentamos pegar o offline
			Player target = OfflinePlayerAPI.getPlayer(args[0]);
			if (target == null) {
				s.sendMessage(Mensagens.Player_Offline);
				return true;
			}

			// Pegando o enderchest do player e abrindo
			EnderChestListener.echestlist.put(sender.getName(), target.getName());
			Inventory i = target.getEnderChest();
			sender.openInventory(i);
			return true;
		}

		// Caso a verificação acima não for valida o enderchest do player sera aberto
		Inventory i = sender.getEnderChest();
		sender.openInventory(i);
		return true;
	}
}
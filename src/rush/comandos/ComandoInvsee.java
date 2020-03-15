package rush.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rush.Main;
import rush.apis.OfflinePlayerAPI;
import rush.configuracoes.Mensagens;
import rush.enums.Version;
import rush.sistemas.comandos.InvseeListener;

@SuppressWarnings("all")
public class ComandoInvsee implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {

		// Verificando se o sender é um player
		if (!(s instanceof Player)) {
			s.sendMessage(Mensagens.Console_Nao_Pode);
			return true;
		}

		// Verificando se o player digitou o número de argumentos corretos
		if (args.length != 1) {
			s.sendMessage(Mensagens.Invsee_Comando_Incorreto);
			return true;
		}
		
		// Pegando o player e verificando se o ele esta online, caso ele não estiver tentamos pegar o offline
		Player target = OfflinePlayerAPI.getPlayer(args[0]);
		if (target == null) {
			s.sendMessage(Mensagens.Player_Offline);
			return true;
		}
		
		// Pegando o player e, verificando se o target e o sender são a mesma pessoa
		Player sender = (Player) s;
		if (target.getName().equals(s.getName())) {
			s.sendMessage(Mensagens.Invsee_Erro_Voce_Mesmo);
			return true;
		}
		
		// Abrindo o inventario e avisando
		InvseeListener.invseelist.put(sender.getName(), target.getName());
		sender.openInventory(target.getInventory());
		sender.sendMessage(Mensagens.Invsee_Abrindo_Inventario.replaceAll("%player%", target.getName()));
		return true;
	}
}
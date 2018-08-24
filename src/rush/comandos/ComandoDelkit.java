package rush.comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import rush.configuracoes.Mensagens;
import rush.entidades.Kits;

public class ComandoDelkit implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {

		// Verificando se o player digitou o número de argumentos corretos
		if (args.length != 1) {
			s.sendMessage(Mensagens.DelKit_Comando_Incorreto);
			return true;
		}

		// Pegando o argumento e verificando se o kit existe
		String kit = args[0].toLowerCase();
		if (!Kits.contains(kit)) {
			s.sendMessage(Mensagens.Kit_Nao_Existe.replace("%kit%", kit));
			ComandoKits.ListKitsForStaff(s);
			return true;
		}

		// Deletando o kit
		Kits.delete(kit);
		s.sendMessage(Mensagens.Kit_Deletado.replace("%kit%", kit));
		return true;
	}
}

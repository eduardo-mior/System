package rush.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rush.apis.TitleAPI;
import rush.configuracoes.Mensagens;

public class ComandoTitle implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {

		// Verificando se o player digitou o número de argumentos corretos
		if (args.length < 1) {
			s.sendMessage(Mensagens.Title_Comando_Incorreto);
			return true;
		}

		// Pegando a mensagem do title
		String msg = "";
		for (String str : args) {msg += str.replace('&', '§') + " ";}

		// Divindo a mensagem em title e subtitle
		String[] txt = msg.split("<nl>");

		// Caso a mensagem não contenha "<nl>" então apenas o title é enviado
		if (!(msg.contains("<nl>"))) {
			// Enviando o title para todos os players do serivdor
			for (Player p : Bukkit.getOnlinePlayers()) {
				TitleAPI.sendTitle(p, 20, 60, 20, msg, "");
			}

		// Caso a mensagem conter "<nl>" então o title e o subtitle é enviado
		} else {
			// Enviando o title para todos os players do serivdor
			for (Player p : Bukkit.getOnlinePlayers()) {
				TitleAPI.sendTitle(p, 20, 60, 20, txt[0], txt[1]);
			}
		}

		// Informando que o title foi enviado
		s.sendMessage(Mensagens.Title_Enviado);
		return true;
	}
}

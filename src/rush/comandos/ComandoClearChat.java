package rush.comandos;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import rush.configuracoes.Mensagens;

public class ComandoClearChat implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("clearchat")) {

			// Utilizando a classe StringUtils nós conseguimos duplicar a mensagem 100x facilmente
			String limparchat = StringUtils.repeat(" §c \n §c ", 100);
			Bukkit.broadcastMessage(limparchat);

			// Verificando se é necessario avisar que o chat foi limpo
			if (Mensagens.Avisar_Que_O_Chat_Foi_Limpo) {
				Bukkit.broadcastMessage(Mensagens.Aviso_Que_O_Chat_Limpo_Global.replace("%player%", s.getName()));
			}
			return true;
		}
		return false;
	}
}

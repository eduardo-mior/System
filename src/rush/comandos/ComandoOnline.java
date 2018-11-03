package rush.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import rush.configuracoes.Mensagens;

public class ComandoOnline implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		s.sendMessage(Mensagens.Players_Online.replace("%online%", String.valueOf(Bukkit.getOnlinePlayers().size())));
		return true;
	}
}
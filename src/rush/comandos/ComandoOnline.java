package rush.comandos;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rush.configuracoes.Mensagens;

@SuppressWarnings("unused")
public class ComandoOnline implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("online")) {
			s.sendMessage(Mensagens.Players_Online.replace("%online%", String.valueOf(Bukkit.getOnlinePlayers().size())));
			return true;
		}
		return false;
	}
}
package rush.comandos;

import java.io.File;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import rush.configuracoes.Mensagens;
import rush.utils.DataManager;

public class ComandoDelwarp implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("delwarp")) {

			// Verificando se o player digitou o número de argumentos corretos
			if (args.length != 1) {
				s.sendMessage(Mensagens.DelWarp_Comando_Incorreto);
				return false;
			}

			// Pegando o argumento e criando/pegando o arquivo (File)
			String warp = args[0];
			File file = DataManager.getFile(warp, "warps");

			// Verificando se o file(warp) existe
			if (!file.exists()) {
				s.sendMessage(Mensagens.Warp_Nao_Existe.replace("%warp%", warp));
				ComandoWarps.ListWarps(s);
				return false;
			}

			// Deletando a warp
			DataManager.deleteFile(file);
			s.sendMessage(Mensagens.Warp_Deletada.replace("%warp%", warp));
		}
		return false;
	}
}

package rush.comandos;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rush.Main;
import rush.apis.OnlinePlayersAPI;
import rush.configuracoes.Mensagens;

@SuppressWarnings("all")
public class ComandoTpall implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		
		// Verificando se o player digitou o número de argumentos corretos
		if (args.length != 1 && args.length != 4) {
			s.sendMessage(Mensagens.Tpall_Comando_Incorreto);
			return true;
		}
		
		// Se os argumentos forem 1 então o sender quer teleportar todos até um player
		if (args.length == 1) {
			
			// Pegando o player e verificando se ele esta online
			Player p = Bukkit.getPlayer(args[0]);
			if (p == null) {
				s.sendMessage(Mensagens.Player_Offline);
				return true;
			}

			// Teleportando todos os players e informando de acordo com a versão do minecraft	
			Player[] players = OnlinePlayersAPI.getOnlinePlayers();
			for (Player target : players) {
				target.teleport(p);
				target.sendMessage(Mensagens.Tphere_Puxado_Com_Sucesso.replace("%player%", p.getName()));
			}
			
			
			s.sendMessage(Mensagens.Tpall_Puxou_Com_Sucesso_Player.replace("%player%", p.getName()));
			return true;
		}
		
		// Se os argumentos foram 4 então o sender quer teleportar todos até uma cordenada
		if (args.length == 4) {

			// Verificando se o mundo é 1 mundo valido
			World w = Bukkit.getWorld(args[0]);
			if (w == null) {
				s.sendMessage(Mensagens.Mundo_Nao_Existe.replace("%mundo%", args[0]));
				return true;
			}

			// Verificando se os números digitados são validos
			double x, y, z;
			try {
				x = Double.parseDouble(args[1]);
				y = Double.parseDouble(args[2]);
				z = Double.parseDouble(args[3]);
			} catch (NumberFormatException e) {
				s.sendMessage(Mensagens.Numero_Invalido.replace("%numero%", e.getMessage().split("\"")[1]));
				return true;
			}

			// Teleportando todos os players e informando de acordo com sua versão do minecrat
			Location l = new Location(w, x, y, z);
			Player[] players = OnlinePlayersAPI.getOnlinePlayers();
			for (Player p : players) {
				p.teleport(l);
			}
			
			
			s.sendMessage(Mensagens.Tpall_Puxou_Com_Sucesso_Cords
					.replace("<world>", args[0])
					.replace("<x>", args[1])
					.replace("<y>", args[2])
					.replace("<z>", args[3]));
			return true;
		}
		return true;
	}
}
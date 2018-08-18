package rush.comandos;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rush.configuracoes.Mensagens;

@SuppressWarnings("all")
public class ComandoTp implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("tp")) {

			// Verificando se o player digitou o número de argumentos corretos
			if (args.length < 1 || args.length > 5) {
				s.sendMessage(Mensagens.Tp_Comando_Incorreto);
				return true;
			}

			// Se os argumentos forem 1 então o sender quer se teleportar para o player
			if (args.length == 1) {

				// Verificando se o sender é um player
				if (!(s instanceof Player)) {
					s.sendMessage(Mensagens.Console_Nao_Pode);
					return true;
				}

				// Pegando o player e verificando se ele esta online
				Player target = Bukkit.getPlayer(args[0]);
				if (target == null) {
					s.sendMessage(Mensagens.Player_Offline);
					return true;
				}
				
				if (target.getName().equals(s.getName())) {
					s.sendMessage(Mensagens.Tp_Erro_Voce_Mesmo);
					return true;
				}

				// Teleportando o player até o alvo e informando
				Player p = (Player) s;
				p.teleport(target);
				p.sendMessage(Mensagens.Tp_Teleportado_Com_Sucesso_Player.replace("%player%", args[0]));
				return true;
			}
			
			// Se os argumentos foram 2 então o sender quer teleportar um player até outro
			if (args.length == 2) {

				// Verificando se o sender o player e o alvo são os mesmos
				if (s.getName().equals(args[0]) && s.getName().equals(args[1])) {
					s.sendMessage(Mensagens.Tp_Erro_Voce_Mesmo);
					return true;
				}
				
				// Pegando o player 1 e verificando se ele esta online
				Player player = Bukkit.getPlayer(args[0]);
				if (player == null) {
					s.sendMessage(Mensagens.Player_Offline);
					return true;
				}

				// Pegando o player 2 e verificando se ele esta online
				Player target = Bukkit.getPlayer(args[1]);
				if (target == null) {
					s.sendMessage(Mensagens.Player_Offline);
					return true;
				}
				
				// Verificando se o player e o alvo são os mesmos
				if (args[0].equals(args[1])) {
					s.sendMessage(Mensagens.Tp_Erro_Player_Mesmo);
					return true;
				}

				// Teleportando o player até o alvo e informando
				target.teleport(player);
				target.sendMessage(Mensagens.Tphere_Puxado_Com_Sucesso.replace("%player%", args[0]));
				s.sendMessage(Mensagens.Tp_Voce_Teleportou_Player_Ate_Player.replace("%player%", args[1]).replace("%alvo%", args[0]));
				return true;
			}

			// Se os argumentos foram 3 não existe uma possibidade então é dado comando invalido
			if (args.length == 3) {
				s.sendMessage(Mensagens.Tp_Comando_Incorreto);
				return true;
			}
			
			// Se os argumentos foram 4 então o sender quer se teleportar até uma cordenada
			if (args.length == 4) {

				// Verificando se o sender é um player
				if (!(s instanceof Player)) {
					s.sendMessage(Mensagens.Console_Nao_Pode);
					return true;
				}

				// Verificando se o mundo é 1 mundo valido
				World w = Bukkit.getWorld(args[0]);
				if (w == null) {
					s.sendMessage(Mensagens.Mundo_Nao_Existe.replace("%mundo%", args[1]));
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

				// Teleportando o player até o alvo e informando
				Player p = (Player) s;
				Location l = new Location(w, x, y, z);
				p.teleport(l);
				p.sendMessage(Mensagens.Tp_Teleportado_Com_Sucesso_Cords
						.replace("<world>", args[0])
						.replace("<x>", args[1])
						.replace("<y>", args[2])
						.replace("<z>", args[3]));
				return true;
			}
			

			// Se os argumentos foram 5 então o sender quer se teleportar até uma cordenada
			if (args.length == 5) {
				// Verificando se o mundo é 1 mundo valido
				World w = Bukkit.getWorld(args[0]);
				if (w == null) {
					s.sendMessage(Mensagens.Mundo_Nao_Existe.replace("%mundo%", args[0]));
					return true;
				}

				// Verificando se os números digitados são validos
				double x = 0, y = 0, z = 0;
				try {
					x = Double.parseDouble(args[1]);
					y = Double.parseDouble(args[2]);
					z = Double.parseDouble(args[3]);
				} catch (NumberFormatException e) {
	                s.sendMessage(Mensagens.Numero_Invalido.replace("%numero%", e.getMessage().split("\"")[1]));
					return true;
				}
				
				// Pegando o player 1 e verificando se ele esta online
				Player p = Bukkit.getPlayer(args[4]);
				if (p == null) {
					s.sendMessage(Mensagens.Player_Offline);
					return true;
				}	

				// Teleportando o player até o alvo e informando
				Location l = new Location(w, x, y, z);
				p.teleport(l);
				s.sendMessage(Mensagens.Tp_Voce_Teleportou_Player_Ate_Cords
						.replace("%player%", args[4])
						.replace("<world>", args[0])
						.replace("<x>", args[1])
						.replace("<y>", args[2])
						.replace("<z>", args[3]));
				return true;
			}
		}
		return false;
	}
}
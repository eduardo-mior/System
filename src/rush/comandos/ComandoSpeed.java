package rush.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rush.configuracoes.Mensagens;

@SuppressWarnings("all")
public class ComandoSpeed implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {

		// Verificando se o sender é um player
		if (!(s instanceof Player)) {

			// Verificando se o sender digitou o número de argumentos correto
			if (args.length != 2) {
				s.sendMessage(Mensagens.Speed_Comando_Incorreto);
				return true;
			}
			
			// Pegando o player e verificando se ele esta online
			Player p = Bukkit.getPlayer(args[1]);
			if (p == null) {
				s.sendMessage(Mensagens.Player_Offline);
				return true;
			}
			
			// Verificando se o player não quer resetar o fly
			if (args[0].equalsIgnoreCase("clear") || args[0].equalsIgnoreCase("reset")) {
				p.setFlySpeed(0.1f);
				p.setWalkSpeed(0.2f);
				s.sendMessage(Mensagens.Speed_Alterado_Outro.replace("%speed%", "PADRAO").replace("%player%", p.getName()));
				return true;
			}
			
			// Verificando se o número é um número valido
			float speed;
			try {
				speed = Float.parseFloat(args[0]);
			} catch (NumberFormatException e) {
				s.sendMessage(Mensagens.Numero_Invalido.replace("%numero%", e.getMessage().split("\"")[1]));
				return true;
			}

			// Verificando se a velocidade é valida (necessario bukkit)
			if (speed > 1.0f || speed < -1.0f) {
				s.sendMessage(Mensagens.Speed_Valor_Invalido);
				return true;
			}
			
			// Setando o speed no player e informando
			p.setFlySpeed(speed);
			p.setWalkSpeed(speed);
			s.sendMessage(Mensagens.Speed_Alterado_Outro.replace("%speed%", args[0]).replace("%player%", p.getName()));
			return true;
		}

		// Verificando se o player digitou o número de argumentos correto
		if (args.length < 1 || args.length > 2) {
			s.sendMessage(Mensagens.Speed_Comando_Incorreto);
			return true;
		}
		
		if (args.length == 1) {
			
			// Verificando se o player não quer resetar o fly
			Player p = (Player) s;
			if (args[0].equalsIgnoreCase("clear") || args[0].equalsIgnoreCase("reset")) {
				p.setFlySpeed(0.1f);
				p.setWalkSpeed(0.2f);
				s.sendMessage(Mensagens.Speed_Alterado_Voce.replace("%speed%", "PADRAO").replace("%player%", p.getName()));
				return true;
			}
			
			// Pegando o player e verificando se o número é um número valido
			float speed;
			try {
				speed = Float.parseFloat(args[0]);
			} catch (NumberFormatException e) {
				s.sendMessage(Mensagens.Numero_Invalido.replace("%numero%", e.getMessage().split("\"")[1]));
				return true;
			}

			// Verificando se a velocidade é valida (necessario bukkit)
			if (speed > 1.0f || speed < -1.0f) {
				s.sendMessage(Mensagens.Speed_Valor_Invalido);
				return true;
			}
			
			// Setando o speed no player e informando
			p.setFlySpeed(speed);
			p.setWalkSpeed(speed);
			s.sendMessage(Mensagens.Speed_Alterado_Voce.replace("%speed%", args[0]));
			return true;
		}

		// Se o número de argumentos é 0 então a velocidade do sender é alterada
		if (args.length == 2) {

			// Verificando se o sender tem permisssão
			if (!s.hasPermission("system.speed.outros")) {
				s.sendMessage(Mensagens.Speed_Outro_Sem_Permissao);
				return true;
			}
			
			// Pegando o player e verificando se ele esta online
			Player p = Bukkit.getPlayer(args[1]);
			if (p == null) {
				s.sendMessage(Mensagens.Player_Offline);
				return true;
			}
			
			// Verificando se o player não quer resetar o fly
			if (args[0].equalsIgnoreCase("clear") || args[0].equalsIgnoreCase("reset")) {
				p.setFlySpeed(0.1f);
				p.setWalkSpeed(0.2f);
				s.sendMessage(Mensagens.Speed_Alterado_Outro.replace("%speed%", "PADRAO").replace("%player%", p.getName()));
				return true;
			}

			// Verificando se o número é um número valido
			float speed;
			try {
				speed = Float.parseFloat(args[0]);
			} catch (NumberFormatException e) {
				s.sendMessage(Mensagens.Numero_Invalido.replace("%numero%", e.getMessage().split("\"")[1]));
				return true;
			}

			// Verificando se a velocidade é valida (necessario bukkit)
			if (speed > 1.0f || speed < -1.0f) {
				s.sendMessage(Mensagens.Speed_Valor_Invalido);
				return true;
			}
			
			// Setando o speed no player e informando
			p.setFlySpeed(speed);
			p.setWalkSpeed(speed);
			s.sendMessage(Mensagens.Speed_Alterado_Outro.replace("%speed%", args[0]).replace("%player%", p.getName()));
			return true;
		}
		return true;
	}
}
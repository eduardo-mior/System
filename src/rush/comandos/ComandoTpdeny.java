package rush.comandos;

import java.util.LinkedHashSet;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rush.configuracoes.Mensagens;
import rush.entidades.Tpa;

@SuppressWarnings("all")
public class ComandoTpdeny extends Tpa implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {

		// Verificando se o player digitou o número de argumentos corretos
		if (args.length > 1) {
			s.sendMessage(Mensagens.Tpdeny_Comando_Incorreto);
			return true;
		}

		// Verificando se o player recebeu algum tpa para poder aceita-lo
		if (!TP_RECEBIDOS.containsKey(s.getName())) {
			s.sendMessage(Mensagens.Tpa_Pendente_Nao_Possui);
			return true;
		}

		// Pegando a lista de tpas que o player recebeu
		LinkedHashSet<String> tpas = TP_RECEBIDOS.get(s.getName());

		// Verificando se o player possui tpas para aceita-los
		if (tpas.size() == 0) {
			s.sendMessage(Mensagens.Tpa_Pendente_Nao_Possui);
			return true;
		}

		// Caso o player não informe argumentos então significa que ele quer aceitar o ultimo TPA recebido
		if (args.length == 0) {

			// Pegando o ultimo player que enviou TPA
			String ultimoTpa = "";
			for (String player : tpas) {ultimoTpa = player;}

			// Removendo o TPA da HashMap
			TP_ENVIADOS.get(ultimoTpa).remove(s.getName());
			TP_RECEBIDOS.get(s.getName()).remove(ultimoTpa);

			// Informando o sender e informando o player que solicitou o tpa
			Player p = Bukkit.getPlayer(ultimoTpa);
			s.sendMessage(Mensagens.Tpdeny_Recusado_Com_Sucesso.replace("%player%", ultimoTpa));
			if (p != null) p.sendMessage(Mensagens.Tpdeny_Recusou_Seu_Pedido.replace("%player%", s.getName()));
			return true;
		}

		// Caso o player informe um argumento então significa que ele quer negar um TPA
		// especifico
		if (args.length == 1) {

			// Verificando se o sender e o player target são a mesma pessoa
			if (s.getName().equals(args[0])) {
				s.sendMessage(Mensagens.Tpdeny_Erro_Voce_Mesmo);
				return true;
			}

			// Verificando se o player informado realmente enviou TPA para o sender
			if (!tpas.contains(args[0])) {
				s.sendMessage(Mensagens.Tpa_Pendente_Player_Nao_Possui.replace("%player%", args[0]));
				return true;
			}

			// Removendo o TPA da HashMap
			TP_ENVIADOS.get(args[0]).remove(s.getName());
			TP_RECEBIDOS.get(s.getName()).remove(args[0]);

			// Informando o sender e informando o player que solicitou o tpa
			Player p = Bukkit.getPlayer(args[0]);
			s.sendMessage(Mensagens.Tpdeny_Recusado_Com_Sucesso.replace("%player%", args[0]));
			if (p != null) p.sendMessage(Mensagens.Tpdeny_Recusou_Seu_Pedido.replace("%player%", s.getName()));
			return true;
		}
		return true;
	}
}
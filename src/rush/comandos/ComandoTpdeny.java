package rush.comandos;

import java.util.LinkedHashSet;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rush.configuracoes.Mensagens;
import rush.entidades.Tpa;

public class ComandoTpdeny extends Tpa implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("tpdeny")) {

			// Verificando se o player digitou o número de argumentos corretos
			if (args.length > 1) {
				s.sendMessage(Mensagens.Tpdeny_Comando_Incorreto);
				return false;
			}
			
			// Verificando se o player recebeu algum tpa para poder aceita-lo
			if (!TPs_recebidos.containsKey(s.getName())) {
				s.sendMessage(Mensagens.Tpa_Pendente_Nao_Possui);
				return false;
			}
			
			// Pegando a lista de tpas que o player recebeu
			LinkedHashSet<String> tpas = TPs_recebidos.get(s.getName());
		
			// Verificando se o player possui tpas para aceita-los
			if (tpas.size() == 0) {
				s.sendMessage(Mensagens.Tpa_Pendente_Nao_Possui);
				return false;
			}
			
			// Caso o player não informe argumentos então significa que ele quer aceitar o ultimo TPA recebido
			if (args.length == 0) {
				
				// Pegando o ultimo player que enviou TPA
				String ultimoTpa = "";
				for (String player : tpas) { ultimoTpa = player; }
				
				// Removendo o TPA da HashMap
				TPs_enviados.get(ultimoTpa).remove(s.getName());
				TPs_recebidos.get(s.getName()).remove(ultimoTpa);
				
				// Informando o sender e informando o player que solicitou o tpa
				Player p = Bukkit.getPlayer(ultimoTpa);
				s.sendMessage(Mensagens.Tpdeny_Recusado_Com_Sucesso.replace("%player%", ultimoTpa));
				if (p != null) p.sendMessage(Mensagens.Tpdeny_Recusou_Seu_Pedido.replace("%player%", s.getName()));
			}
			
			// Caso o player informe um argumento então significa que ele quer negar um TPA especifico
			if (args.length == 1) {
				
				// Verificando se o sender e o player target são a mesma pessoa
				if (s.getName().equals(args[0])) {
					s.sendMessage(Mensagens.Tpdeny_Erro_Voce_Mesmo);
					return false;
				}
					
				// Verificando se o player informado realmente enviou TPA para o sender
				if (!tpas.contains(args[0])) {
					s.sendMessage(Mensagens.Tpa_Pendente_Player_Nao_Possui.replace("%player%", args[0]));
					return false;
				}
				
				// Removendo o TPA da HashMap
				TPs_enviados.get(args[0]).remove(s.getName());
				TPs_recebidos.get(s.getName()).remove(args[0]);
				
				// Informando o sender e informando o player que solicitou o tpa
				Player p = Bukkit.getPlayer(args[0]);
				s.sendMessage(Mensagens.Tpdeny_Recusado_Com_Sucesso.replace("%player%", args[0]));
				if (p != null) p.sendMessage(Mensagens.Tpdeny_Recusou_Seu_Pedido.replace("%player%", s.getName()));
				return false;
			}
		}
		return false;
	}
}
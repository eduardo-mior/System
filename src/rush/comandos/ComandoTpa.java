package rush.comandos;

import java.util.LinkedHashSet;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import rush.Main;
import rush.configuracoes.Mensagens;
import rush.configuracoes.Settings;
import rush.entidades.Tpa;

@SuppressWarnings("all")
public class ComandoTpa extends Tpa implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		
		// Verificando se o sender é um player
		if (!(s instanceof Player)) {
			s.sendMessage(Mensagens.Console_Nao_Pode); 
			return true;
        }
		
		// Verificando se o player digitou o número de argumentos corretos
		if (args.length != 1) {
			s.sendMessage(Mensagens.Tpa_Comando_Incorreto);
			return true;
		}
		
		// Definindo o sender que esta enviando o tpa e o target que esta recebendo
		final String sender = s.getName();
		
		// Verificando se o sender e o player alvo são os mesmos
		if (s.getName().equals(args[0])) {
			s.sendMessage(Mensagens.Tp_Erro_Voce_Mesmo);
			return true;
		}
		
        // Pegando o player alvo e verificando se ele esta online
        Player pTarget = Bukkit.getPlayer(args[0]);
		if (pTarget == null) {
			s.sendMessage(Mensagens.Player_Offline);
			return true;
		}
		final String target = pTarget.getName();
		
		// Verificando se o player já enviou algum TPA
		if (TPs_enviados.containsKey(sender)) {
			
			// Verificando se o player já possui um TPA pendente com o alvo
			if (TPs_enviados.get(sender).contains(target)) {
				s.sendMessage(Mensagens.Tpa_Ja_Possui_Solicitacao.replace("%player%", target));
				return true;
			}
			
		// Caso ele não tenha enviado nenhum TPA então ele adicionado na lista
		} else {
			TPs_enviados.put(sender, new LinkedHashSet<>());
		}
		
		// Verificando se o player precisa esperar o cooldown
		if (cooldown.containsKey(sender)) {
			if (System.currentTimeMillis() < cooldown.get(sender)) {
				s.sendMessage(Mensagens.Tpa_Aguarde_Cooldown);
				return true;
			}
		}
		
		// Verificando se o alvo esta com o TPA desativado
		if (toggles.contains(target)) {
			s.sendMessage(Mensagens.Tpa_Desligado_Tptoggle.replace("%player%", target));
			return true;
		}
		
		// Adicionando o TPA na HashMap e informando o sender e o target
		if (!TPs_recebidos.containsKey(target)) TPs_recebidos.put(target, new LinkedHashSet<>());
		TPs_recebidos.get(target).add(sender);
		TPs_enviados.get(sender).add(target);
		
		// Adicionando o player na lista de cooldown
		cooldown.put(sender, (System.currentTimeMillis() + (1000 * Settings.Tempo_Para_Poder_Enviar_Outra_Solicitacao_Tpa)));
		s.sendMessage(Mensagens.Tpa_Solicitacao_Enviada_Sucesso.replace("%player%", target));
		pTarget.sendMessage(Mensagens.Tpa_Solicitacao_Recebida.replace("%player%", sender));
		
		// Iniciando a runnable que expira o teleporte depois de tantos segundos
		new BukkitRunnable() {
			@Override
			public void run() {
				// Caso o TPA ainda não tenha sido aceito então ele é expirado
				if (TPs_enviados.get(sender).contains(target)) {
					// Removendo o TPA da HashMap
					TPs_enviados.get(sender).remove(target);
					TPs_recebidos.get(target).remove(sender);
					// Verificando se nenhum dos players deslogou do servidor e informando
					if (s != null && pTarget != null) {
						s.sendMessage(Mensagens.Tpa_Solicitcao_Expirada_Player.replace("%player%", target));
						pTarget.sendMessage(Mensagens.Tpa_Solicitcao_Expirada_Alvo.replace("%player%", sender));
					}
				}
			}
		}.runTaskLater(Main.get(), 20 * Settings.Tempo_Para_Expirar_Solicitacao_Tpa);	
		return true;
	}
}
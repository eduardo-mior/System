package rush.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rush.apis.ViewDistanceAPI;
import rush.configuracoes.Mensagens;

public class ComandoRenderizacao implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {

		// Verificando se o sender é um player
		if (!(s instanceof Player)) {
			s.sendMessage(Mensagens.Console_Nao_Pode);
			return true;
		}

		// Verificando se o player digitou o número de argumentos corretos
		if (args.length != 1) {
			s.sendMessage(Mensagens.Renderizacao_Comando_Incorreto);
			return true;
		}
				
		// Verificando se o número é um número valido
		int renderizacao;
		try {
			renderizacao = Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
			s.sendMessage(Mensagens.Numero_Invalido.replace("%numero%", e.getMessage().split("\"")[1]));
			return true;
		}
		
		// Pegando o player e a renderizacao atual do player
		Player p = (Player) s;
		int atualRender = ViewDistanceAPI.getViewDistance(p);
		
		// Verificando se a renderizacao atual é a mesma que a digita
		if (atualRender == renderizacao) {
			s.sendMessage(Mensagens.Renderizacao_Ja_Definida.replace("%renderizacao%", String.valueOf(renderizacao)));
			return true;
		}

		// Verificando se o player tem permissão para usar distancia maior que a parmitida
		if (!s.hasPermission("system.renderizacao.staff")) {
			
			// Verificando se o player digitou uma renderização valida
			int limiteServer = Bukkit.getViewDistance();
			if (renderizacao >  limiteServer || renderizacao < 3) {
				s.sendMessage(Mensagens.Renderizacao_Valor_Invalido_Players.replace("%limite-servidor%", String.valueOf(limiteServer)));
				return true;
			}
		}
				
		// Verificando se a renderização é valida
		if (renderizacao > 32 || renderizacao < 3) {
			s.sendMessage(Mensagens.Renderizacao_Valor_Invalido_Staff);
			return true;
		}
		
		// Setando a renderiazação no Player e informando
		s.sendMessage(Mensagens.Renderizacao_Alterado_Sucesso.replace("%renderizacao%", String.valueOf(renderizacao)));
		ViewDistanceAPI.setViewDistance(p, renderizacao);
		return true;
	}
}
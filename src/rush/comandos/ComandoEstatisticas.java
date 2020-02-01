package rush.comandos;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rush.Main;
import rush.apis.OfflinePlayerAPI;
import rush.configuracoes.Mensagens;
import rush.enums.Version;
import rush.utils.TimeFormatter;

@SuppressWarnings("all")
public class ComandoEstatisticas implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {

		// Verificando se o player digitou o número de argumentos corretos
		if (args.length > 1) {
			s.sendMessage(Mensagens.Estatisticas_Comando_Incorreto);
			return true;
		}

		// Player indifinido
		Player p;

		// Caso o número de argumentos seja 1 então queremos pegar as estatisticas de um player
		if (args.length == 1) {

			// Pegando o player e verificando se ele esta online
			p = Main.getVersion() == Version.v1_14 || Main.getVersion() == Version.v1_15 ? Bukkit.getPlayer(args[0]) : OfflinePlayerAPI.getPlayer(args[0]);
			if (p == null) {
				s.sendMessage(Mensagens.Player_Offline);
				return true;
			}

			// Caso o número de argumentos seja 0 então pegamos as estatisticas do sender
		} else {

			// Verificando se o sender é um player
			if (!(s instanceof Player)) {
				s.sendMessage(Mensagens.Estatisticas_Comando_Incorreto);
				return true;
			}

			// Pegando o player
			p = (Player) s;
		}
		
		// Pegando o formatador de números e datas e configurando
		Locale brasil = new Locale("pt", "BR");
		NumberFormat nf = NumberFormat.getNumberInstance(brasil);
		DateFormat df = DateFormat.getDateTimeInstance(0, 0, brasil);
		nf.setMaximumFractionDigits(2);
		
		// Pegando as informações do player
		String player = p.getName();
		String mortes = nf.format(p.getStatistic(Statistic.DEATHS));
		String mobsMortos = nf.format(p.getStatistic(Statistic.MOB_KILLS));
		String playersMortos = nf.format(p.getStatistic(Statistic.PLAYER_KILLS));
		String danoCausado = nf.format(p.getStatistic(Statistic.DAMAGE_DEALT));
		String danoSofrido = nf.format(p.getStatistic(Statistic.DAMAGE_TAKEN));
		String pulosDados = nf.format(p.getStatistic(Statistic.JUMP));
		String itensPescados = nf.format(itensPescados(p));
		String itensEncantados = nf.format(itensEncantados(p));
		String distanciaCaminhada = nf.format(distanciaCaminhada(p) / 100.0);
		String distanciaNadada = nf.format(distanciaNadada(p) / 100.0);
		String distanciaVoada = nf.format(p.getStatistic(Statistic.FLY_ONE_CM) / 100.0);
		String distanciaVeiculo = nf.format(distanciaVeiculo(p) / 100.0);
		String distanciaTotal = nf.format(distanciaTotal(p) / 100.0);
		String tempoJogado = TimeFormatter.format(tempoJogado(p));
		String tempoSemMorrer = TimeFormatter.format(tempoSemMorrer(p));
		String primeiroLogin = df.format(new Date(p.getFirstPlayed()));
		
		// Enviando a mensagens com as informações para o sender
		s.sendMessage(Mensagens.Estatisticas_Informacoes
				.replace("%player%", player)
				.replace("%mortes%", mortes)
				.replace("%kill-mobs%", mobsMortos)
				.replace("%kill-players%", playersMortos)
				.replace("%dano-causado%", danoCausado)
				.replace("%dano-sofrido%", danoSofrido)
				.replace("%itens-pescados%", itensPescados)
				.replace("%itens-encantados%", itensEncantados)
				.replace("%pulou%", pulosDados)
				.replace("%distancia-caminhada%", distanciaCaminhada)
				.replace("%distancia-nadada%", distanciaNadada)
				.replace("%distancia-voada%", distanciaVoada)
				.replace("%distancia-veiculo%", distanciaVeiculo)
				.replace("%distancia-total%", distanciaTotal)
				.replace("%tempo-jogado%", tempoJogado)
				.replace("%tempo-sem-morrer%", tempoSemMorrer)
				.replace("%primeiro-login%", primeiroLogin.substring(0, primeiroLogin.length() - 4)));
		return true;
	}

	// Método para pegar a quantia de itens encantados pelo player
	private int itensEncantados(Player p) {
		try {
			return p.getStatistic(Statistic.ITEM_ENCHANTED);
		} catch (NoSuchFieldError e) {
			return -1;
		}
	}

	// Método para pegar a quantia total de metros percoridos
	private int distanciaTotal(Player p) {
		return p.getStatistic(Statistic.FLY_ONE_CM) + distanciaCaminhada(p) + distanciaNadada(p) + distanciaVeiculo(p);
	}

	// Método para pegar a quantia total de metros percoridos com veiculos
	private int distanciaVeiculo(Player p) {
		return p.getStatistic(Statistic.MINECART_ONE_CM) + p.getStatistic(Statistic.BOAT_ONE_CM) + p.getStatistic(Statistic.PIG_ONE_CM) + p.getStatistic(Statistic.HORSE_ONE_CM);
	}

	// Método para pegar a quantia total de metros percoridos na água
	private int distanciaNadada(Player p) {
		try {
			return p.getStatistic(Statistic.SWIM_ONE_CM) + p.getStatistic(Statistic.DIVE_ONE_CM);
		} catch (NoSuchFieldError e) {
			return p.getStatistic(Statistic.SWIM_ONE_CM) + p.getStatistic(Statistic.valueOf("WALK_ON_WATER_ONE_CM")) + p.getStatistic(Statistic.valueOf("WALK_UNDER_WATER_ONE_CM"));
		}
	}
	
	// Metódo para pegar a distancia corrida pelo player
	private int distanciaCaminhada(Player p) {
		try {
			return p.getStatistic(Statistic.WALK_ONE_CM) + p.getStatistic(Statistic.SPRINT_ONE_CM);
		} catch (NoSuchFieldError e) {
			return p.getStatistic(Statistic.WALK_ONE_CM);
		}
	}

	// Método para pegar a quantia total de millisegundos jogados pelo player
	private long tempoJogado(Player p) {
		try {
			return p.getStatistic(Statistic.PLAY_ONE_TICK) * 50;
		} catch (NoSuchFieldError e) {
			return p.getStatistic(Statistic.valueOf("PLAY_ONE_MINUTE")) * 50;
		}
	}


	// Método para pegar a quantia total de millisegundos jogados desde a ultima morte
	private long tempoSemMorrer(Player p) {
		try {
			return p.getStatistic(Statistic.TIME_SINCE_DEATH) * 50;
		} catch (NoSuchFieldError e) {
			return -1;
		}
	}
	
	// Método para pegar a quantia de itens pescados pelo player
	private double itensPescados(Player p) {
		try {
			return p.getStatistic(Statistic.JUNK_FISHED) + p.getStatistic(Statistic.TREASURE_FISHED) + p.getStatistic(Statistic.FISH_CAUGHT);
		} catch (NoSuchFieldError e) {
			return p.getStatistic(Statistic.FISH_CAUGHT);
		}
	}
}
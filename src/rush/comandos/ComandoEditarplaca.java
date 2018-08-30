package rush.comandos;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rush.configuracoes.Mensagens;

@SuppressWarnings("all")
public class ComandoEditarplaca implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {

		// Verificando se o sender é um player
		if (!(s instanceof Player)) {
			s.sendMessage(Mensagens.Console_Nao_Pode);
			return true;
		}

		// Verificando se o player digitou o número de argumentos corretos
		if (args.length < 2) {
			s.sendMessage(Mensagens.Editar_Placa_Comando_Incorreto);
			return true;
		}

		// Verificando se o número é um numero valido
		int linha;
		try {
			linha = Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
			s.sendMessage(Mensagens.Numero_Invalido.replace("%numero%", e.getMessage().split("\"")[1]));
			return true;
		}

		// Verificando se o player digitou uma linha valida
		if (linha < 1 || linha > 4) {
			s.sendMessage(Mensagens.Editar_Placa_Linha_Invalida);
			return true;
		}

		// Pegando o player e verificando se ele esta olhando para uma placa
		Player p = (Player) s;
		Block b = null;
		try {
			b = p.getTargetBlock((Set<Material>) null, 10);
		} catch (NoSuchMethodError e) {
			b = p.getTargetBlock((HashSet<Byte>) null, 10);
		}

		if (b == null || !(b.getState() instanceof Sign)) {
			s.sendMessage(Mensagens.Editar_Placa_Nao_Esta_Olhando);
			return true;
		}

		// Pegando a placa e a mensage e setando a mensagem na linha da placa
		String msg = "";
		for (int i = 1; i < args.length; i++) {msg += " " + args[i].replace('&', '§');}
		Sign sign = (Sign) b.getState();
		sign.setLine((linha - 1), msg);
		sign.update();
		s.sendMessage(Mensagens.Editar_Placa_Com_Sucesso);
		return true;
	}
}
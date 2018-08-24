package rush.comandos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import rush.configuracoes.Mensagens;
import rush.entidades.Kit;
import rush.entidades.Kits;

public class ComandoKits implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		ListKits(s);
		return true;
	}

	// Se a lista de kits sera exibida para um player então verificamos se ele tem permissão
	public static void ListKits(CommandSender s) {
		Collection<Kit> kits = Kits.getAll();
		List<String> listKits = new ArrayList<String>();

		// Criando um contador para saber o número de kits e passando por todos os kits
		int cont = 0;
		for (Kit kit : kits) {
			String permissao = kit.getPermissao();
			if (s.hasPermission(permissao) || s.hasPermission("system.kit.all")) {
				listKits.add(kit.getName());
				cont++;
			}
		}

		// Se o contador dor 0 então significa que nenhum kit foi criado
		if (cont == 0) {
			s.sendMessage(Mensagens.Nenhum_Kit_Criado);
			return;
		}

		// Exibindo a mensagem para o player
		String stringKits = listKits.toString().replace(",", Mensagens.Separador_De_Listas);
		s.sendMessage(Mensagens.Kits_Lista.replace("%kits%", stringKits.substring(1, stringKits.length() - 1)).replace("%n%", String.valueOf(cont)));
	}
	
	// Se a lista de kits sera exibida para um player então não é necessario verificarmos se ele tem permissão
	public static void ListKitsForStaff(CommandSender s) {
		Collection<Kit> kits = Kits.getAll();
		List<String> listKits = new ArrayList<String>();

		// Criando um contador para saber o número de kits e passando por todos os kits
		int cont = 0;
		for (Kit kit : kits) {
			listKits.add(kit.getName());
			cont++;	
		}

		// Se o contador dor 0 então significa que nenhum kit foi criado
		if (cont == 0) {
			s.sendMessage(Mensagens.Nenhum_Kit_Criado);
			return;
		}
		
		// Exibindo a mensagem para o player
		String stringKits = listKits.toString().replace(",", Mensagens.Separador_De_Listas);
		s.sendMessage(Mensagens.Kits_Lista.replace("%kits%", stringKits.substring(1, stringKits.length() - 1)).replace("%n%", String.valueOf(cont)));
	}
}
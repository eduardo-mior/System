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
		if (cmd.getName().equalsIgnoreCase("kits")) {
			ListKits(s);
		}
		return false;
	}

	public static void ListKits(CommandSender s) {
		Collection<Kit> kits = Kits.getAll();
		List<String> listKits = new ArrayList<String>();

		int cont = 0;
		for (Kit kit : kits) {
			String permissao = kit.getPermissao();
			if (s.hasPermission(permissao) || s.hasPermission("system.kit.all")) {
				listKits.add(kit.getName());
				cont++;
			}
		}

		if (cont == 0) {
			s.sendMessage(Mensagens.Nenhum_Kit_Criado);
			return;
		}

		String separador = Mensagens.Separador_De_Listas;
		String stringKits = listKits.toString().replace(",", separador);
		s.sendMessage(Mensagens.Kits_Lista.replace("%kits%", stringKits.substring(1, stringKits.length() - 1)).replace("%n%", String.valueOf(cont)));
	}
	
	public static void ListKitsForStaff(CommandSender s) {
		Collection<Kit> kits = Kits.getAll();
		List<String> listKits = new ArrayList<String>();

		int cont = 0;
		for (Kit kit : kits) {
			listKits.add(kit.getName());
			cont++;	
		}

		if (cont == 0) {
			s.sendMessage(Mensagens.Nenhum_Kit_Criado);
			return;
		}

		String separador = Mensagens.Separador_De_Listas;
		String stringKits = listKits.toString().replace(",", separador);
		s.sendMessage(Mensagens.Kits_Lista.replace("%kits%", stringKits.substring(1, stringKits.length() - 1)).replace("%n%", String.valueOf(cont)));
	}
}
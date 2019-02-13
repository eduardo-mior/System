package rush.comandos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import rush.Main;
import rush.configuracoes.Mensagens;
import rush.enums.EntityName;
import rush.sistemas.spawners.MobSpawner;
import rush.utils.Utils;

@SuppressWarnings("all")
public class ComandoSGive implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {

		// Verificando se o player digitou o número de argumentos corretos
		if (args.length > 3 || args.length < 2) {
			s.sendMessage(Mensagens.Sgive_Comando_Incorreto);
			return true;
		}

		// Pegando o player e verificando se ele esta online
		Player p = Bukkit.getPlayer(args[0]);
		if (p == null) {
			s.sendMessage(Mensagens.Player_Offline);
			return true;
		}

		// Pegando o tipo do spawners e verificando se é 1 tipo ovalido
		String type = args[1].toUpperCase();
		if (!Utils.isValidEnum(EntityType.class, type)) {
			String spawners = Utils.getEnumList(EntityType.class).toString().replace(",", Mensagens.Separador_De_Listas);
			s.sendMessage(Mensagens.Spawner_Desconhecido.replace("%lista%", spawners));
			return true;
		}

		// Caso o player informe o argumento da quantia o número é verificado, se não a quantia fica 1
		int quantia = 1;
		if (args.length == 3) {
			try {
				quantia = Integer.parseInt(args[2]);
			} catch (NumberFormatException e) {
				s.sendMessage(Mensagens.Numero_Invalido.replace("%numero%", e.getMessage().split("\"")[1]));
				return true;
			}
		}

		// Criando o item do mobspawner e enviando para o player
		ItemStack spawner = Main.isOldVersion() ? MobSpawner.getOld(type, quantia) : MobSpawner.get(type, quantia);
		for (ItemStack item : p.getInventory().addItem(spawner).values()) {
			p.getWorld().dropItem(p.getLocation(), item);
		}
		s.sendMessage(Mensagens.Spawner_Givado.replace("%tipo%", EntityName.valueOf(type).getName()));
		return true;
	}

}
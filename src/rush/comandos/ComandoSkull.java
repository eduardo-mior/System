package rush.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import rush.Main;
import rush.apis.SkullAPI;
import rush.configuracoes.Mensagens;

@SuppressWarnings("all")
public class ComandoSkull implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {

		// Verificando se o sender é um player
		if (!(s instanceof Player)) {

			// Verificando se o player digitou o número de argumentos corretos
			if (args.length != 2) {
				s.sendMessage(Mensagens.Skull_Comando_Incorreto);
				return true;
			}

			// Pegando o player e verificando se ele esta online
			Player p = Bukkit.getPlayer(args[1]);
			if (p == null) {
				s.sendMessage(Mensagens.Player_Offline);
				return true;
			}

			// Verificando se o player quer givar uma skull de URL, ou uma skull de player
			if (!Main.isOldVersion() && args[0].length() > 32) {
				
				// Verificando se a URL é valida
				if (args[0].length() > 70 || args[0].length() < 55 || args[0].contains("http") || args[0].contains("texture")) {
					s.sendMessage(Mensagens.Skull_Url_Incorreta);
					return true;
				}
				
				// Se a URL for valida, então é obtida a skull e enviada para o player
				p.getInventory().addItem(SkullAPI.getByUrl("http://textures.minecraft.net/texture/" + args[0]));
				s.sendMessage(Mensagens.Skull_Enviada_Outro.replace("%dono%", "URL").replace("%player%", p.getName()));
				return true;
			}
			
			// Caso o argumento não sejá uma URL então é pegada a cabeça de um player e enviada para o player
			p.getInventory().addItem(SkullAPI.getByName(args[0]));
			s.sendMessage(Mensagens.Skull_Enviada_Outro.replace("%dono%", args[0]).replace("%player%", p.getName()));
			return true;
		}

		// Verificando se o player digitou o número de argumentos corretos
		if (args.length != 2 && args.length != 1) {
			s.sendMessage(Mensagens.Skull_Comando_Incorreto);
			return true;
		}

		// Pegando a skull e o seu dono
		String dono;
		ItemStack skull;

		// Verificando se o player quer givar uma skull de URL, ou uma skull de player
		if (!Main.isOldVersion() && args[0].length() > 32) {
			
			// Verificando se a URL é valida
			if (args[0].length() > 70 || args[0].length() < 55 || args[0].contains("http") || args[0].contains("texture")) {
				s.sendMessage(Mensagens.Skull_Url_Incorreta);
				return true;
			}
			
			// Se a URL for valida, então é obtida a skull e enviada para o player
			skull = SkullAPI.getByUrl("http://textures.minecraft.net/texture/" + args[0]);
			dono = "URL";
		} else {
			skull = SkullAPI.getByName(args[0]);
			dono = args[0];
		}
		
		// Verificando se a skull vai ser enviada para outro player
		if (args.length == 2) {

			// Pegando o player e verificando se ele esta online
			Player p = Bukkit.getPlayer(args[1]);
			if (p == null) {
				s.sendMessage(Mensagens.Player_Offline);
				return true;
			}

			// Adicionando a skull no inventario do player
			p.getInventory().addItem(skull);
			s.sendMessage(Mensagens.Skull_Enviada_Outro.replace("%dono%", dono).replace("%player%", p.getName()));
			return true;
		}

		// Caso o número de argumentos for menor que 2 então a skull é enviada para o sender
		Player p = (Player) s;
		p.getInventory().addItem(skull);
		s.sendMessage(Mensagens.Skull_Enviada_Voce.replace("%dono%", dono));
		return true;
	}
}
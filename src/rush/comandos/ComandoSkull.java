package rush.comandos;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import rush.configuracoes.Mensagens;

public class ComandoSkull implements CommandExecutor {
	
	@SuppressWarnings("deprecation")	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("skull")) {
			
			// Verificando se o sender é um player
			if (!(s instanceof Player)) {
				
				// Verificando se o player digitou o número de argumentos corretos
				if (args.length != 2) {
					s.sendMessage(Mensagens.Skull_Comando_Incorreto);
					return false;
				}
				
		        // Pegando o player e verificando se ele esta online
				Player p = Bukkit.getPlayer(args[1]);
				if (p == null) {
					s.sendMessage(Mensagens.Player_Offline);
					return false;
				}
				
				// Pegando a skull e enviando para o player
				ItemStack skull = getCabeca(args[0]);
				p.getInventory().addItem(skull);
				s.sendMessage(Mensagens.Skull_Enviada_Outro.replace("%dono%", args[0]).replace("%player%", p.getName()));
				return false;
			}
			
			// Verificando se o player digitou o número de argumentos corretos
			if (args.length != 2 && args.length != 1) {
				s.sendMessage(Mensagens.Skull_Comando_Incorreto);
				return false;
			}
			
			// Pegando a skull
			ItemStack skull = getCabeca(args[0]);
			
			// Verificando se a skull vai ser enviada para outro player
			if (args.length == 2) {
				
		        // Pegando o player e verificando se ele esta online
				Player p = Bukkit.getPlayer(args[1]);
				if (p == null) {
					s.sendMessage(Mensagens.Player_Offline);
					return false;
				}

				// Adicionando a skull no inventario do player
				p.getInventory().addItem(skull);
				s.sendMessage(Mensagens.Skull_Enviada_Outro.replace("%dono%", args[0]).replace("%player%", p.getName()));
				return false;
			}
			
			// Caso o número de argumentos for menor que 2 então a skull é enviada para o sender
			Player p = (Player) s;
			p.getInventory().addItem(skull);
			s.sendMessage(Mensagens.Skull_Enviada_Voce.replace("%dono%", args[0]));
		}
		return false;
	}
	
	private ItemStack getCabeca(String dono) {
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
		SkullMeta meta = (SkullMeta)skull.getItemMeta();
		meta.setOwner(dono);
		skull.setItemMeta(meta);
		return skull;
	}
}
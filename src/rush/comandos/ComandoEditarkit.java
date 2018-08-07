package rush.comandos;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import rush.configuracoes.Mensagens;
import rush.entidades.Kit;
import rush.entidades.Kits;
import rush.utils.DataManager;

public class ComandoEditarkit implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("editarkit")) {
			
			// Verificando se o player digitou o número de argumentos correto
			if (args.length > 3  || args.length < 2) {
		        s.sendMessage(Mensagens.EditarKit_Comando_Incorreto);
		        return false;
			}
			
			// Pegando o argumento e verificando se o kit existe
			String nome = args[0].toLowerCase();
			if (!Kits.contains(nome)) {
		        s.sendMessage(Mensagens.Kit_Nao_Existe.replace("%kit%", nome));
		        ComandoKits.ListKits(s);
		        return false;
			}
			
			// Pegando o kit e a config do Kit
			Kit kit = Kits.get(nome);
			File file = DataManager.getFile(nome, "kits");
			FileConfiguration config = DataManager.getConfiguration(file);
			
			// Verificando se o player quer editar os itens do kit
			if (args[1].equalsIgnoreCase("itens")) {
				
				// Verificando se o sender não é o console
				if (!(s instanceof Player)) {
				    s.sendMessage(Mensagens.Console_Nao_Pode);
				    return false;
				}
				
				Player p = (Player)s;
		    	ItemStack[] ITENS = kit.getItens();
				Inventory inv = Bukkit.getServer().createInventory(p, 36, "§0Editar Kit §n" + kit);
		    	for (int i = 0; ITENS.length > i; i++) {
		    		 ItemStack item = ITENS[i];
		    		 if (item != null) inv.addItem(item);
		    	}
		    	p.openInventory(inv);
		    	return false;
			}
			
			// Verificando se o player quer editar o delay do kit
			if (args[1].equalsIgnoreCase("delay")) {
				if (args.length != 3) {
			        s.sendMessage(Mensagens.EditarKit_Comando_Incorreto_Delay);
			        return false;
				}
				
	            long delay;
	            try {
	                delay = Long.valueOf(args[2]);
	            }
	            catch (NumberFormatException e) {
	                s.sendMessage(Mensagens.Numero_Invalido);
	                return false;
	            }
	            
	            kit.setDelay(delay);
	            config.set("Delay", delay);
				try {
					config.save(file);
					s.sendMessage(Mensagens.Kit_Editado.replace("%kit%", args[0]));
				} catch (IOException e) {
					Bukkit.getConsoleSender().sendMessage(Mensagens.Falha_Ao_Salvar.replace("%arquivo%", file.getName()));
				}
				return false;
			}
			
			// Verificando se o player quer editar a permissão para pegar o kit
			if (args[1].equalsIgnoreCase("perm")) {
				if (args.length != 3) {
			        s.sendMessage(Mensagens.EditarKit_Comando_Incorreto_Perm);
			        return false;
				}
				
				kit.setPermissao(args[2]);
				config.set("Permissao", args[2]);
				try {
					config.save(file);
					s.sendMessage(Mensagens.Kit_Editado.replace("%kit%", args[0]));
				} catch (IOException e) {
					Bukkit.getConsoleSender().sendMessage(Mensagens.Falha_Ao_Salvar.replace("%arquivo%", file.getName()));
				}
				return false;
			}
			
			// Caso nenhuma das opção acima for aceita sera dado como comando incorreto
	        s.sendMessage(Mensagens.EditarKit_Comando_Incorreto);
		}
		return false;
	}
}
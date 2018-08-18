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
				return true;
			}
			
			// Pegando o argumento e verificando se o kit existe
			String nome = args[0].toLowerCase();
			if (!Kits.contains(nome)) {
		        s.sendMessage(Mensagens.Kit_Nao_Existe.replace("%kit%", nome));
		        ComandoKits.ListKits(s);
				return true;
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
					return true;
				}
				
				// Pegando o player e abrindo um inventarios com os itens, o resto é feito pela classe KitsListener
				Player p = (Player)s;
				Inventory inv = Bukkit.getServer().createInventory(p, 36, "§1Kit §n" + nome);
		    	for (ItemStack item : kit.getItens()) {
		    		 if (item != null) inv.addItem(item);
		    	}
		    	p.openInventory(inv);
				return true;
			}
			
			// Verificando se o player quer editar o delay do kit
			if (args[1].equalsIgnoreCase("delay")) {
				
				// Verificando se o player digitou o número de argumentos correto
				if (args.length != 3) {
			        s.sendMessage(Mensagens.EditarKit_Comando_Incorreto_Delay);
					return true;
				}
				
				// Verificando se o número é um número valido
	            long delay;
	            try {
	                delay = Long.parseLong(args[2]);
	            } catch (NumberFormatException e) {
	                s.sendMessage(Mensagens.Numero_Invalido.replace("%numero%", e.getMessage().split("\"")[1]));
	    			return true;
	            }
	            
	            // Salvando os arquivos na config
	            kit.setDelay(delay);
	            config.set("Delay", delay);
				try {
					config.save(file);
					s.sendMessage(Mensagens.Kit_Editado.replace("%kit%", args[0]));
				} catch (IOException e) {
					Bukkit.getConsoleSender().sendMessage(Mensagens.Falha_Ao_Salvar.replace("%arquivo%", file.getName()));
				}
				return true;
			}
			
			// Verificando se o player quer editar a permissão para pegar o kit
			if (args[1].equalsIgnoreCase("perm")) {
				
				// Verificando se o player digitou o número de argumentos correto
				if (args.length != 3) {
			        s.sendMessage(Mensagens.EditarKit_Comando_Incorreto_Perm);
					return true;
				}
				
	            // Salvando os arquivos na config
				kit.setPermissao(args[2]);
				config.set("Permissao", args[2]);
				try {
					config.save(file);
					s.sendMessage(Mensagens.Kit_Editado.replace("%kit%", args[0]));
				} catch (IOException e) {
					Bukkit.getConsoleSender().sendMessage(Mensagens.Falha_Ao_Salvar.replace("%arquivo%", file.getName()));
				}
				return true;
			}
			
			// Caso nenhuma das opção acima for aceita sera dado como comando incorreto
	        s.sendMessage(Mensagens.EditarKit_Comando_Incorreto);
			return true;
		}
		return false;
	}
}
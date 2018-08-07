package rush.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import rush.configuracoes.Mensagens;
import rush.sistemas.spawners.MobSpawner;
import rush.utils.EnumUtils;

public class ComandoSGive implements CommandExecutor {	
	
	@SuppressWarnings("deprecation")		
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
        if (cmd.getName().equalsIgnoreCase("sgive")) {
        
			// Verificando se o player digitou o número de argumentos corretos
	        if (args.length > 3 || args.length < 2) {
	        	s.sendMessage(Mensagens.Sgive_Comando_Incorreto);
	            return false;
	        }
	        	      
	        // Pegando o player e verificando se ele esta online
            Player p = Bukkit.getPlayer(args[0]);
			if (p == null) {
				s.sendMessage(Mensagens.Player_Offline);
				return false;
			}
			
			// Pegando o tipo do spawners e verificando se é 1 tipo ovalido
    		String type = args[1].toUpperCase();
    		if (!EnumUtils.isValidEnum(EntityType.class, type)) {
    			String spawners = EnumUtils.getEnumList(EntityType.class).toString();
            	s.sendMessage(Mensagens.Spawner_Desconhecido.replace("%lista%", spawners));
            	return false;
    		}
	        
    		// Caso o player informe o argumento da quantia o número é verificado, se não a quantia fica 1
            int quantia = 1;
	        if (args.length == 3) {
	            try {
	                quantia = Integer.valueOf(args[2]);
	            }
	            catch (NumberFormatException e) {
	                s.sendMessage(Mensagens.Numero_Invalido);
	                return false;
	            }
	        }
	        
	        // Criando o item do mobspawner e enviando para o player
        	ItemStack spawner = MobSpawner.get(type, quantia);
        	p.getInventory().addItem(spawner);
        	s.sendMessage(Mensagens.Spawner_Givado.replace("%tipo%", args[1].replace("_", " ")));
        }
        return false;
    }
}

package rush.comandos;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import rush.configuracoes.Mensagens;
import rush.utils.DataManager;

public class ComandoWarps implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("warps")) {
			ListWarps(s);
			return true;
		}
		return false;
	}
	
	// Se a lista de kits sera exibida para um player então verificamos se ele tem permissão
	public static void ListWarps(CommandSender s) {
		
		// Pegando todas os arquivos das warps
		File folder = DataManager.getFolder("warps");
		File[] file = folder.listFiles();
  	  	
  	  	// Verificando se já existe alguma warp criada
  	  	if (file.length == 0) {
  	  		s.sendMessage(Mensagens.Nenhuma_Warp_Definida);
  	  		return;
  	  	} 
  	  	
  	  	// Pegando o nome e o número de warps
  	  	List<String> warps = new ArrayList<String>();
  	  	int cont = 0;
  	  	for (int i=0; i < file.length; i++) {
  	  		if (file[i].isFile()) {
  	  			String permissao = DataManager.getConfiguration(file[i]).getString("Permissao");
  	  			if(s.hasPermission(permissao)) {
  	  				warps.add(file[i].getName().replace(".yml", ""));
  	  				cont++;
  	  			}
  	  		}
  	  	}
  	  	
  	  	// Se o contandor dor 0 então nenhuma warp foi criada
  	  	if (cont == 0) {
  	  		s.sendMessage(Mensagens.Nenhuma_Warp_Definida);
  	  		return;
  	  	} 
  	  	
		// Exibindo a mensagem para o player
  	  	String separador = Mensagens.Separador_De_Listas;
  	  	String warplist = warps.toString().replace("%n%", String.valueOf(cont)).replace(",", separador);
  	  	s.sendMessage(Mensagens.Warps_Lista.replace("%warps%", warplist.substring(1,warplist.length() -1)));
	}
	
	// Se a lista de kits sera exibida para um player então não é necessario verificarmos se ele tem permissão
	public static void ListWarpsForStaff(CommandSender s) {
		
		// Pegando todas os arquivos das warps
		File folder = DataManager.getFolder("warps");
		File[] file = folder.listFiles();
  	  	
  	  	// Verificando se já existe alguma warp criada
  	  	if (file.length == 0) {
  	  		s.sendMessage(Mensagens.Nenhuma_Warp_Definida);
  	  		return;
  	  	} 
  	  	
  	  	// Pegando o nome e o número de warps
  	  	List<String> warps = new ArrayList<String>();
  	  	int cont = 0;
  	  	for (int i = 0; i < file.length; i++) {
  	  		if (file[i].isFile()) {
  	  			warps.add(file[i].getName().replace(".yml", ""));
  	  			cont++;	
  	  		}
  	  	}
  	  	
  	  	// Se o contandor dor 0 então nenhuma warp foi criada
  	  	if (cont == 0) {
  	  		s.sendMessage(Mensagens.Nenhuma_Warp_Definida);
  	  		return;
  	  	} 
  	  	
		// Exibindo a mensagem para o player
  	  	String separador = Mensagens.Separador_De_Listas;
  	  	String warplist = warps.toString().replace("%n%", String.valueOf(cont)).replace(",", separador);
  	  	s.sendMessage(Mensagens.Warps_Lista.replace("%warps%", warplist.substring(1,warplist.length() -1)));
	}
}
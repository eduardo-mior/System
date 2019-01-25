package rush.comandos;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import rush.configuracoes.Mensagens;
import rush.entidades.Warp;
import rush.entidades.Warps;
import rush.utils.manager.DataManager;

public class ComandoSetwarp implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		
		// Verificando se o sender é um player
		if (!(s instanceof Player)) {
			s.sendMessage(Mensagens.Console_Nao_Pode); 
			return true;
		}	 
			
		// Verificando se o player digitou o número de argumentos corretos
		if (args.length != 1) {
			s.sendMessage(Mensagens.SetWarp_Comando_Incorreto);
			return true;
		}
			     
		// Pegando o argumento, o file e a config
		String warp = args[0].toLowerCase();
		File file = DataManager.getFile(warp, "warps");
		FileConfiguration config = DataManager.getConfiguration(file);
		
		// Verificando se a já warp existe
		if (file.exists()) {
			s.sendMessage(Mensagens.Warp_Ja_Existe.replace("%warp%", warp));
			return true;
		}
		
		Player p = (Player) s;
		DataManager.createFile(file);
		Location location = p.getLocation();
		String loc = serializeLocation(location);
		String perm = "system.warp." + warp;
		String semPerm = "&cVocê não tem permissão para se teleportar para a warp " + args[0] + "!";
		String inicio = "&aVocê sera teleportado para a warp " + args[0] + " em 5 segundos!";
		String fim = "&aTeleportado com sucesso para a warp " + args[0] + "!";
		String title = "&aVOCÊ FOI TELEPORTADO";
		String subtitle = "&ePARA A WARP " + args[0];
		Warp w = new Warp(warp, loc, perm, semPerm, 5, false, inicio, fim, true, title, subtitle);
		config.set("Localizacao" , loc);
		config.set("Permissao" , perm);
		config.set("MensagemSemPermissao", semPerm);
		config.set("Delay", 5);
		config.set("DelayParaVips", false);
		config.set("MensagemInicio" , inicio);
		config.set("MensagemFinal" , fim);
		config.set("EnviarTitle" , true);
		config.set("Title" , title);
		config.set("SubTitle" , subtitle);
		try {
			Warps.create(warp, w);
			config.save(file);
			s.sendMessage(Mensagens.Warp_Definida.replace("%warp%", warp));
		} catch (IOException e) {
			Bukkit.getConsoleSender().sendMessage(Mensagens.Falha_Ao_Salvar.replace("%arquivo%", file.getName()));
		}
		return true;
	}

    private String serializeLocation(Location l) {
    	return l.getWorld().getName()+","+l.getX()+","+l.getY()+","+l.getZ()+","+l.getYaw()+","+l.getPitch();
    }
    
}
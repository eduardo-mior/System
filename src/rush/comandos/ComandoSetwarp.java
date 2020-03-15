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
import rush.configuracoes.Settings;
import rush.entidades.Warp;
import rush.entidades.Warps;
import rush.utils.Utils;
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
		
		// Verificando se o nome da Warp contem caracteres especiais
		if (Utils.stringContainsSpecialCharacters(args[0])) {
			s.sendMessage(Mensagens.Erro_Nome_Com_Caracteres_Especiais.replace("%nome%", "da warp").replace("%caractere%", Utils.getSpecialCharacters(args[0])));
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
		String loc = Utils.serializeLocation(location);
		String perm = "system.warp." + warp;
		String semPerm = Mensagens.Warp_MensagemSemPermissao.replace("%warp%", args[0]);
		String inicio = Mensagens.Warp_MensagemInicio.replace("%warp%", args[0]);
		String fim = Mensagens.Warp_MensagemFinal.replace("%warp%", args[0]);
		String title = Mensagens.Warp_Title.replace("%warp%", args[0]);
		String subtitle = Mensagens.Warp_SubTitle.replace("%warp%", args[0]);
		String teleportado = Mensagens.Warp_MensagemPlayerTeleportado.replace("%warp%", args[0]);
		String teleportadoStaff = Mensagens.Warp_MensagemPlayerTeleportadoStaff.replace("%warp%", args[0]);
		int delay = Settings.Delay_Padrao_Warps;
		Warp w = new Warp(warp, loc, perm, semPerm, delay, false, true, inicio, fim, true, title, subtitle, teleportado, teleportadoStaff);
		config.set("Localizacao" , loc);
		config.set("Permissao" , perm);
		config.set("MensagemSemPermissao", semPerm);
		config.set("Delay", delay);
		config.set("DelayParaVips", false);
		config.set("EnviarMensagem" , true);
		config.set("MensagemInicio" , inicio);
		config.set("MensagemFinal" , fim);
		config.set("EnviarTitle" , true);
		config.set("Title" , title);
		config.set("SubTitle" , subtitle);
		config.set("MensagemPlayerTeleportado" , teleportado);
		config.set("MensagemPlayerTeleportadoStaff" , teleportadoStaff);
		try {
			Warps.create(warp, w);
			config.save(file);
			s.sendMessage(Mensagens.Warp_Definida.replace("%warp%", warp));
		} catch (IOException e) {
			Bukkit.getConsoleSender().sendMessage(Mensagens.Falha_Ao_Salvar.replace("%arquivo%", file.getName()));
		}
		return true;
	}
}
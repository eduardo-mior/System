package rush.comandos;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.scheduler.BukkitRunnable;

import rush.Main;
import rush.configuracoes.Mensagens;
import rush.entidades.Warp;
import rush.entidades.Warps;

public class ComandoWarpOLD implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		
		// Verificando se o sender é um player
		if (!(s instanceof Player)) {
			s.sendMessage(Mensagens.Console_Nao_Pode); 
			return true;
		}
			     
		// Verificando se o sender digitou o número de argumentos correto
		if (args.length != 1) {
			s.sendMessage(Mensagens.Warp_Comando_Incorreto);
			return true;
		}
			     
		// Pegando a warp e verificando se ela existe
		String warp = args[0].toLowerCase();
		if (!Warps.contains(warp)) {
			s.sendMessage(Mensagens.Warp_Nao_Existe.replace("%warp%", warp));
			ComandoWarps.ListWarps(s);
			return true;
		}
		
		// Pegando o player e a localização
		Player p = (Player) s;
		Warp w = Warps.get(warp);
		Location location = w.getLocation();
		
		// Verificando se o player tem permissão para se teleportar a warp
		if (!s.hasPermission(w.getPermissao()) && !s.hasPermission("system.warp.all")) {
			s.sendMessage(w.getSemPermissao());
			return true;
		} 
			    	
		// Verificando se o player tem permissão para se teleportar sem delay
		if (!s.hasPermission("system.semdelay") || w.delayParaVips()) {
			s.sendMessage(w.getMensagemInicio());
			new BukkitRunnable() {
				@Override
				public void run() {
					s.sendMessage(w.getMensagemFinal());
					p.teleport(location, TeleportCause.COMMAND);
				}
			}.runTaskLater(Main.get(), 20L * w.getDelay());
			return true;
		}
			    	
		// Caso o player tiver permissão para se teleportar sem delay então
		s.sendMessage(w.getMensagemFinal());
		p.teleport(location, TeleportCause.COMMAND);
		return true;
	}
}
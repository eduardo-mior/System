package rush.comandos;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.scheduler.BukkitRunnable;

import rush.Main;
import rush.apis.TitleAPI;
import rush.configuracoes.Mensagens;
import rush.entidades.Warp;
import rush.entidades.Warps;

@SuppressWarnings("all")
public class ComandoWarp implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		
		// Verificando se o sender é um player
		if (!(s instanceof Player)) {
			s.sendMessage(Mensagens.Console_Nao_Pode); 
			return true;
		}
			     
		// Verificando se o sender digitou o número de argumentos correto
		if (args.length < 1 || args.length > 2) {
			if (s.hasPermission("system.warp.outros")) {
				s.sendMessage(Mensagens.Warp_Comando_Incorreto_Staff);
			} else {
				s.sendMessage(Mensagens.Warp_Comando_Incorreto);
			}
			return true;
		}
			     
		// Pegando a warp e verificando se ela existe
		String warp = args[0].toLowerCase();
		if (!Warps.contains(warp)) {
			s.sendMessage(Mensagens.Warp_Nao_Existe.replace("%warp%", warp));
			if (!s.hasPermission("system.warp.all")) {
				ComandoWarps.ListWarps(s);
			} else {
				ComandoWarps.ListWarpsForStaff(s);
			}
			return true;
		}
		
		// Pegando a localização
		Warp w = Warps.get(warp);
		Location location = w.getLocation();
		
		// Verificando se o número de argumentos for 2 então queremos teleportar outro player
		if (args.length == 2) {
			
			// Pegando o player e verificando se ele esta online
			Player target = Bukkit.getPlayer(args[1]);
			if (target == null) {
				s.sendMessage(Mensagens.Player_Offline);
				return true;
			}		
			
			target.teleport(w.getLocation(), TeleportCause.COMMAND);
			target.sendMessage(w.getMensagemPlayerTeleportado().replace("%player%", s.getName()));
			s.sendMessage(w.getMensagemPlayerTeleportadoStaff().replace("%player%", target.getName()));
			return true;
		}
		
		// Verificando se o player tem permissão para se teleportar a warp
		if (!s.hasPermission(w.getPermissao()) && !s.hasPermission("system.warp.all")) {
			s.sendMessage(w.getSemPermissao());
			return true;
		} 
		
		// Pegando o player
		Player p = (Player) s;
			    	
		// Verificando se o player tem permissão para se teleportar sem delay
		if (!s.hasPermission("system.semdelay") || w.delayParaVips()) {
			if (w.getDelay() > 0 && w.enviarMensagem()) {
				s.sendMessage(w.getMensagemInicio());
			}
			new BukkitRunnable() {
				@Override
				public void run() {
					p.teleport(location, TeleportCause.COMMAND);
					if (w.enviarTitle()) {
						TitleAPI.sendTitle(p, 10, 40, 10, w.getTitle(), w.getSubtitle());		
					}
					if (w.enviarMensagem()) {
						s.sendMessage(w.getMensagemFinal());
					}
				}
			}.runTaskLater(Main.get(), 20L * w.getDelay());
			return true;
		}
			    	
		// Caso o player tiver permissão para se teleportar sem delay então
		p.teleport(location, TeleportCause.COMMAND);
		if (w.enviarTitle()) {
			TitleAPI.sendTitle(p, 10, 40, 10, w.getTitle(), w.getSubtitle());		
		}
		s.sendMessage(w.getMensagemFinal());
		return true;
	}
}
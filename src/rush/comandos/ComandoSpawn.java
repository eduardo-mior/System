package rush.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.scheduler.BukkitRunnable;

import rush.Main;
import rush.configuracoes.Locations;
import rush.configuracoes.Mensagens;
import rush.configuracoes.Settings;

@SuppressWarnings("all")
public class ComandoSpawn implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lb, String[] args) {

		// Verificando se o sender é um player
		if (!(s instanceof Player)) {
			s.sendMessage(Mensagens.Console_Nao_Pode);
			return true;
		}
		
		// Verificando se o número de argumentos é maior que 0 e se ele tem permissão para teleportar outros
		if (args.length > 0 && s.hasPermission("system.spawn.outros")) {
			
			// Verificando se ele digitou o número de argumentos corretos
			if (args.length != 2) {
				s.sendMessage(Mensagens.Spawn_Comando_Incorreto);
				return true;
			}
			
			// Pegando o player e verificando se ele esta online
			Player target = Bukkit.getPlayer(args[1]);
			if (target == null) {
				s.sendMessage(Mensagens.Player_Offline);
				return true;
			}
			
			// Verificando se o player quer teleportar o spawn normal
			if (args[0].equals("normal")) {
				target.teleport(Locations.spawn, TeleportCause.COMMAND);
				target.sendMessage(Mensagens.Teleportado_Para_Spawn.replace("%player%", s.getName()));
				s.sendMessage(Mensagens.Teleportado_Outro_Com_Sucesso_Spawn.replace("%player%", target.getName()));
				return true;
			}
			
			// Verificando se o player quer teleportar o spawn vip
			if (args[0].equals("vip")) {
				target.teleport(Locations.spawnVip, TeleportCause.COMMAND);
				target.sendMessage(Mensagens.Teleportado_Para_Spawn_Vip.replace("%player%", s.getName()));
				s.sendMessage(Mensagens.Teleportado_Outro_Com_Sucesso_Spawn_Vip.replace("%player%", target.getName()));
				return true;
			}
			
			// Caso o argumento não seja nem normal nem vip
			s.sendMessage(Mensagens.Spawn_Comando_Incorreto);
			return true;
		}

		// Pegando o player e verificando se ele possui permissão para se teleportar sem precisar esperar
		Player p = (Player) s;
		if (!s.hasPermission("system.semdelay")) {
			s.sendMessage(Mensagens.Iniciando_Teleporte_Spawn);
			new BukkitRunnable() {
				@Override
				public void run() {
					p.teleport(Locations.spawn, TeleportCause.COMMAND);
					s.sendMessage(Mensagens.Teleportado_Com_Sucesso_Spawn);
				}
			}.runTaskLater(Main.get(), 20L * Settings.Delay_Para_Teleportar_Comandos);
			return true;
		}

		// Caso o player possui a permissão para se teleportar sem delay o código acima é ignorado
		p.teleport(Locations.spawn, TeleportCause.COMMAND);
		s.sendMessage(Mensagens.Teleportado_Com_Sucesso_Spawn);
		return true;
	}
}
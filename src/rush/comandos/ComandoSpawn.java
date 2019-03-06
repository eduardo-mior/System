package rush.comandos;

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

public class ComandoSpawn implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lb, String[] args) {

		// Verificando se o sender é um player
		if (!(s instanceof Player)) {
			s.sendMessage(Mensagens.Console_Nao_Pode);
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
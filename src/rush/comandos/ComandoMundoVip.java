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
public class ComandoMundoVip implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		
		// Verificando se o número de argumentos é maior que 0 e se ele tem permissão para teleportar outros
		if (args.length > 0 && s.hasPermission("system.mundovip.outros")) {
			
			// Verificando se ele digitou o número de argumentos corretos
			if (args.length > 1) {
				s.sendMessage(Mensagens.MundoVip_Comando_Incorreto);
				return true;
			}

			// Pegando o player e verificando se ele esta online
			Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				s.sendMessage(Mensagens.Player_Offline);
				return true;
			}			
			
			// Teleportando o player para o spawn e informando
			target.teleport(Locations.areaVip, TeleportCause.COMMAND);
			target.sendMessage(Mensagens.Teleportado_Para_Vip.replace("%player%", s.getName()));
			s.sendMessage(Mensagens.Teleportado_Outro_Com_Sucesso_Vip.replace("%player%", target.getName()));
			return true;
		}

		// Verificando se o sender é um player
		if (!(s instanceof Player)) {
			s.sendMessage(Mensagens.Console_Nao_Pode);
			return true;
		}
		
		// Pegando o player
		Player p = (Player) s;

		// Verificando se o player tem permissão para se teleportar para areavip
		if (!s.hasPermission("system.vip")) {

			// Verificando se o camarote para os sem vip esta habilitado e teleportando o palyer
			if (Settings.Ativar_Camarote_Para_Os_Sem_Vip) {
				s.sendMessage(Mensagens.Iniciando_Teleporte_Vip);
				new BukkitRunnable() {
					@Override
					public void run() {
						p.teleport(Locations.areaNaoVip, TeleportCause.COMMAND);
						s.sendMessage("§f ");
						s.sendMessage(Mensagens.Teleportado_Com_Sucesso_Sem_Vip);
						s.sendMessage("§f ");

					}
				}.runTaskLater(Main.get(), 20L * Settings.Delay_Para_Teleportar_Comandos);
				return true;
			}

			// Caso o camarote para os sem vips não esteja habilitado então um erro é exibido
			s.sendMessage(Mensagens.Sem_Permissao);
			return true;
		}

		// Caso o player possua a permissão 'system.vip' este código sera executado
		p.teleport(Locations.areaVip, TeleportCause.COMMAND);
		s.sendMessage(Mensagens.Teleportado_Com_Sucesso_Vip);
		return true;

	}
}

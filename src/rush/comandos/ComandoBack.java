package rush.comandos;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.scheduler.BukkitRunnable;

import rush.Main;
import rush.addons.MassiveFactions;
import rush.configuracoes.Mensagens;
import rush.configuracoes.Settings;
import rush.sistemas.comandos.BackListener;

public class ComandoBack implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {

		// Verificando se o sender é um player
		if (!(s instanceof Player)) {
			s.sendMessage(Mensagens.Console_Nao_Pode);
			return true;
		}

		// Pegando o player
		Player p = (Player) s;

		// Verificando se o player possui um lugar para se voltar
		if (!BackListener.backList.containsKey(p.getName())) {
			p.sendMessage(Mensagens.Nao_Possui_Back);
			return true;
		}
			
		// Pegando a ultima localização do player
		Location l = BackListener.backList.get(p.getName());

		// Verificando se a compatibilidade com o factions
		if (Main.setupFactions) {
			if (!MassiveFactions.isValidTeleport(l, p)) {
				return true;
			}
		}

		// Verificando se ele possui permissão para se teleportar sem precisar esperar
		if (!p.hasPermission("system.semdelay")) {
			p.sendMessage(Mensagens.Iniciando_Teleporte_Back);
			new BukkitRunnable() {
				@Override
				public void run() {
					p.teleport(l, TeleportCause.COMMAND);
					p.sendMessage(Mensagens.Back_Teleportado_Sucesso);
				}
			}.runTaskLater(Main.get(), 20L * Settings.Delay_Para_Teleportar_Comandos);
			return true;
		}
			
		// Caso o player possui a permissão para se teleportar sem delay o código acima é ignorado
		p.teleport(l, TeleportCause.COMMAND);
		p.sendMessage(Mensagens.Back_Teleportado_Sucesso);
		return true;
	}
}
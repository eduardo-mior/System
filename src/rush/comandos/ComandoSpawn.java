package rush.comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import rush.Main;
import rush.configuracoes.Locations;
import rush.configuracoes.Mensagens;
import rush.configuracoes.Settings;

public class ComandoSpawn implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lb, String[] args) {
		if (cmd.getName().equalsIgnoreCase("spawn")) {
			
			// Verificando se o sender é um player
			if (!(s instanceof Player)) {
				s.sendMessage(Mensagens.Console_Nao_Pode); 
				return false; 
			}
		       
			// Pegando o player e verificando se ele possui permissão para se teleportar sem precisar esperar
			Player p = (Player) s;
			if (!s.hasPermission("system.semdelay")) {
				s.sendMessage(Mensagens.Iniciando_Teleporte_Spawn.replace("%tempo%", String.valueOf(Settings.Delay_Para_Teleportar_Comandos)));
				new BukkitRunnable() {
					@Override
					public void run() {
						p.teleport(Locations.spawn);
						s.sendMessage(Mensagens.Teleportado_Com_Sucesso_Spawn);
					}
				}.runTaskLater(Main.aqui, 20 * Settings.Delay_Para_Teleportar_Comandos);
				return false;
			}
			
			// Caso o player possui a permissão para se teleportar sem delay o código acima é ignorado
			s.sendMessage(Mensagens.Teleportado_Com_Sucesso_Spawn);
			p.teleport(Locations.spawn);
		}
		return false;
	}
}

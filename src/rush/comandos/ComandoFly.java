package rush.comandos;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rush.configuracoes.Mensagens;
import rush.configuracoes.Settings;

@SuppressWarnings("all")
public class ComandoFly implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("fly")) {
			
			// Verificando se o sender é um player
			if (!(s instanceof Player)) {
				
				// Verificando se o sender digitou o número de argumentos correto
				if (args.length > 2 || args.length < 1) {
					s.sendMessage(Mensagens.Fly_Comando_Incorreto);
					return true;
				}
	    		  
				// Pegando o player e verificando se ele esta online
				Player p = Bukkit.getPlayer(args[0]);
				if (p == null) {
					s.sendMessage(Mensagens.Player_Offline);
					return true;
				}
	    		  				
				// Verificando se o sender informou mais de 1 argumento /fly <player> [on/off]
				if (args.length > 1) {
					
					// Verificando se esse argumento é a palavra 'on'
					if (args[1].equalsIgnoreCase("on")) {
						
						// Verificando se o player já esta com fly ligado, caso contrario o fly é ligado
						if (p.getAllowFlight()) {
							s.sendMessage(Mensagens.Fly_Ja_Habilitado_Outro.replace("%player%", p.getName()));
						} else {
							s.sendMessage(Mensagens.Fly_Habilitado_Outro.replace("%player%", p.getName()));
							p.setAllowFlight(true);
						}
						return true;
					}
		    		  
					// Verificando se esse argumento é a palavra 'off'
					if (args[1].equalsIgnoreCase("off")) {
						
						// Verificando se o player esta esta com o fly ligado, caso contrar é exibida uma mensagem
						if (p.getAllowFlight()) {
							s.sendMessage(Mensagens.Fly_Desabilitado_Outro.replace("%player%", p.getName()));
							p.setAllowFlight(false);
						} else {
							s.sendMessage(Mensagens.Fly_Ja_Desabilitado_Outro.replace("%player%", p.getName()));
						}
						return true;
					}
				}
	    		  
				// Se o player não informar 2 argumentos, ou se o segundo argumento não for 'on' ou 'off'
				if (p.getAllowFlight()) {
					s.sendMessage(Mensagens.Fly_Desabilitado_Outro.replace("%player%", p.getName()));
					p.setAllowFlight(false);
				} else {
					s.sendMessage(Mensagens.Fly_Habilitado_Outro.replace("%player%", p.getName()));
					p.setAllowFlight(true);
				}
				return true;
			}
	    	  
			// Verificando se o player possui permissão para ativar o fly em qualquer lugar
			if (!s.hasPermission("system.fly.staff")) {
				Player p = (Player)s;
				String world = p.getWorld().getName();
				List<String> worlds = Settings.Mundos_Onde_Pode_Usar_Fly;
				
				// Verificando se o mundo onde ele esta pode ser usado fly
				if (!worlds.contains(world)) {
					s.sendMessage(Mensagens.Fly_Desabilitado_Neste_Mundo);
					return true;
				}
			}
						
			// Verificando se o player informou ao menos 1 argumento /fly [on/off/player]
			if (args.length > 0) {
				
				// Verificando se esse argumento é a palavra 'on'
				if (args[0].equalsIgnoreCase("on")) {
					Player p = (Player)s;
					if (p.getAllowFlight()) {
						s.sendMessage(Mensagens.Fly_Ja_Habilitado_Voce);
					} else {
						s.sendMessage(Mensagens.Fly_Habilitado_Voce);
						p.setAllowFlight(true);
					}
					return true;
				}
	    		  
				// Verificando se esse argumento é a palavra 'off'
				if (args[0].equalsIgnoreCase("off")) {
					Player p = (Player)s;
					if (p.getAllowFlight()) {
						s.sendMessage(Mensagens.Fly_Desabilitado_Voce);
						p.setAllowFlight(false);
					} else {
						s.sendMessage(Mensagens.Fly_Ja_Desabilitado_Voce);
					}
					return true;
				}
	    		
				/** Caso o primeiro argumento não for a palavra 'on' ou 'off' entende-se que é o nome de um player */
				
				// Verificando se o player possui permissão para alterar o fly de outros players
				if (s.hasPermission("system.fly.outros")) {
		    		
					// Verificando se o sender digitou o número de argumentos correto
					if (args.length > 2) {
						s.sendMessage(Mensagens.Fly_Comando_Incorreto);
						return true;
					}
					
					// Pegando o player e verificando se ele esta online
					Player p = Bukkit.getPlayer(args[0]);
					if (p == null) {
						s.sendMessage(Mensagens.Player_Offline);
						return true;
					}
					
					// Verificando se o player informou mais de 1 argumento /fly <player> [on/off]
					if (args.length > 1) {
						
						// Verificando se esse argumento é a palavra 'on'
						if (args[1].equalsIgnoreCase("on")) {
							
							// Verificando se o player já esta com fly ligado, caso contrario o fly é ligado
							if (p.getAllowFlight()) {
								s.sendMessage(Mensagens.Fly_Ja_Habilitado_Outro.replace("%player%", p.getName()));
							} else {
								s.sendMessage(Mensagens.Fly_Habilitado_Outro.replace("%player%", p.getName()));
								p.setAllowFlight(true);
							}
							return true;
						}
						
						// Verificando se esse argumento é a palavra 'off'
						if (args[1].equalsIgnoreCase("off")) {
							
							// Verificando se o player esta esta com o fly ligado, caso contrar é exibida uma mensagem
							if (p.getAllowFlight()) {
								s.sendMessage(Mensagens.Fly_Desabilitado_Outro.replace("%player%", p.getName()));
								p.setAllowFlight(false);
							} else {
								s.sendMessage(Mensagens.Fly_Ja_Desabilitado_Outro.replace("%player%", p.getName()));
							}
							return true;
						}
			    		  
						// Se o segundo argumento não for 'on' ou 'off'
						if (p.getAllowFlight()) {
							s.sendMessage(Mensagens.Fly_Desabilitado_Outro.replace("%player%", p.getName()));
							p.setAllowFlight(false);
						} else {
							s.sendMessage(Mensagens.Fly_Habilitado_Outro.replace("%player%", p.getName()));
							p.setAllowFlight(true);
						}
						return true;
					}
		    		  
					// Se o player não informar 2 argumentos, ou se o segundo argumento não for 'on' ou 'off'
					if (p.getAllowFlight()) {
						s.sendMessage(Mensagens.Fly_Desabilitado_Outro.replace("%player%", p.getName()));
						p.setAllowFlight(false);
					} else {
						s.sendMessage(Mensagens.Fly_Habilitado_Outro.replace("%player%", p.getName()));
						p.setAllowFlight(true);
					}
					return true;
				}
				
				// Se o player tem a permissão de staff mas não tem a permissão para alterar o fly de
				// outros players então a mensagem abaixo é exibida, caso contrar segue o código
				if (s.hasPermission("system.fly.staff")) {
					s.sendMessage(Mensagens.Fly_Sem_Permissao_Outro);
					return true;
				}
			}
			
			// Se o player não informar nenhum argumento ou se os argumentos informados pelo player
			// não se encaixar em nenhuma verificação feita no código acima este código sera executado
			Player p = (Player)s;
			if (p.getAllowFlight()) {
				s.sendMessage(Mensagens.Fly_Desabilitado_Voce);
				p.setAllowFlight(false);
			} else {
				s.sendMessage(Mensagens.Fly_Habilitado_Voce);
				p.setAllowFlight(true);
			}
			return true;
		}
	    return false;
	}
}
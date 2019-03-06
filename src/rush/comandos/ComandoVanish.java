package rush.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import rush.apis.OnlinePlayersAPI;
import rush.configuracoes.Mensagens;
import rush.configuracoes.Settings;
import rush.sistemas.comandos.VanishListener;

@SuppressWarnings("all")
public class ComandoVanish implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		
		// Verificando se o sender é um player
		if (!(s instanceof Player)) {
			
			// Verificando se o sender digitou o número de argumentos correto
			if (args.length > 2 || args.length < 1) {
				s.sendMessage(Mensagens.Vanish_Comando_Incorreto);
				return true;
			}
    		  
			// Pegando o player e verificando se ele esta online
			Player p = Bukkit.getPlayer(args[0]);
			if (p == null) {
				s.sendMessage(Mensagens.Player_Offline);
				return true;
			}
    		  				
			// Verificando se o sender informou mais de 1 argumento /vanish <player> [on/off]
			if (args.length > 1) {
			
				// Verificando se esse argumento é a palavra 'on'
				if (args[1].equalsIgnoreCase("on")) {
					
					// Verificando se o player já esta com vanish ligado, caso contrario o vanish é ligado
					if (getVanishMode(p)) {
						s.sendMessage(Mensagens.Vanish_Ja_Habilitado_Outro.replace("%player%", p.getName()));
					} else {
						s.sendMessage(Mensagens.Vanish_Habilitado_Outro.replace("%player%", p.getName()));
						setVanishMode(p, true);
					}
					return true;
				}
	    		  
				// Verificando se esse argumento é a palavra 'off'
				if (args[1].equalsIgnoreCase("off")) {
					
					// Verificando se o player esta esta com o vanish ligado, caso contrar é exibida uma mensagem
					if (getVanishMode(p)) {
						s.sendMessage(Mensagens.Vanish_Desabilitado_Outro.replace("%player%", p.getName()));
						setVanishMode(p, false);
					} else {
						s.sendMessage(Mensagens.Vanish_Ja_Desabilitado_Outro.replace("%player%", p.getName()));
					}
					return true;
				}
			}
    		  
			// Se o player não informar 2 argumentos, ou se o segundo argumento não for 'on' ou 'off'
			if (getVanishMode(p)) {
				s.sendMessage(Mensagens.Vanish_Desabilitado_Outro.replace("%player%", p.getName()));
				setVanishMode(p, false);
			} else {
				s.sendMessage(Mensagens.Vanish_Habilitado_Outro.replace("%player%", p.getName()));
				setVanishMode(p, true);
			}
			return true;
		}
					
		// Verificando se o player informou ao menos 1 argumento /vanish [on/off/player]
		if (args.length > 0) {
			
			// Verificando se esse argumento é a palavra 'on'
			if (args[0].equalsIgnoreCase("on")) {
				Player p = (Player)s;
				if (getVanishMode(p)) {
					s.sendMessage(Mensagens.Vanish_Ja_Habilitado_Voce);
				} else {
					s.sendMessage(Mensagens.Vanish_Habilitado_Voce);
					setVanishMode(p, true);
				}
				return true;
			}
    		  
			// Verificando se esse argumento é a palavra 'off'
			if (args[0].equalsIgnoreCase("off")) {
				Player p = (Player)s;
				if (getVanishMode(p)) {
					s.sendMessage(Mensagens.Vanish_Desabilitado_Voce);
					setVanishMode(p, false);
				} else {
					s.sendMessage(Mensagens.Vanish_Ja_Desabilitado_Voce);
				}
				return true;
			}
    		
			/** Caso o primeiro argumento não for a palavra 'on' ou 'off' entende-se que é o nome de um player */
			
			// Verificando se o player possui permissão para alterar o vanish de outros players
			if (s.hasPermission("system.vanish.outros")) {
	    		
				// Verificando se o sender digitou o número de argumentos correto
				if (args.length > 2) {
					s.sendMessage(Mensagens.Vanish_Comando_Incorreto);
					return true;
				}
				
				// Pegando o player e verificando se ele esta online
				Player p = Bukkit.getPlayer(args[0]);
				if (p == null) {
					s.sendMessage(Mensagens.Player_Offline);
					return true;
				}
				
				// Verificando se o player informou mais de 1 argumento /vanish <player> [on/off]
				if (args.length > 1) {
					
					// Verificando se esse argumento é a palavra 'on'
					if (args[1].equalsIgnoreCase("on")) {
						
						// Verificando se o player já esta com vanish ligado, caso contrario o vanish é ligado
						if (getVanishMode(p)) {
							s.sendMessage(Mensagens.Vanish_Ja_Habilitado_Outro.replace("%player%", p.getName()));
						} else {
							s.sendMessage(Mensagens.Vanish_Habilitado_Outro.replace("%player%", p.getName()));
							setVanishMode(p, true);
						}
						return true;
					}
					
					// Verificando se esse argumento é a palavra 'off'
					if (args[1].equalsIgnoreCase("off")) {
						
						// Verificando se o player esta esta com o vanish ligado, caso contrar é exibida uma mensagem
						if (getVanishMode(p)) {
							s.sendMessage(Mensagens.Vanish_Desabilitado_Outro.replace("%player%", p.getName()));
							setVanishMode(p, false);
						} else {
							s.sendMessage(Mensagens.Vanish_Ja_Desabilitado_Outro.replace("%player%", p.getName()));
						}
						return true;
					}
				}
				  
				// Se o player não informar 2 argumentos, ou se o segundo argumento não for 'on' ou 'off'
				if (getVanishMode(p)) {
					s.sendMessage(Mensagens.Vanish_Desabilitado_Outro.replace("%player%", p.getName()));
					setVanishMode(p, false);
				} else {
					s.sendMessage(Mensagens.Vanish_Habilitado_Outro.replace("%player%", p.getName()));
					setVanishMode(p, true);
				}
				return true;
			}
			
			// Se o player não informar 2 argumentos, ou se o segundo argumento não for 'on' ou 'off'
			s.sendMessage(Mensagens.Vanish_Sem_Permissao_Outro);
			return true;
		}
		
		// Se o player não informar nenhum argumento ou se os argumentos informados pelo player
		// não se encaixar em nenhuma verificação feita no código acima este código sera executado
		Player p = (Player)s;
		if (getVanishMode(p)) {
			s.sendMessage(Mensagens.Vanish_Desabilitado_Voce);
			setVanishMode(p, false);
		} else {
			s.sendMessage(Mensagens.Vanish_Habilitado_Voce);
			setVanishMode(p, true);
		}
		return true;
	}
	
	// Método para setar o vanishMode
	private void setVanishMode(Player player, boolean enabled) {
		
		// Verificando se o vanish esta sendo ativado, se sim ativando
		if (enabled) {
			
			// Adicionado efeito de invisibilidade e adicionando o player na lista
			player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 50), true);
			VanishListener.VANISHEDS.add(player);
			
			// Fazendo um loop por todos os players do server
			for (Player target : OnlinePlayersAPI.getOnlinePlayers()) {
				
				// Verificando se o player não tem permissão de bypass
				if (!target.hasPermission("system.vanish.bypass")) {
					target.hidePlayer(player);
				}
			}
			return;
			
		// Caso contrario desativando o vanish
		} else {
			
			// Removendo efeito de invisibilidade e remove o player da lista
			player.removePotionEffect(PotionEffectType.INVISIBILITY);
			VanishListener.VANISHEDS.remove(player);
			
			// Fazendo um loop por todos os players do servidor e removendo o vanish
			for (Player target : OnlinePlayersAPI.getOnlinePlayers()) {
				target.showPlayer(player);
			}
		}
	}

	// Método para pegar o vanishMode
	private boolean getVanishMode(Player player) {
		return VanishListener.VANISHEDS.contains(player);
	}
	
}
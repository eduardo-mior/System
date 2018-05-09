package rush.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import rush.utils.ConfigManager;

public class ComandoFly implements Listener, CommandExecutor {
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender s, Command cmd, String commandlabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("fly")) {
			if (!(s instanceof Player)) {
				if(args.length > 2 || args.length < 1) {
					s.sendMessage(ConfigManager.getConfig("mensagens").getString("Fly-Comando-Incorreto").replaceAll("&", "§"));
		    		return false;
				}
	    		  
				Player p = Bukkit.getPlayer(args[0]);
				if (p == null) {
					s.sendMessage(ConfigManager.getConfig("mensagens").getString("Player-Offline").replaceAll("&", "§"));
					return false;
				}
	    		  
				boolean fly = p.getAllowFlight();
				if (args.length > 1) {
					if (args[1].equalsIgnoreCase("on")) {
						if (fly) {
							s.sendMessage(ConfigManager.getConfig("mensagens").getString("Fly-Ja-Habilitado-Outro").replace("&", "§").replace("%player%", p.getName()));
						} else {
							s.sendMessage(ConfigManager.getConfig("mensagens").getString("Fly-Habilitado-Outro").replace("&", "§").replace("%player%", p.getName()));
							p.setAllowFlight(true);
						}
						return false;
					}
		    		  
					if (args[1].equalsIgnoreCase("off")) {
						if (fly) {
							s.sendMessage(ConfigManager.getConfig("mensagens").getString("Fly-Desabilitado-Outro").replace("&", "§").replace("%player%", p.getName()));
							p.setAllowFlight(false);
						} else {
							s.sendMessage(ConfigManager.getConfig("mensagens").getString("Fly-Ja-Desabilitado-Outro").replace("&", "§").replace("%player%", p.getName()));
						}
						return false;
					}
				}
	    		  
				if (fly) {
					s.sendMessage(ConfigManager.getConfig("mensagens").getString("Fly-Desabilitado-Outro").replace("&", "§").replace("%player%", p.getName()));
					p.setAllowFlight(false);
				} else {
					s.sendMessage(ConfigManager.getConfig("mensagens").getString("Fly-Habilitado-Outro").replace("&", "§").replace("%player%", p.getName()));
					p.setAllowFlight(true);
				}
				return false;
			}
	    	  
			if (!s.hasPermission("system.fly")) {
				s.sendMessage(ConfigManager.getConfig("mensagens").getString("Sem-Permissao").replace("&", "§"));
				return false;
			}
			
			if (args.length > 2) {
				s.sendMessage(ConfigManager.getConfig("mensagens").getString("Fly-Comando-Incorreto").replaceAll("&", "§"));
				return false;
			}
	    	  
			if (args.length == 0) {
				Player p = (Player)s;
				boolean fly = p.getAllowFlight();
				if (fly) {
					s.sendMessage(ConfigManager.getConfig("mensagens").getString("Fly-Desabilitado-Voce").replace("&", "§"));
					p.setAllowFlight(false);
				} else {
					s.sendMessage(ConfigManager.getConfig("mensagens").getString("Fly-Habilitado-Voce").replace("&", "§"));
					p.setAllowFlight(true);
				}
				return false;
			}
	    	  
			if (args.length > 0) {
				if (args[0].equalsIgnoreCase("on")) {
					Player p = (Player)s;
					boolean fly = p.getAllowFlight();
					if (fly) {
						s.sendMessage(ConfigManager.getConfig("mensagens").getString("Fly-Ja-Habilitado-Voce").replace("&", "§"));
					} else {
						s.sendMessage(ConfigManager.getConfig("mensagens").getString("Fly-Habilitado-Voce").replace("&", "§"));
						p.setAllowFlight(true);
					}
					return false;
				}
	    		  
				if (args[0].equalsIgnoreCase("off")) {
					Player p = (Player)s;
					boolean fly = p.getAllowFlight();
					if (fly) {
						s.sendMessage(ConfigManager.getConfig("mensagens").getString("Fly-Desabilitado-Voce").replace("&", "§"));
						p.setAllowFlight(false);
					} else {
						s.sendMessage(ConfigManager.getConfig("mensagens").getString("Fly-Ja-Desabilitado-Voce").replace("&", "§"));
					}
					return false;
				}
	    		  
				if (!s.hasPermission("system.fly.outros")) {
					s.sendMessage(ConfigManager.getConfig("mensagens").getString("Fly-Outro-Sem-Permissao").replace("&", "§"));
					return false;
				}
	    		  
				Player p = Bukkit.getPlayer(args[0]);
				if (p == null) {
					s.sendMessage(ConfigManager.getConfig("mensagens").getString("Player-Offline").replaceAll("&", "§"));
					return false;
				}
	    		  
				boolean fly = p.getAllowFlight();
				if (args.length > 1) {
					if (args[1].equalsIgnoreCase("on")) {
						if (fly) {
							s.sendMessage(ConfigManager.getConfig("mensagens").getString("Fly-Ja-Habilitado-Outro").replace("&", "§").replace("%player%", p.getName()));
						} else {
							s.sendMessage(ConfigManager.getConfig("mensagens").getString("Fly-Habilitado-Outro").replace("&", "§").replace("%player%", p.getName()));
							p.setAllowFlight(true);
						}
						return false;
					}
					
					if (args[1].equalsIgnoreCase("off")) {
						if (fly) {
							s.sendMessage(ConfigManager.getConfig("mensagens").getString("Fly-Desabilitado-Outro").replace("&", "§").replace("%player%", p.getName()));
							p.setAllowFlight(false);
						} else {
							s.sendMessage(ConfigManager.getConfig("mensagens").getString("Fly-Ja-Desabilitado-Outro").replace("&", "§").replace("%player%", p.getName()));
						}
						return false;
					}
				}
	    		  
				if (fly) {
					s.sendMessage(ConfigManager.getConfig("mensagens").getString("Fly-Desabilitado-Outro").replace("&", "§").replace("%player%", p.getName()));
					p.setAllowFlight(false);
				} else {
					s.sendMessage(ConfigManager.getConfig("mensagens").getString("Fly-Habilitado-Outro").replace("&", "§").replace("%player%", p.getName()));
					p.setAllowFlight(true);
				}
				return false;
			}
		}
	    return false;
	}
}
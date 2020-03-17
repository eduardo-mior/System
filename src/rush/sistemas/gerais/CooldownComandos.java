package rush.sistemas.gerais;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import rush.configuracoes.Mensagens;
import rush.configuracoes.Settings;
import rush.utils.TimeFormatter;

public class CooldownComandos implements Listener {

	public static HashMap<String, HashMap<String, Long>> cooldownlist = new HashMap<String, HashMap<String, Long>>();
	
	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
	public void aoExecutarComando(PlayerCommandPreprocessEvent e) {
		String cmd = e.getMessage().toLowerCase().split(" ")[0];
		for (Entry<String, Long> cooldownCmd : Settings.Lista_Dos_Comandos_Com_Cooldown.entrySet()) {
			if (cooldownCmd.getKey().equals(cmd) || (cmd.split(":").length > 1 && cooldownCmd.getKey().equals("/" + cmd.split(":")[1]))) {
				Player p = e.getPlayer();
								
				// Caso o player ainda não esteja na lista
				if (!cooldownlist.containsKey(p.getName())) {
					HashMap<String, Long> playerCooldown = new HashMap<>();
					playerCooldown.put(cooldownCmd.getKey(), System.currentTimeMillis() + (1000L * cooldownCmd.getValue()));
					cooldownlist.put(p.getName(), playerCooldown);
					return;
				}
				
				// Caso o comando ainda não esteja na lista do player
				if (!cooldownlist.get(p.getName()).containsKey(cooldownCmd.getKey())) {
					HashMap<String, Long> playerCooldown = cooldownlist.get(p.getName());
					playerCooldown.put(cooldownCmd.getKey(), System.currentTimeMillis() + (1000L * cooldownCmd.getValue()));
					cooldownlist.replace(p.getName(), playerCooldown);
					return;
				}
				
				// Caso o player não tenha perm de bypass e caso ainda não tenha esperado o cooldown
				if (!(p.hasPermission("system.bypass.comandocooldown")) && System.currentTimeMillis() < cooldownlist.get(p.getName()).get(cooldownCmd.getKey())) {
					long millis = cooldownlist.get(p.getName()).get(cooldownCmd.getKey()) - System.currentTimeMillis();
					String tempo = TimeFormatter.format(millis);
					p.sendMessage(Mensagens.Aguarde_Cooldown_Comandos.replace("%tempo%", tempo).replace("%cmd%", cooldownCmd.getKey()));
					e.setCancelled(true);
					return;
				// Caso já tenha passado o tempo do delay...
				} else {
					HashMap<String, Long> playerCooldown = cooldownlist.get(p.getName());
					playerCooldown.put(cooldownCmd.getKey(), System.currentTimeMillis() + (1000L * cooldownCmd.getValue()));
					cooldownlist.replace(p.getName(), playerCooldown);
				}
			}
		}
	}
	
}
package rush.recursos.adicionais;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import rush.Main;

public class BloquearPlacas implements Listener {

	@EventHandler
	public void aoUsarPlaca(SignChangeEvent e) {
		Player p = e.getPlayer();
		if (!(p.hasPermission("system.bypass.placabloqueada"))) {
			if (Main.aqui.getConfig().getBoolean("Bloquear-Todas-As-Palavras")) {
				e.setCancelled(true);
				e.getPlayer().sendMessage(Main.aqui.getMensagens().getString("Placas.Palavras-Desativadas-Na-Placa").replaceAll("&", "§"));
				}
		
			if (Main.aqui.getConfig().getBoolean("Bloquear-Apenas-Algumas-Palavras")) {
				String[] linhas;
				int j = (linhas = e.getLines()).length;
				for (int i = 0; i < j; i++) {
					String s = linhas[i];
					for (String palavra : Main.aqui.getConfig().getStringList("Lista-Das-Palavras-Bloqueadas")) {
						if (s.toLowerCase().contains(palavra.toLowerCase()))  {
							e.setCancelled(true);
							e.getPlayer().sendMessage((Main.aqui.getMensagens().getString("Placas.Palavra-Bloqueada-Na-Placa")).replaceAll("%palavra%", palavra).replaceAll("&", "§"));
							e.getBlock().breakNaturally();
							return;
						}
					}
				}
			}
		}
	}
}
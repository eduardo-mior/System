package rush.addons;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.permissions.PermissionAttachmentInfo;

import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;
import rush.configuracoes.Settings;

public class LegendChat implements Listener {

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void aoEnviarMenssagem(ChatMessageEvent e) {
    	Player p = e.getSender();
       
    	if (p.hasPermission("system.chat.destaque")) {
    		e.setFormat(" \n" + e.getFormat() + "\n ");
    	}
       
    	String colorId = getChatColorIdByPerm(p);
    	if (colorId != null && Settings.CorAutomatica.containsKey(colorId)) {	
    		String color = Settings.CorAutomatica.get(colorId);
    		e.setMessage(color + e.getMessage()); 
    	}
    }
	
	private String getChatColorIdByPerm(Player p) {
		
		// Percorrendo todas as cores e verificando se o player tem permissão de alguma
		for (String perm : Settings.CorAutomatica.keySet()) {
			if (p.hasPermission("system.chat.cor." + perm)) {
				return perm;
			}
		}
		
		// Percorrendo todas as permissões do player e verificando se ele possui alguma cor
		for (PermissionAttachmentInfo perm : p.getEffectivePermissions()) {
			if (perm.getPermission().startsWith("system.chat.cor.")) {
				return perm.getPermission().replace("system.chat.cor.", "");
			}
		}
		
		// Caso não tenha nada retorna nulo
		return null;
	}
    
}
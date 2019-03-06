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
		for (PermissionAttachmentInfo perm : p.getEffectivePermissions()) {
			if (perm.getPermission().startsWith("system.chat.cor.")) {
				return perm.getPermission().replace("system.chat.cor.", "");
			}
		}
		return null;
	}
    
}
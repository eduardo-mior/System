package rush.addons;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;
import rush.configuracoes.Settings;

public class LegendChat implements Listener {

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void aoEnviarMenssagem(ChatMessageEvent e) {
    	Player p = e.getSender();
       
    	if (p.hasPermission("system.chat.destaque")) {
    		e.setFormat(" \n" + e.getFormat() + "\n ");
    	}
       
    	String perm = getChatColor(p);
    	if (perm != null && Settings.CorAutomatica.containsKey(perm)) {	
    		String color = (String) Settings.CorAutomatica.get(perm);
    		e.setMessage(color + e.getMessage() ); 
    	}
    }
    
	/**
	 * Powered by kickpost;
	 */
	
    private String getChatColor(Player p) {
    	try {
    		return  p.getEffectivePermissions().stream()
    			   .filter(r -> r.getPermission().toLowerCase().startsWith("system.chat.cor."))
    			   .findFirst().get().getPermission().replace("system.chat.cor.", "").trim();
    	} catch (Throwable e) {
			return null;
		}
	}
}

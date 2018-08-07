package rush.addons;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import br.com.devpaulo.legendchat.api.Legendchat;
import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;
import br.com.devpaulo.legendchat.channels.types.Channel;
import rush.configuracoes.Settings;

public class LegendChat implements Listener {

    @EventHandler
    public void aoEnviarMenssagem(ChatMessageEvent e) {
       Player p = e.getSender();
       String msg = e.getMessage();
       Channel c = Legendchat.getChannelManager().getChannelByName("global");
       
       if (p.hasPermission("system.chat.destaque") && e.getChannel() == c) {
          e.setFormat("\n§6" + e.getFormat() + "\n§6 "); }
       
       if (p.hasPermission("system.chat.cor1")) {
           e.setMessage(Settings.CorAutomatica_Cor1 + msg ); 
       
       } else if (p.hasPermission("system.chat.cor2")) {
           e.setMessage(Settings.CorAutomatica_Cor2 + msg ); 
       
       } else if (p.hasPermission("system.chat.cor3")) {
           e.setMessage(Settings.CorAutomatica_Cor3 + msg ); 
       
       } else if (p.hasPermission("system.chat.cor4")) {
           e.setMessage(Settings.CorAutomatica_Cor4 + msg ); 
       
       } else if (p.hasPermission("system.chat.cor5")) {
           e.setMessage(Settings.CorAutomatica_Cor5 + msg ); 
       
       } else if (p.hasPermission("system.chat.cor6")) {
           e.setMessage(Settings.CorAutomatica_Cor6 + msg ); 
       
       } else if (p.hasPermission("system.chat.cor7")) {
           e.setMessage(Settings.CorAutomatica_Cor7 + msg ); 
       
       } else if (p.hasPermission("system.chat.cor8")) {
           e.setMessage(Settings.CorAutomatica_Cor8 + msg ); 
       
       } else if (p.hasPermission("system.chat.cor9")) {
           e.setMessage(Settings.CorAutomatica_Cor9 + msg ); 
       
       } else if (p.hasPermission("system.chat.cor10")) {
           e.setMessage(Settings.CorAutomatica_Cor10 + msg ); 
       }
    }
}

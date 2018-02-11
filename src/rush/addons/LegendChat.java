package rush.addons;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import br.com.devpaulo.legendchat.api.Legendchat;
import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;
import br.com.devpaulo.legendchat.channels.types.Channel;
import rush.Main;

public class LegendChat implements Listener {

    @EventHandler
    public void aoEnviarMenssagem(ChatMessageEvent e) {
       Player p = e.getSender();
       String msg = e.getMessage();
       Legendchat.getMessageManager();
       Channel c = Legendchat.getChannelManager().getChannelByName("global");
       
       if (p.hasPermission("system.destaquechat") && e.getChannel() == c) {
          e.setFormat("\n§6" + e.getFormat() + "\n§6 "); }
       
       if (p.hasPermission("system.cor1")) {
           e.setMessage(Main.aqui.getConfig().getString("CorAutomatica.Cor1") + msg ); }
       
       if (p.hasPermission("system.cor2")) {
           e.setMessage(Main.aqui.getConfig().getString("CorAutomatica.Cor2") + msg ); }
       
       if (p.hasPermission("system.cor3")) {
           e.setMessage(Main.aqui.getConfig().getString("CorAutomatica.Cor3") + msg ); }
       
       if (p.hasPermission("system.cor4")) {
           e.setMessage(Main.aqui.getConfig().getString("CorAutomatica.Cor4") + msg ); }
       
       if (p.hasPermission("system.cor5")) {
           e.setMessage(Main.aqui.getConfig().getString("CorAutomatica.Cor5") + msg ); }
       
       if (p.hasPermission("system.cor6")) {
           e.setMessage(Main.aqui.getConfig().getString("CorAutomatica.Cor6") + msg ); }
       
       if (p.hasPermission("system.cor7")) {
           e.setMessage(Main.aqui.getConfig().getString("CorAutomatica.Cor7") + msg ); }
       
       if (p.hasPermission("system.cor8")) {
           e.setMessage(Main.aqui.getConfig().getString("CorAutomatica.Cor8") + msg ); }
       
       if (p.hasPermission("system.cor9")) {
           e.setMessage(Main.aqui.getConfig().getString("CorAutomatica.Cor9") + msg ); }
       
       if (p.hasPermission("system.cor10")) {
           e.setMessage(Main.aqui.getConfig().getString("CorAutomatica.Cor10") + msg ); }
    }
}

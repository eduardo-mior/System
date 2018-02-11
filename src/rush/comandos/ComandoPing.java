package rush.comandos;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import rush.Main;

public class ComandoPing implements Listener, CommandExecutor {
	
    public boolean onCommand(final CommandSender s, Command cmd, String lbl, String[] args) {
 	     if (cmd.getName().equalsIgnoreCase("ping"))
      	 try {
             String name = args.length == 1 ? args[0] : (s instanceof Player ? ((Player)s).getName() : null);
             if (!(s instanceof Player)) {
   	          s.sendMessage(Main.aqui.getMensagens().getString("Console-Nao-Pode").replaceAll("&", "§"));
   	          return true;}
             
             @SuppressWarnings("deprecation")
             Player player = Bukkit.getServer().getPlayer(name);
             if (player == null) {
                s.sendMessage(String.format(Main.aqui.getMensagens().getString("Player-Offline").replaceAll("&", "§")));
                return true;
             }

             Method player_getHandle = player.getClass().getMethod("getHandle");
             Object player_MC = player_getHandle.invoke(player, (Object[])null);
             Field player_ping = player_MC.getClass().getField("ping");
             s.sendMessage(String.format((args.length == 0 ? "§aSeu ping é " : String.format("§aO ping de %s ", player.getName())) + "§a%dms", Integer.valueOf(String.valueOf(player_ping.get(player_MC)))));
          } catch (Exception var10) {
             var10.printStackTrace();
          }
          return true;
       }
}

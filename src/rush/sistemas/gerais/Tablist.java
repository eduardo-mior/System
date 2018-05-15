package rush.sistemas.gerais;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import rush.utils.ConfigManager;

public class Tablist implements Listener {
	
   String header = ConfigManager.getConfig("settings").getString("Parte-De-Cima").replace("&", "§");
   String footer = ConfigManager.getConfig("settings").getString("Parte-De-Baixo").replace("&", "§");
	
   public static void sendTabList(Player player, String header, String footer) {
	   try {
		   Constructor<?> titelConstructor = getNMSClass("PacketPlayOutPlayerListHeaderFooter").getConstructor(getNMSClass("IChatBaseComponent"));
    	  
		   Object tabHeader = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + header + "\"}");
		   Object tabFooter = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + footer + "\"}");
		   Object packet = titelConstructor.newInstance(tabHeader);
		   Field field = packet.getClass().getDeclaredField("b");
		   field.setAccessible(true);
		   field.set(packet, tabFooter);
		   sendPacket(player, packet);
	   } catch (NoSuchMethodException | IllegalAccessException | NoSuchFieldException | InstantiationException | InvocationTargetException | SecurityException var8) {
		   var8.printStackTrace();
	   }
   	}

   	public static Class<?> getNMSClass(String name) {
   		String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
   		try {
   			return Class.forName("net.minecraft.server." + version + "." + name);
   		} catch (ClassNotFoundException var3) {
   			var3.printStackTrace();
   			return null;
   		}
   	}

   	public static void sendPacket(Player player, Object packet) {
   		try {
   			Object handle = player.getClass().getMethod("getHandle").invoke(player);
   			Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
   			playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);
   		} catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | NoSuchFieldException var4) {
   			var4.printStackTrace();
   		}
   	}
   
   	@EventHandler
   	public void aoEntrar(PlayerJoinEvent e) {
   		Player p = e.getPlayer();
   		sendTabList(p, header, footer);	   
   	}
}
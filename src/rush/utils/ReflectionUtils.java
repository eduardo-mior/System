package rush.utils;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * @author Mior
 * @version 1.0
 * @category utils
 */

public class ReflectionUtils {
	
   	public static Class<?> getNMSClass(String name) {
   		String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
   		try {
   			return Class.forName("net.minecraft.server." + version + "." + name);
   		} catch (ClassNotFoundException var3) {
   			var3.printStackTrace();
   			return null;
   		}
   	}
   	
   	public static Class<?> getOBClass(String name) {
   		String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
   		try {
   			return Class.forName("org.bukkit.craftbukkit." + version + "." + name);
   		} catch (ClassNotFoundException var3) {
   			var3.printStackTrace();
   			return null;
   		}
   	}
   	
   	public static void sendPacket(Player player, Object packet) {
   		try {
   			Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
   			Object playerConnection = entityPlayer.getClass().getField("playerConnection").get(entityPlayer);
   			playerConnection.getClass().getMethod("sendPacket", ReflectionUtils.getNMSClass("Packet")).invoke(playerConnection, packet);
   		} catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | NoSuchFieldException var4) {
   			var4.printStackTrace();
   		}
   	}
}

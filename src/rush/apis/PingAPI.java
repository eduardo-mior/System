package rush.apis;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.entity.Player;

import rush.utils.ReflectionUtils;

public class PingAPI {

	private static Method getHandle;
	private static Field ping;
	
	public static String getPlayerPing(Player player) {
		try {
			Object entityPlayer = getHandle.invoke(player);
			return String.valueOf(ping.get(entityPlayer));
		} catch (Throwable e) {
			return "Indisponivel";
		}
	}
	
	static void load() {
		try 
		{
			Class<?> craftPlayerClass = ReflectionUtils.getOBClass("entity.CraftPlayer");
			Class<?> entityPlayerClass = ReflectionUtils.getNMSClass("EntityPlayer");
			getHandle = craftPlayerClass.getMethod("getHandle");
			ping = entityPlayerClass.getField("ping"); 
		}
		catch (Throwable e) {}
	}
	
}
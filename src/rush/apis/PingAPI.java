package rush.apis;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.entity.Player;

import rush.Main;
import rush.utils.ReflectionUtils;

public class PingAPI {

	private static Method getHandle;
	private static Field ping;
	
	public static String getPlayerPing(Player player) {
		try {
			
			if (Main.getVersion().value < 17) {
				Object entityPlayer = getHandle.invoke(player);
				return String.valueOf(ping.get(entityPlayer));				
			} else {
				return String.valueOf((int) player.getClass().getMethod("getPing").invoke(player));
			}
			
		} catch (Throwable e) {
			return "Indisponivel";
		}
	}
	
	static void load() {
		try 
		{
			if (Main.getVersion().value < 17) {				
				Class<?> craftPlayerClass = ReflectionUtils.getOBClass("entity.CraftPlayer");
				Class<?> entityPlayerClass = ReflectionUtils.getNMSClass("EntityPlayer");
				getHandle = craftPlayerClass.getMethod("getHandle");
				ping = entityPlayerClass.getField("ping"); 
			}
		}
		catch (Throwable e) {}
	}
	
}
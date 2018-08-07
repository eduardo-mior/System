package rush.api;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import rush.utils.ReflectionUtils;

public class ActionBarAPI implements Listener {
    
	public static void sendActionBar(Player player, String message) {
		try {
			Constructor<?> constructor = ReflectionUtils.getNMSClass("PacketPlayOutChat").getConstructor(ReflectionUtils.getNMSClass("IChatBaseComponent"), byte.class);
			Object icbc = ReflectionUtils.getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + message + "\"}");
		    Object packet = constructor.newInstance(icbc, (byte) 2);
		    ReflectionUtils.sendPacket(player, packet);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException e) {
			e.printStackTrace();
		}
	}
}
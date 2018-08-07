package rush.api;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.bukkit.entity.Player;

import rush.utils.ReflectionUtils;

public class TablistAPI {

	public static void sendTabList(Player player, String header, String footer) {
		try {
			Constructor<?> titleConstructor = ReflectionUtils.getNMSClass("PacketPlayOutPlayerListHeaderFooter").getConstructor(ReflectionUtils.getNMSClass("IChatBaseComponent"));
			Object tabHeader = ReflectionUtils.getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + header + "\"}");
			Object tabFooter = ReflectionUtils.getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + footer + "\"}");
			Object packet = titleConstructor.newInstance(tabHeader);
			Field field = packet.getClass().getDeclaredField("b");
			field.setAccessible(true);
			field.set(packet, tabFooter);
			ReflectionUtils.sendPacket(player, packet);
		} catch (NoSuchMethodException | IllegalAccessException | NoSuchFieldException | InstantiationException | InvocationTargetException | SecurityException var8) {
			var8.printStackTrace();
		}
	}	
}

package rush.apis;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.bukkit.entity.Player;

import rush.utils.ReflectionUtils;

public class TablistAPI {

	public static void sendTabList(Player player, String header, String footer) {
		try {
			Class<?> icbc = ReflectionUtils.getNMSClass("IChatBaseComponent");
			
			Object packet = ReflectionUtils.getNMSClass("PacketPlayOutPlayerListHeaderFooter").newInstance();
			Object tabHeader = icbc.getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + header + "\"}");
			Object tabFooter = icbc.getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + footer + "\"}");
			
			Field headerField = packet.getClass().getDeclaredField("a");
			headerField.setAccessible(true);
			headerField.set(packet, tabHeader);
	        
			Field footerField = packet.getClass().getDeclaredField("b");
			footerField.setAccessible(true);
			footerField.set(packet, tabFooter);
	        
			ReflectionUtils.sendPacket(player, packet);
		} catch (NoSuchMethodException | IllegalAccessException | NoSuchFieldException | InstantiationException | InvocationTargetException | SecurityException e) {
			e.printStackTrace();
		}
	}
}

package rush.apis;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.entity.Player;

import rush.utils.ReflectionUtils;

public class TablistAPI {

	private static Class<?> ppop;
	private static Method a;
	private static int h;
	private static int f;

	public static void sendTabList(Player player, String header, String footer) {
		try {

			Object tabHeader = a.invoke(null, "{\"text\":\"" + header + "\"}");
			Object tabFooter = a.invoke(null, "{\"text\":\"" + footer + "\"}");

			Object packet = ppop.newInstance();

			Field headerField = ppop.getDeclaredFields()[h];
			headerField.setAccessible(true);
			headerField.set(packet, tabHeader);

			Field footerField = ppop.getDeclaredFields()[f];
			footerField.setAccessible(true);
			footerField.set(packet, tabFooter);

			ReflectionUtils.sendPacket(player, packet);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void load() {
		try 
		{
			Class<?> icbc = ReflectionUtils.getNMSClass("IChatBaseComponent");
			ppop = ReflectionUtils.getNMSClass("PacketPlayOutPlayerListHeaderFooter");

			if (icbc.getDeclaredClasses().length > 0) {
				a = icbc.getDeclaredClasses()[0].getMethod("a", String.class);
			} else {
				a = ReflectionUtils.getNMSClass("ChatSerializer").getMethod("a", String.class);
			}

			if (ppop.getDeclaredFields().length > 2) {
				h = 2;
				f = 3;
			} else {
				h = 0;
				f = 1;
			}
		} catch (Exception e) {}
	}
}

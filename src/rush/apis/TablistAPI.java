package rush.apis;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.entity.Player;

import rush.utils.ReflectionUtils;

public class TablistAPI {

	private static Class<?> ppop;
	private static Method a;
	private static Field headerField;
	private static Field footerField;

	public static void sendTabList(Player player, String header, String footer) {
		try 
		{
			Object tabHeader = a.invoke(null, "{\"text\":\"" + header + "\"}");
			Object tabFooter = a.invoke(null, "{\"text\":\"" + footer + "\"}");
			Object packet = ppop.newInstance();

			headerField.set(packet, tabHeader);
			footerField.set(packet, tabFooter);

			ReflectionUtils.sendPacket(player, packet);
		} 
		catch (Throwable e) {
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

			int h, f;
			if (ppop.getDeclaredFields().length > 2) {
				h = 2;
				f = 3;
			} else {
				h = 0;
				f = 1;
			}

			headerField = ppop.getDeclaredFields()[h];
			footerField = ppop.getDeclaredFields()[f];
			headerField.setAccessible(true);
			footerField.setAccessible(true);
		} 
		catch (Throwable e) {}
	}
}
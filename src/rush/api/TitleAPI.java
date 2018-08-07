package rush.api;

import java.lang.reflect.Constructor;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import rush.utils.ReflectionUtils;

public class TitleAPI extends JavaPlugin implements Listener {
	
	public static void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle) {
		try {
        	Class<?> icbc = ReflectionUtils.getNMSClass("IChatBaseComponent");
        	Class<?> ppot = ReflectionUtils.getNMSClass("PacketPlayOutTitle");

			Object enumTIMES = ppot.getDeclaredClasses()[0].getField("TIMES").get(null);
			Constructor<?> timeTitleConstructor = ppot.getConstructor(ppot.getDeclaredClasses()[0], icbc, Integer.TYPE, Integer.TYPE, Integer.TYPE);
			Object timeTitlePacket = timeTitleConstructor.newInstance(enumTIMES, null, fadeIn, stay, fadeOut);
			ReflectionUtils.sendPacket(player, timeTitlePacket);

			Object enumTITLE = ppot.getDeclaredClasses()[0].getField("TITLE").get(null);
			Object chatTitle = icbc.getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + title + "\"}");
			Constructor<?> titleConstructor = ppot.getConstructor(ppot.getDeclaredClasses()[0], icbc);
			Object titlePacket = titleConstructor.newInstance(enumTITLE, chatTitle);
			ReflectionUtils.sendPacket(player, titlePacket);

			Object enumSUBTITLE = ppot.getDeclaredClasses()[0].getField("SUBTITLE").get(null);
			Object  chatSubtitle = icbc.getDeclaredClasses()[0].getMethod("a", String.class).invoke(null,"{\"text\":\"" + subtitle + "\"}");
			Constructor<?> subtitleConstructor = ppot.getConstructor(ppot.getDeclaredClasses()[0], icbc);
			Object subtitlePacket = subtitleConstructor.newInstance(enumSUBTITLE, chatSubtitle);
			ReflectionUtils.sendPacket(player, subtitlePacket);

		} catch (Exception var11) {
			var11.printStackTrace();
		}
	}
}
package rush.apis;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import rush.Main;
import rush.utils.ReflectionUtils;

public class TitleAPI {
	
	private static Method a;
	private static Object enumTIMES;
	private static Object enumTITLE;
	private static Object enumSUBTITLE;
	private static Constructor<?> timeTitleConstructor;
	private static Constructor<?> textTitleConstructor;
	
	public static void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle) {
		try {
			
			if (Main.getVersion().value < 17) {		
				
				Object chatTitle = a.invoke(null, "{\"text\":\"" + title + "\"}");
				Object chatSubtitle = a.invoke(null,"{\"text\":\"" + subtitle + "\"}");
				
				Object timeTitlePacket = timeTitleConstructor.newInstance(enumTIMES, null, fadeIn, stay, fadeOut);
				Object titlePacket = textTitleConstructor.newInstance(enumTITLE, chatTitle);
				Object subtitlePacket = textTitleConstructor.newInstance(enumSUBTITLE, chatSubtitle);
				
				ReflectionUtils.sendPacket(player, timeTitlePacket);
				ReflectionUtils.sendPacket(player, titlePacket);
				ReflectionUtils.sendPacket(player, subtitlePacket);
				
			} else {
				
				Class<?> playerClass = player.getClass();
				Method sendTitle = playerClass.getMethod("sendTitle", String.class, String.class, int.class, int.class, int.class);
				sendTitle.invoke(player,  title, subtitle, fadeIn, stay, fadeOut);
			
			}
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public static void broadcastTitle(Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle) {
		try 
		{
			
			if (Main.getVersion().value < 17) {				
				Object chatTitle = a.invoke(null, "{\"text\":\"" + title + "\"}");
				Object chatSubtitle = a.invoke(null,"{\"text\":\"" + subtitle + "\"}");
				
				Object timeTitlePacket = timeTitleConstructor.newInstance(enumTIMES, null, fadeIn, stay, fadeOut);
				Object titlePacket = textTitleConstructor.newInstance(enumTITLE, chatTitle);
				Object subtitlePacket = textTitleConstructor.newInstance(enumSUBTITLE, chatSubtitle);
				
				for (Player player : Bukkit.getOnlinePlayers()) {
					ReflectionUtils.sendPacket(player, timeTitlePacket);
					ReflectionUtils.sendPacket(player, titlePacket);
					ReflectionUtils.sendPacket(player, subtitlePacket);
				}
				
			} else {
				
				for (Player player : Bukkit.getOnlinePlayers()) {
					Class<?> playerClass = player.getClass();
					Method sendTitle = playerClass.getMethod("sendTitle", String.class, String.class, int.class, int.class, int.class);
					sendTitle.invoke(player,  title, subtitle, fadeIn, stay, fadeOut);
				}
				
			}
			
		} 
		catch (Throwable e) {
			e.printStackTrace();
		}
	}
		
	static void load() {
		try 
		{
			if (Main.getVersion().value < 17) {				
				Class<?> icbc = ReflectionUtils.getNMSClass("IChatBaseComponent");
				Class<?> ppot = ReflectionUtils.getNMSClass("PacketPlayOutTitle");
				Class<?> enumClass;
				
				if (ppot.getDeclaredClasses().length > 0) {
					enumClass = ppot.getDeclaredClasses()[0];
				} else {
					enumClass = ReflectionUtils.getNMSClass("EnumTitleAction");
				}
				
				if (icbc.getDeclaredClasses().length > 0) {
					a = icbc.getDeclaredClasses()[0].getMethod("a", String.class);
				} else {
					a = ReflectionUtils.getNMSClass("ChatSerializer").getMethod("a", String.class);
				}
				
				enumTIMES = enumClass.getField("TIMES").get(null);
				enumTITLE = enumClass.getField("TITLE").get(null);
				enumSUBTITLE = enumClass.getField("SUBTITLE").get(null);
				timeTitleConstructor = ppot.getConstructor(enumClass, icbc, int.class, int.class, int.class);
				textTitleConstructor = ppot.getConstructor(enumClass, icbc);
			}
		}
		catch (Throwable e) {}
	}
}
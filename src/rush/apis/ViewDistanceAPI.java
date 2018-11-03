package rush.apis;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.entity.Player;

import rush.Main;
import rush.enums.Version;
import rush.utils.ReflectionUtils;

public class ViewDistanceAPI {

	private static Class<?> worldServerClass;
	private static Method getPlayerChunkMap;
	private static Method updateViewDistance;
	private static String name;
	
	public static int getViewDistance(Player player) {
		try {
			Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
			Object world = entityPlayer.getClass().getField("world").get(entityPlayer);
			Object worldServer = worldServerClass.cast(world);
			Object playerChunkMap = getPlayerChunkMap.invoke(worldServer);
			Field viewDistance = playerChunkMap.getClass().getDeclaredField(name);
			viewDistance.setAccessible(true);
			return (int) viewDistance.get(playerChunkMap);
		} catch (Exception e) {
			return -1;
		}
	}
	
	public static void setViewDistance(Player player, int viewDistance) {
		try {
			Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
			Object world = entityPlayer.getClass().getField("world").get(entityPlayer);
			Object worldServer = worldServerClass.cast(world);
			Object playerChunkMap = getPlayerChunkMap.invoke(worldServer);
			updateViewDistance.invoke(playerChunkMap, viewDistance);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static void load() {
		try 
		{
			Class<?> playerChunkMapClass = ReflectionUtils.getNMSClass("PlayerChunkMap");
			worldServerClass = ReflectionUtils.getNMSClass("WorldServer");
			getPlayerChunkMap = worldServerClass.getMethod("getPlayerChunkMap");
			updateViewDistance =  playerChunkMapClass.getMethod("a", int.class);
			if (Main.getVersion() == Version.v1_8) {
				name = "g";
			} else {
				name = "j";
			}
		} catch (Exception e) {}
	}
}
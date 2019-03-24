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
	private static Method getHandle;
	private static Field worldField;
	private static Field distanceField;
	
	public static int getViewDistance(Player player) {
		try {
			Object entityPlayer = getHandle.invoke(player);
			Object world = worldField.get(entityPlayer);
			Object worldServer = worldServerClass.cast(world);
			Object playerChunkMap = getPlayerChunkMap.invoke(worldServer);
			return (int) distanceField.get(playerChunkMap);
		} catch (Throwable e) {
			return -1;
		}
	}
	
	public static void setViewDistance(Player player, int viewDistance) {
		try {
			Object entityPlayer = getHandle.invoke(player);
			Object world = worldField.get(entityPlayer);
			Object worldServer = worldServerClass.cast(world);
			Object playerChunkMap = getPlayerChunkMap.invoke(worldServer);
			updateViewDistance.invoke(playerChunkMap, viewDistance);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	static void load() {
		try 
		{
			Class<?> craftPlayerClass = ReflectionUtils.getOBClass("entity.CraftPlayer");
			Class<?> playerChunkMapClass = ReflectionUtils.getNMSClass("PlayerChunkMap");
			Class<?> entityPlayerClass = ReflectionUtils.getNMSClass("EntityPlayer");
			
			worldField = entityPlayerClass.getField("world"); 
			getHandle = craftPlayerClass.getMethod("getHandle");
			worldServerClass = ReflectionUtils.getNMSClass("WorldServer");
			getPlayerChunkMap = worldServerClass.getMethod("getPlayerChunkMap");
			updateViewDistance =  playerChunkMapClass.getMethod("a", int.class);
			
			String fieldName;
			if (Main.getVersion() == Version.v1_8) {
				fieldName = "g";
			} else {
				fieldName = "j";
			}
			
			distanceField = playerChunkMapClass.getDeclaredField(fieldName);
			distanceField.setAccessible(true);
		} 
		catch (Throwable e) {}
	}
}
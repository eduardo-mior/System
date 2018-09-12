package rush.apis;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.entity.Player;

public class GodModeAPI {
	
	public static void setGodMode(Player player, boolean enabled) {
		try {
			Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
			Object abilities = entityPlayer.getClass().getField("abilities").get(entityPlayer);
			abilities.getClass().getField("isInvulnerable").set(abilities, enabled);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	public static boolean getGodMode(Player player) {
		try {
			Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
			Object abilities = entityPlayer.getClass().getField("abilities").get(entityPlayer);
			return abilities.getClass().getField("isInvulnerable").getBoolean(abilities);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}
		return false;
	}
}

package rush.apis;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.bukkit.entity.Player;

public class PingAPI {

	public static String getPlayerPing(Player player) {
		try {
			Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
			Field ping = entityPlayer.getClass().getField("ping");
			return String.valueOf(ping.get(entityPlayer));
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException	| SecurityException | NoSuchFieldException e) {
			return "§cIndisponivel";
		}
	}
}

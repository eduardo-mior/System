package rush.apis;

import java.lang.reflect.Field;

import org.bukkit.entity.Player;

public class PingAPI {

	public static String getPlayerPing(Player player) {
		try {
			Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
			Field ping = entityPlayer.getClass().getField("ping");
			return String.valueOf(ping.get(entityPlayer));
		} catch (Exception e) {
			return "Indisponivel";
		}
	}
}

package rush.apis;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class OnlinePlayersAPI {

	public static Player[] getOnlinePlayers() {
		try {
			return Bukkit.getOnlinePlayers().toArray(new Player[Bukkit.getOnlinePlayers().size()]);
		} catch (Exception | Error e) {
			try {
				return (Player[]) Bukkit.class.getMethod("getOnlinePlayers").invoke(null);
			} catch (Exception e2) {}
		}
		return null;
	}
}
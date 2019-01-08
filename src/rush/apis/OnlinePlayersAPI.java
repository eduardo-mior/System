package rush.apis;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class OnlinePlayersAPI {

	public static Player[] getOnlinePlayers() {
		try {
			return Bukkit.getOnlinePlayers().toArray(new Player[Bukkit.getOnlinePlayers().size()]);
		} catch (Throwable e) {
			try {
				return (Player[]) Bukkit.class.getMethod("getOnlinePlayers").invoke(null);
			} catch (Throwable e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}
}
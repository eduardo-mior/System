package rush.apis;

import org.bukkit.entity.Player;

public class HealthAPI {

	/**
	 * Em versões antigas o minecraft usa Inteiros para manipular a health dos
	 * players, então se tentarmos usar os métodos convencionais excessões serão
	 * lançadas.
	 */

	public static double getHealth(Player p) {
		try {
			return p.getHealth();
		} catch (NoSuchMethodError e1) {
			try {
				return (int) p.getClass().getMethod("getHealth").invoke(p);
			} catch (Throwable e2) {
				e2.printStackTrace();
			}
		}
		return 0;
	}

	public static double getMaxHealth(Player p) {
		try {
			return p.getHealth();
		} catch (NoSuchMethodError e1) {
			try {
				return (int) p.getClass().getMethod("getMaxHealth").invoke(p);
			} catch (Throwable e2) {
				e2.printStackTrace();
			}
		}
		return 0;
	}

	public static void setHealth(Player p, int health) {
		try {
			p.getClass().getMethod("setHealth", double.class).invoke(p, (double) health);
		} catch (Throwable e1) {
			try {
				p.getClass().getMethod("setHealth", int.class).invoke(p, health);
			} catch (Throwable e2) {
				e1.printStackTrace();
			}
		}
	}

	public static void setMaxHealth(Player p, int health) {
		try {
			p.getClass().getMethod("setMaxHealth", double.class).invoke(p, (double) health);
		} catch (Throwable e1) {
			try {
				p.getClass().getMethod("setMaxHealth", int.class).invoke(p, health);
			} catch (Throwable e2) {
				e2.printStackTrace();
			}
		}
	}

}
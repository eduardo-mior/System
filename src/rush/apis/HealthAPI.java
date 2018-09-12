package rush.apis;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.entity.Player;

public class HealthAPI {
	
	/** 
	 * Em versões antigas o minecraft usa Inteiros para manipular 
	 * a health dos players, então se tentarmos usar os métodos 
	 * convencionais excessões serão lançadas. 
	 */
	
	public static double getHealth(Player p) {
		try {
			return p.getHealth();
		} catch (NoSuchMethodError e1) {
			try {
				return (int) p.getClass().getMethod("getHealth").invoke(p);
			} catch (Exception e2) {}
		}
		return 0;
	}
	
	public static double getMaxHealth(Player p) {
		try {
			return p.getHealth();
		} catch (NoSuchMethodError e1) {
			try {
				return (int) p.getClass().getMethod("getMaxHealth").invoke(p);
			} catch (Exception e2) {}
		}
		return 0;
	}
	
	public static void setHealth(Player p, int health) {
		try {
			p.getClass().getMethod("setHealth", double.class).invoke(p, (double) health);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldError | SecurityException | InvocationTargetException | NoSuchMethodException e) {
			try {
				p.getClass().getMethod("setHealth", int.class).invoke(p, health);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e1) {
				p.sendMessage("§cErro ao tentar setar a health!");
				e.printStackTrace();
			}
		}
	}
	
	public static void setMaxHealth(Player p, int health) {
		try {
			p.getClass().getMethod("setMaxHealth", double.class).invoke(p, (double) health);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldError | SecurityException | InvocationTargetException | NoSuchMethodException e) {
			try {
				p.getClass().getMethod("setMaxHealth", int.class).invoke(p, health);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e1) {
				p.sendMessage("§cErro ao tentar setar a maxHealth!");
				e.printStackTrace();
			}
		}
	}
	
}

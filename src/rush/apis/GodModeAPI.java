package rush.apis;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.entity.Player;

import rush.Main;
import rush.utils.ReflectionUtils;

public class GodModeAPI {

	private static Method getHandle;
	private static Field abilitiesField;
	private static Field isInvulnerableField;
	
	public static void setGodMode(Player player, boolean enabled) {
		try {
			
			if (Main.getVersion().value < 17) {				
				Object entityPlayer = getHandle.invoke(player);
				Object abilities = abilitiesField.get(entityPlayer);
				isInvulnerableField.set(abilities, enabled);
			} else {
				player.getClass().getMethod("setInvulnerable", boolean.class).invoke(player, enabled);
			}
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static boolean getGodMode(Player player) {
		try {
			
			if (Main.getVersion().value < 17) {				
				Object entityPlayer = getHandle.invoke(player);
				Object abilities = abilitiesField.get(entityPlayer);
				return isInvulnerableField.getBoolean(abilities);
			} else {
				return (boolean) player.getClass().getMethod("isInvulnerable").invoke(player);
			}
			
		} catch (Throwable e) {
			e.printStackTrace();
			return false;
		}
	}

	static void load() {
		try 
		{
			if (Main.getVersion().value < 17) {				
				Class<?> craftPlayerClass = ReflectionUtils.getOBClass("entity.CraftPlayer");
				Class<?> entityPlayerClass = ReflectionUtils.getNMSClass("EntityPlayer");
				Class<?> playerAbilitiesClass = ReflectionUtils.getNMSClass("PlayerAbilities");
				getHandle = craftPlayerClass.getMethod("getHandle");
				abilitiesField = entityPlayerClass.getField("abilities"); 
				isInvulnerableField =playerAbilitiesClass.getField("isInvulnerable");
			}
		}
		catch (Throwable e) {}
	}
	
}
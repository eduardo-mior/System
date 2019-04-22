package rush.apis;

import java.lang.reflect.Field;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import rush.utils.ReflectionUtils;

public class SkullAPI {
	
	private static ItemStack base;
	private static Field profileField;
	
	public static ItemStack getByUrl(String url) {
		ItemStack skull = base.clone();
		try {
			SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
			GameProfile profile = new GameProfile(UUID.randomUUID(), null);
			byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
			profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
			profileField.set(skullMeta, profile);
			skull.setItemMeta(skullMeta);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return skull;
	}
	
	public static ItemStack getByName(String name) {
		ItemStack skull = base.clone();
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setOwner(name);
		skull.setItemMeta(meta);
		return skull;
	}
	
	static void load() {
		try 
		{
			Class<?> skullMetaClass = ReflectionUtils.getOBClass("inventory.CraftMetaSkull");			
			profileField = skullMetaClass.getDeclaredField("profile");
			profileField.setAccessible(true);
			base = new ItemStack(Material.SKULL_ITEM);
			base.setDurability((short) 3);
		}
		catch (Throwable e) {}
	}
	
}

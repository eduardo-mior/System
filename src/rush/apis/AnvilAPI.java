package rush.apis;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import rush.Main;
import rush.enums.Version;
import rush.utils.ReflectionUtils;

public class AnvilAPI {
	
	private static Constructor<?> containerAnvilConstructor;
	private static Constructor<?> blockPositionConstructor;
	private static Constructor<?> packetConstructor;
	private static Method addSlotListener;
	private static Object message;
	
	public static void openAnvil(Player p) {
		try {
			
			Location l = p.getLocation();
			int x = l.getBlockX();
			int y = l.getBlockY();
			int z = l.getBlockZ();
			
			Object packet;
			Object container;
			Object entityPlayer = p.getClass().getMethod("getHandle").invoke(p);
			Object world = entityPlayer.getClass().getField("world").get(entityPlayer);
			Object inventory = entityPlayer.getClass().getField("inventory").get(entityPlayer);			
			Object counter = entityPlayer.getClass().getMethod("nextContainerCounter").invoke(entityPlayer);
			
			if (!Main.isOldVersion()) {
				Object blockPosition = blockPositionConstructor.newInstance(x, y, z);
				container = containerAnvilConstructor.newInstance(inventory, world, blockPosition, entityPlayer);
				packet = packetConstructor.newInstance(counter, "minecraft:anvil", message);
			} else {
				container = containerAnvilConstructor.newInstance(inventory, world, x, y, z, entityPlayer);
				packet = packetConstructor.newInstance(counter, 8, "Repairing", 9, false);
			}
			
			ReflectionUtils.sendPacket(p, packet);
			
			Field checkReachable = container.getClass().getField("checkReachable");
			checkReachable.set(container, false);
			
			Field activeContainerField = entityPlayer.getClass().getField("activeContainer");
			activeContainerField.set(entityPlayer, container);
			
			Object activeContainer = entityPlayer.getClass().getField("activeContainer").get(entityPlayer);
			Field windowId = activeContainer.getClass().getField("windowId");
			windowId.set(activeContainer, counter);
			
			addSlotListener.invoke(activeContainer, entityPlayer);
		
		} catch (Error | Exception e) {
			e.printStackTrace();
		}
	}

	static void load() {
		try 
		{
			Class<?> icraftingClass = ReflectionUtils.getNMSClass("ICrafting");
			Class<?> containerClass = ReflectionUtils.getNMSClass("Container");
			addSlotListener = containerClass.getMethod("addSlotListener", icraftingClass);
			
			Class<?> containerAnvilClass = ReflectionUtils.getNMSClass("ContainerAnvil");
			Class<?> playerInventoryClass = ReflectionUtils.getNMSClass("PlayerInventory");
			Class<?> worldClass = ReflectionUtils.getNMSClass("World");
			Class<?> entityHumanClass = ReflectionUtils.getNMSClass("EntityHuman");
			if (!Main.isOldVersion()) {
				Class<?> blockPositionClass = ReflectionUtils.getNMSClass("BlockPosition");
				blockPositionConstructor = blockPositionClass.getConstructor(int.class, int.class, int.class);
				containerAnvilConstructor = containerAnvilClass.getConstructor(playerInventoryClass, worldClass, blockPositionClass, entityHumanClass);
			} else {
				containerAnvilConstructor = containerAnvilClass.getConstructor(playerInventoryClass, worldClass, int.class, int.class, int.class, entityHumanClass);
			}
			
			Class<?> packetOpenWindowClass;
			if (Main.getVersion() == Version.v1_5 || Main.getVersion() == Version.v1_6) {
				packetOpenWindowClass = ReflectionUtils.getNMSClass("Packet100OpenWindow");
			} else {
				packetOpenWindowClass = ReflectionUtils.getNMSClass("PacketPlayOutOpenWindow");
			}
			
			if (!Main.isOldVersion()) {
				Class<?> icbc = ReflectionUtils.getNMSClass("IChatBaseComponent");
				Class<?> cm = ReflectionUtils.getNMSClass("ChatMessage");
				Constructor<?> messageConstrutor = cm.getConstructor(String.class, Object[].class);
				packetConstructor = packetOpenWindowClass.getConstructor(int.class, String.class, icbc);
				message = messageConstrutor.newInstance("Repairing", new Object[] {});
			} else {
				packetConstructor = packetOpenWindowClass.getConstructor(int.class, int.class, String.class, int.class, boolean.class);
			}
			
		}
		catch (Error | Exception e) {
			e.printStackTrace();
		}
	}
	
}
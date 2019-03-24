package rush.apis;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Random;

import org.bukkit.inventory.ItemStack;

import rush.utils.ReflectionUtils;

public class ItemAPI {

	private static Class<?> ItemStackClass;
	private static Class<?> CraftItemStackClass;
	private static Class<?> NBTTagCompoundClass;
	private static Class<?> NBTTagListClass;
	private static Class<?> NBTTagStringClass;
	private static Class<?> NBTTagIntClass;
	private static Class<?> NBTTagDoubleClass;
	private static Method asNMSCopy;
	private static Method asBukkitCopy;
	private static Method asCraftMirror;
	private static Method getRepairCost;
	private static Method setRepairCost;
	private static Method setNBTTagCompound;
	private static Method hasNBTTagCompound;
	private static Method getNBTTagCompound;
	private static Method hasKey;
	private static Method getString;
	private static Method getBoolean;
	private static Method setString;
	private static Method setBoolean;
	private static Method getNBTBase;
	private static Method getNBTList;
	private static Method hasTag;
	private static Method setNBTBaseCompound;
	private static Method addNBTBaseTag;
	private static Method createTag;
	
	public static ItemStack setAttributeNBT(ItemStack item, String attribute, double value, int operation) {
		int least = new Random().nextInt(8192);
		int most = new Random().nextInt(8192);
		try	{
			Object NBTTagCompound;
			Object CraftItemStack = asNMSCopy.invoke(null, item);
			boolean hasNBTTag = (boolean) hasNBTTagCompound.invoke(CraftItemStack);
			if (hasNBTTag) {
				NBTTagCompound = getNBTTagCompound.invoke(CraftItemStack);
			} else {
				NBTTagCompound = NBTTagCompoundClass.newInstance();
			}
			
			Object AttributeModifiers;
			Object Modifier = NBTTagCompoundClass.newInstance();
			boolean hasAttribute = (boolean) hasTag.invoke(NBTTagCompound, "AttributeModifiers");
			if (hasAttribute) {
				AttributeModifiers = getNBTList.invoke(NBTTagCompound, "AttributeModifiers", 10);
			} else {
				AttributeModifiers = NBTTagListClass.newInstance();
			}
			
			Object AttributeName = createTag.invoke(null, (byte) 8);
			Field fieldAttributeName = NBTTagStringClass.getDeclaredField("data");
			fieldAttributeName.setAccessible(true);
			fieldAttributeName.set(AttributeName, attribute);
			
			Object Name = createTag.invoke(null, (byte) 8);
			Field fieldName = NBTTagStringClass.getDeclaredField("data");
			fieldName.setAccessible(true);
			fieldName.set(Name, attribute);
			
			Object Amount = createTag.invoke(null, (byte) 6);
			Field fieldAmount = NBTTagDoubleClass.getDeclaredField("data");
			fieldAmount.setAccessible(true);
			fieldAmount.set(Amount, value);
			
			Object Operation = createTag.invoke(null, (byte) 3);
			Field fieldOperation = NBTTagIntClass.getDeclaredField("data");
			fieldOperation.setAccessible(true);
			fieldOperation.set(Operation, operation);
			
			Object UUIDLeast = createTag.invoke(null, (byte) 3);
			Field fieldUUIDLeast = NBTTagIntClass.getDeclaredField("data");
			fieldUUIDLeast.setAccessible(true);
			fieldUUIDLeast.set(UUIDLeast, least);
			
			Object UUIDMost = createTag.invoke(null, (byte) 3);
			Field fieldUUIDMost = NBTTagIntClass.getDeclaredField("data");
			fieldUUIDMost.setAccessible(true);
			fieldUUIDMost.set(UUIDMost, most);
			
			setNBTBaseCompound.invoke(Modifier, "AttributeName", AttributeName);
			setNBTBaseCompound.invoke(Modifier, "Name", Name);
			setNBTBaseCompound.invoke(Modifier, "Amount", Amount);
			setNBTBaseCompound.invoke(Modifier, "Operation", Operation);
			setNBTBaseCompound.invoke(Modifier, "UUIDLeast", UUIDLeast);
			setNBTBaseCompound.invoke(Modifier, "UUIDMost", UUIDMost);

			Object NBTBase = getNBTBase.invoke(Modifier);
			addNBTBaseTag.invoke(AttributeModifiers, NBTBase);
			setNBTBaseCompound.invoke(NBTTagCompound, "AttributeModifiers", AttributeModifiers);
			setNBTTagCompound.invoke(CraftItemStack, NBTTagCompound);
			
			return (ItemStack) asCraftMirror.invoke(null, CraftItemStack);
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean hasInfo(ItemStack item, String key) {
		try {
			Object CraftItemStack = asNMSCopy.invoke(null, item);
			boolean hasNBTTag = (boolean) hasNBTTagCompound.invoke(CraftItemStack);
			if (hasNBTTag) {
				Object NBTTagCompound = getNBTTagCompound.invoke(CraftItemStack);
				return (boolean) hasKey.invoke(NBTTagCompound, key);
			}
			return false;
		} catch (Throwable e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static String getInfo(ItemStack item, String key) {
		try {
			Object CraftItemStack = asNMSCopy.invoke(null, item);
			boolean hasNBTTag = (boolean) hasNBTTagCompound.invoke(CraftItemStack);
			if (hasNBTTag) {
				Object NBTTagCompound = getNBTTagCompound.invoke(CraftItemStack);
				return getString.invoke(NBTTagCompound, key).toString();
			}
			return null;
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static ItemStack saveInfo(ItemStack item, String key, String value) {
		try	{
			Object NBTTagCompound;
			Object CraftItemStack = asNMSCopy.invoke(null, item);
			boolean hasNBTTag = (boolean) hasNBTTagCompound.invoke(CraftItemStack);
			if (hasNBTTag) {
				NBTTagCompound = getNBTTagCompound.invoke(CraftItemStack);
			} else {
				NBTTagCompound = NBTTagCompoundClass.newInstance();
			}
			setString.invoke(NBTTagCompound, key, value);
			setNBTTagCompound.invoke(CraftItemStack, NBTTagCompound);
			return (ItemStack) asCraftMirror.invoke(null, CraftItemStack);
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean isUnbreakable(ItemStack item) {
		try {
			Object NBTTagCompound;
			Object CraftItemStack = asNMSCopy.invoke(null, item);
			boolean hasNBTTag = (boolean) hasNBTTagCompound.invoke(CraftItemStack);
			if (hasNBTTag) {
				NBTTagCompound = getNBTTagCompound.invoke(CraftItemStack);
			} else {
				return false;
			}
			return (boolean) getBoolean.invoke(NBTTagCompound, "Unbreakable");
		} catch (Throwable e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static ItemStack setUnbreakable(ItemStack item, boolean bool) {
		if (item.getType().getMaxDurability() != 0 && item.getDurability() != 0) {
			item.setDurability((short) 0);
		}
		try	{
			Object NBTTagCompound;
			Object CraftItemStack = asNMSCopy.invoke(null, item);
			boolean hasNBTTag = (boolean) hasNBTTagCompound.invoke(CraftItemStack);
			if (hasNBTTag) {
				NBTTagCompound = getNBTTagCompound.invoke(CraftItemStack);
			} else {
				NBTTagCompound = NBTTagCompoundClass.newInstance();
			}
			setBoolean.invoke(NBTTagCompound, "Unbreakable", true);
			setNBTTagCompound.invoke(CraftItemStack, NBTTagCompound);
			return (ItemStack) asCraftMirror.invoke(null, CraftItemStack);
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static int getRepairCost(ItemStack item) {
		try {
			Object CraftItemStack = asNMSCopy.invoke(null, item);
			int cost = (int) getRepairCost.invoke(CraftItemStack);
			if (item.getType().getMaxDurability() != 0) {
				double durability = item.getDurability();
				double maxDurability = item.getType().getMaxDurability();
				double durabilityPercent = (durability * 100.0) / maxDurability;
				     if (durabilityPercent <=  25.0) cost += 1;
				else if (durabilityPercent <=  50.0) cost += 2;
				else if (durabilityPercent <=  75.0) cost += 3;
				else if (durabilityPercent <= 100.0) cost += 4;
			}
			return cost;
		} catch (Throwable e) {
			e.printStackTrace();
			return 40;
		}
	}
	
	public static ItemStack setRepairCost(ItemStack item, int cost) {
		try {
			Object CraftItemStack = asNMSCopy.invoke(null, item);
			setRepairCost.invoke(CraftItemStack, cost);
			return (ItemStack) asBukkitCopy.invoke(null, CraftItemStack);
		} catch (Throwable e) {
			e.printStackTrace();
			return item;
		}
	}
	
	static void load() {
		try 
		{
			// Item Classes 
			ItemStackClass = ReflectionUtils.getNMSClass("ItemStack");
			CraftItemStackClass = ReflectionUtils.getOBClass("inventory.CraftItemStack");
			
			// NBTTag Classes
			NBTTagCompoundClass = ReflectionUtils.getNMSClass("NBTTagCompound");
			NBTTagListClass = ReflectionUtils.getNMSClass("NBTTagList");
			NBTTagStringClass = ReflectionUtils.getNMSClass("NBTTagString");
			NBTTagIntClass = ReflectionUtils.getNMSClass("NBTTagInt");
			NBTTagDoubleClass = ReflectionUtils.getNMSClass("NBTTagDouble");
			Class<?> NBTBaseClass = ReflectionUtils.getNMSClass("NBTBase");
			
			// Item Handle Methods
			asNMSCopy = CraftItemStackClass.getDeclaredMethod("asNMSCopy", ItemStack.class);
			asBukkitCopy = CraftItemStackClass.getDeclaredMethod("asBukkitCopy", ItemStackClass);
			asCraftMirror = CraftItemStackClass.getDeclaredMethod("asCraftMirror", ItemStackClass);
			
			// Repair cost Methods
			getRepairCost = ItemStackClass.getDeclaredMethod("getRepairCost");
			setRepairCost = ItemStackClass.getMethod("setRepairCost", int.class);
			
			// Item NBTTag Methods
			getNBTTagCompound = ItemStackClass.getDeclaredMethod("getTag");			
			hasNBTTagCompound = ItemStackClass.getDeclaredMethod("hasTag");
			setNBTTagCompound = ItemStackClass.getDeclaredMethod("setTag", NBTTagCompoundClass);

			// Basic NBTTag Handle Methods
			hasKey = NBTTagCompoundClass.getDeclaredMethod("hasKey", String.class);
			getString = NBTTagCompoundClass.getDeclaredMethod("getString", String.class);
			getBoolean = NBTTagCompoundClass.getDeclaredMethod("getBoolean", String.class);
			setString = NBTTagCompoundClass.getDeclaredMethod("setString", String.class, String.class);
			setBoolean = NBTTagCompoundClass.getDeclaredMethod("setBoolean", String.class, boolean.class);
			
			// Advance NBTTag Handle
			getNBTBase = NBTTagCompoundClass.getDeclaredMethod("clone");
			getNBTList = NBTTagCompoundClass.getDeclaredMethod("getList", String.class, int.class);
			hasTag = NBTTagCompoundClass.getDeclaredMethod("hasKey", String.class);
			setNBTBaseCompound = NBTTagCompoundClass.getDeclaredMethod("set", String.class, NBTBaseClass);
			addNBTBaseTag = NBTTagListClass.getDeclaredMethod("add", NBTBaseClass);
			createTag = NBTBaseClass.getDeclaredMethod("createTag", byte.class);
			createTag.setAccessible(true);
		}
		catch (Throwable e) {}
	}
	
}
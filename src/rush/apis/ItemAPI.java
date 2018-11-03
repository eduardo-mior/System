package rush.apis;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Random;

import org.bukkit.inventory.ItemStack;

import rush.utils.ReflectionUtils;

public class ItemAPI {

	private static Class<?> CraftItemStackClass;
	private static Class<?> NBTTagCompoundClass;
	private static Class<?> NBTBaseClass;
	private static Class<?> NBTTagListClass;
	private static Class<?> NBTTagStringClass;
	private static Class<?> NBTTagIntClass;
	private static Class<?> NBTTagDoubleClass;
	private static Class<?> ItemStackClass;
	private static Method asNMSCopy;
	private static Method asCraftMirror;
	private static Method setBoolean;
	private static Method setNBTTagCompound;
	private static Method hasNBTTagCompound;
	private static Method getNBTTagCompound;
	private static Method getNBTList;
	private static Method getNBTBase;
	private static Method addNBTBaseTag;
	private static Method setNBTBaseCompound;
	private static Method hasTag;
	private static Method createTag;
	
	public static ItemStack setAttributeNBT(ItemStack item, String attribute, double value, int operation) {
		int least = new Random().nextInt(8192);
		int most = new Random().nextInt(8192);
		try	{
			Object NBTTagCompound;
			Object CraftItemStack = asNMSCopy.invoke(null, item);
			boolean hasNBT = (boolean) hasNBTTagCompound.invoke(CraftItemStack);
			if (hasNBT) {
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
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static ItemStack setUnbreakable(ItemStack item, boolean bool) {
		if (item != null && item.getType().getMaxDurability() != 0 && item.getDurability() != 0) {
			item.setDurability((short)0);
		}
		try	{
			Object NBTTagCompound;
			Object CraftItemStack = asNMSCopy.invoke(null, item);
			boolean hasNBT = (boolean) hasNBTTagCompound.invoke(CraftItemStack);
			if (hasNBT) {
				NBTTagCompound = getNBTTagCompound.invoke(CraftItemStack);
			} else {
				NBTTagCompound = NBTTagCompoundClass.newInstance();
			}
			setBoolean.invoke(NBTTagCompound, "Unbreakable", true);
			setNBTTagCompound.invoke(CraftItemStack, NBTTagCompound);
			return (ItemStack) asCraftMirror.invoke(null, CraftItemStack);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	static void load() {
		try 
		{
			CraftItemStackClass = ReflectionUtils.getOBClass("inventory.CraftItemStack");
			NBTTagCompoundClass = ReflectionUtils.getNMSClass("NBTTagCompound");
			NBTBaseClass = ReflectionUtils.getNMSClass("NBTBase");
			NBTTagListClass = ReflectionUtils.getNMSClass("NBTTagList");
			NBTTagStringClass = ReflectionUtils.getNMSClass("NBTTagString");
			NBTTagIntClass = ReflectionUtils.getNMSClass("NBTTagInt");
			NBTTagDoubleClass = ReflectionUtils.getNMSClass("NBTTagDouble");
			ItemStackClass = ReflectionUtils.getNMSClass("ItemStack");
			asNMSCopy = CraftItemStackClass.getDeclaredMethod("asNMSCopy", ItemStack.class);
			asCraftMirror = CraftItemStackClass.getDeclaredMethod("asCraftMirror", ItemStackClass);
			setBoolean = NBTTagCompoundClass.getDeclaredMethod("setBoolean", String.class, boolean.class);
			setNBTTagCompound = ItemStackClass.getDeclaredMethod("setTag", NBTTagCompoundClass);
			hasNBTTagCompound = ItemStackClass.getDeclaredMethod("hasTag");
			getNBTTagCompound = ItemStackClass.getDeclaredMethod("getTag");
			getNBTList = NBTTagCompoundClass.getDeclaredMethod("getList", String.class, int.class);
			getNBTBase = NBTTagCompoundClass.getDeclaredMethod("clone");
			addNBTBaseTag = NBTTagListClass.getDeclaredMethod("add", NBTBaseClass);
			setNBTBaseCompound = NBTTagCompoundClass.getDeclaredMethod("set", String.class, NBTBaseClass);
			hasTag = NBTTagCompoundClass.getDeclaredMethod("hasKey", String.class);
			createTag = NBTBaseClass.getDeclaredMethod("createTag", byte.class);
			createTag.setAccessible(true);
		}
		catch (Exception e) {}
	}
}

package rush.utils.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.bukkit.inventory.ItemStack;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import rush.utils.objectStream.BukkitObjectInputStream;
import rush.utils.objectStream.BukkitObjectOutputStream;

public class SerializerOLD {
	
    public static ItemStack deserializeItemStack(String data) {
    	try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            dataInput.readInt();
            ItemStack item = (ItemStack) dataInput.readObject();
            
            dataInput.close();
            return item;
        } catch (Throwable e) {
            e.printStackTrace();
        }
		return null;
    }
    
    public static ItemStack[] deserializeListItemStack(String data) {
    	try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            ItemStack[] items = new ItemStack[dataInput.readInt()];
    
            for (int i = 0; i < items.length; i++) {
            	items[i] = (ItemStack) dataInput.readObject();
            }
            
            dataInput.close();
            return items;
        } catch (Throwable e) {
            e.printStackTrace();
        }
		return null;
    }
    
	public static String serializeItemStack(ItemStack item) {
    	try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
            
            // Write the size of the inventory
            dataOutput.writeInt(1);
            
            // Save every element in the list
            dataOutput.writeObject(item);
            
            // Serialize that array
            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (Throwable e) {
           e.printStackTrace();
        }	
    	return null;
   	}
    
    public static String serializeListItemStack(ItemStack[] items) {
    	try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
            
            dataOutput.writeInt(items.length);
            
            for (int i = 0; i < items.length; i++) {
                dataOutput.writeObject(items[i]);
            }
            
            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (Throwable e) {
           e.printStackTrace();
        }
		return null;
    }

}
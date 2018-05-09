package rush.utils;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import rush.Main;

public class Locations {

	public static Location spawn;
	public static Location areaVip;
	public static Location areaNaoVip;
	
	public static void loadLocations() {
		areaVip();
		areaNaoVip();
		spawn();
	}
	
	public static boolean validarSpawn() {
		List<World> worlds = Bukkit.getWorlds();
		World world = spawn.getWorld();
		if (worlds.contains(world)) return true;
		else return false;
	}

	public static void areaVip() {
         areaVip = new Location(Main.aqui.getServer().getWorld
           (ConfigManager.getConfig("locations").getString("AreaVip.world")), 
           ConfigManager.getConfig("locations").getDouble("AreaVip.x"), 
           ConfigManager.getConfig("locations").getDouble("AreaVip.y"), 
           ConfigManager.getConfig("locations").getDouble("AreaVip.z"), 
           Float.parseFloat(ConfigManager.getConfig("locations").getString("AreaVip.yaw")), 
           Float.parseFloat(ConfigManager.getConfig("locations").getString("AreaVip.pitch")));
	}
	
	public static void areaNaoVip() {
        areaNaoVip = new Location(Main.aqui.getServer().getWorld
           (ConfigManager.getConfig("locations").getString("AreaNaoVip.world")), 
           ConfigManager.getConfig("locations").getDouble("AreaNaoVip.x"), 
           ConfigManager.getConfig("locations").getDouble("AreaNaoVip.y"), 
           ConfigManager.getConfig("locations").getDouble("AreaNaoVip.z"), 
           Float.parseFloat(ConfigManager.getConfig("locations").getString("AreaNaoVip.yaw")), 
           Float.parseFloat(ConfigManager.getConfig("locations").getString("AreaNaoVip.pitch")));
	}
	
	public static void spawn() {
	    spawn = new Location(Main.aqui.getServer().getWorld
	       (ConfigManager.getConfig("locations").getString("Spawn.world")), 
	       ConfigManager.getConfig("locations").getDouble("Spawn.x"), 
	       ConfigManager.getConfig("locations").getDouble("Spawn.y"), 
	       ConfigManager.getConfig("locations").getDouble("Spawn.z"), 
	       Float.parseFloat(ConfigManager.getConfig("locations").getString("Spawn.yaw")), 
	       Float.parseFloat(ConfigManager.getConfig("locations").getString("Spawn.pitch")));
	} 
}

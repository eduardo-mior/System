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
	public static Location padrao;

	
	public static void loadLocations() {
		areaVip();
		areaNaoVip();
		spawn();
		validarLocations();
	}
	
	public static void validarLocations() {
		List<World> worlds = Bukkit.getWorlds();
		World worldSpawn = spawn.getWorld();
		World worldVip = areaVip.getWorld();
		World worldNaoVip = areaNaoVip.getWorld();
		padrao = new Location(worlds.get(0), 1.0, 1.0, 1.0, 1.0F, 1.0F);
		if (!worlds.contains(worldSpawn)) spawn = padrao;
		if (!worlds.contains(worldVip)) areaVip = padrao;
		if (!worlds.contains(worldNaoVip)) areaNaoVip = padrao;
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
package rush.utils;

import org.bukkit.Location;

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

	public static void areaVip() {
         areaVip = new Location(Main.aqui.getServer().getWorld
            (Main.aqui.getVip().getString("AreaVip.world")), 
            Main.aqui.getVip().getDouble("AreaVip.x"), 
            Main.aqui.getVip().getDouble("AreaVip.y"), 
            Main.aqui.getVip().getDouble("AreaVip.z"), 
            Float.parseFloat(Main.aqui.getVip().getString("AreaVip.yaw")), 
            Float.parseFloat(Main.aqui.getVip().getString("AreaVip.pitch")));
	}
	
	public static void areaNaoVip() {
        areaNaoVip = new Location(Main.aqui.getServer().getWorld
           (Main.aqui.getNaoVip().getString("AreaNaoVip.world")), 
           Main.aqui.getNaoVip().getDouble("AreaNaoVip.x"), 
           Main.aqui.getNaoVip().getDouble("AreaNaoVip.y"), 
           Main.aqui.getNaoVip().getDouble("AreaNaoVip.z"), 
           Float.parseFloat(Main.aqui.getNaoVip().getString("AreaNaoVip.yaw")), 
           Float.parseFloat(Main.aqui.getNaoVip().getString("AreaNaoVip.pitch")));
	}
	
	public static void spawn() {
	    spawn = new Location(Main.aqui.getServer().getWorld
	        (Main.aqui.getSpawn().getString("Spawn.world")), 
	        Main.aqui.getSpawn().getDouble("Spawn.x"), 
	        Main.aqui.getSpawn().getDouble("Spawn.y"), 
	        Main.aqui.getSpawn().getDouble("Spawn.z"), 
	        Float.parseFloat(Main.aqui.getSpawn().getString("Spawn.yaw")), 
	        Float.parseFloat(Main.aqui.getSpawn().getString("Spawn.pitch")));
	} 
}

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
            (Main.aqui.getConfig().getString("AreaVip.world")), 
            Main.aqui.getConfig().getDouble("AreaVip.x"), 
            Main.aqui.getConfig().getDouble("AreaVip.y"), 
            Main.aqui.getConfig().getDouble("AreaVip.z"), 
            Float.parseFloat(Main.aqui.getConfig().getString("AreaVip.yaw")), 
            Float.parseFloat(Main.aqui.getConfig().getString("AreaVip.pitch")));
	}
	
	public static void areaNaoVip() {
        areaNaoVip = new Location(Main.aqui.getServer().getWorld
           (Main.aqui.getConfig().getString("AreaNaoVip.world")), 
           Main.aqui.getConfig().getDouble("AreaNaoVip.x"), 
           Main.aqui.getConfig().getDouble("AreaNaoVip.y"), 
           Main.aqui.getConfig().getDouble("AreaNaoVip.z"), 
           Float.parseFloat(Main.aqui.getConfig().getString("AreaNaoVip.yaw")), 
           Float.parseFloat(Main.aqui.getConfig().getString("AreaNaoVip.pitch")));
	}
	
	public static void spawn() {
	    spawn = new Location(Main.aqui.getServer().getWorld
	        (Main.aqui.getConfig().getString("Spawn.world")), 
	        Main.aqui.getConfig().getDouble("Spawn.x"), 
	        Main.aqui.getConfig().getDouble("Spawn.y"), 
	        Main.aqui.getConfig().getDouble("Spawn.z"), 
	        Float.parseFloat(Main.aqui.getConfig().getString("Spawn.yaw")), 
	        Float.parseFloat(Main.aqui.getConfig().getString("Spawn.pitch")));
	   }
}

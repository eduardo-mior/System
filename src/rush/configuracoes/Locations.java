package rush.configuracoes;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

import rush.Main;
import rush.utils.manager.ConfigManager;

public class Locations {

	public static Location padrao = new Location(Bukkit.getServer().getWorlds().get(0), 1.0, 250.0, 1.0, 1.0F, 1.0F);
	public static Location spawn = padrao;
	public static Location spawnVip = padrao;
	public static Location areaVip = padrao;
	public static Location areaNaoVip = padrao;
	
	public static void loadLocations() {
		setSpawn();
		setAreaVip();
		setSpawnVip();
		setAreaNaoVip();
		validarLocations(true);
		setDefaultServerSpawn();
	}
	
	private static void validarLocations(boolean reavaliar) {
		boolean tentarValidarNovamente = false;
		
		World worldSpawn = spawn.getWorld();
		World worldSpawnVip = spawnVip.getWorld();
		World worldVip = areaVip.getWorld();
		World worldNaoVip = areaNaoVip.getWorld();
		
		if (worldSpawn == null) {
			tentarValidarNovamente = true;
			spawn = padrao;
			if (!reavaliar) {
				Bukkit.getConsoleSender().sendMessage("§c[System] Nao foi possivel carregar a localizacao do Spawn! Entre no jogo e use /setspawn normal");
			}
		}
		
		if (worldSpawnVip  == null) {
			tentarValidarNovamente = true;
			spawnVip = padrao;
			if (!reavaliar) {
				Bukkit.getConsoleSender().sendMessage("§c[System] Nao foi possivel carregar a localizacao do Spawn Vip! Entre no jogo e use /setspawn vip");
			}
		}
		
		if (worldVip == null) {
			tentarValidarNovamente = true;
			areaVip = padrao;
			if (!reavaliar) {
				Bukkit.getConsoleSender().sendMessage("§c[System] Nao foi possivel carregar a localizacao da Area Vip! Entre no jogo e use /setmundovip areaVip");
			}
		}
		
		if (worldNaoVip == null) {
			tentarValidarNovamente = true;
			areaNaoVip = padrao;
			if (!reavaliar) {
				Bukkit.getConsoleSender().sendMessage("§c[System] Nao foi possivel carregar a localizacao da Area Vip! Entre no jogo e use /setmundovip areaNaoVip");
			}
		}
		
		if (tentarValidarNovamente && reavaliar) tentarValidarNovamente();
	}
	
	private static void tentarValidarNovamente() {
		new BukkitRunnable() {
			@Override
			public void run() {
				setAreaVip();
				setAreaNaoVip();
				setSpawn();
				setSpawnVip();
				validarLocations(false);
			}
		}.runTaskLaterAsynchronously(Main.get(), 20 * 25);
	}

	private static void setAreaVip() {
		try {
         areaVip = new Location(Bukkit.getServer().getWorld(
           ConfigManager.getConfig("locations").getString("AreaVip.world")), 
           ConfigManager.getConfig("locations").getDouble("AreaVip.x"), 
           ConfigManager.getConfig("locations").getDouble("AreaVip.y"), 
           ConfigManager.getConfig("locations").getDouble("AreaVip.z"), 
           Float.parseFloat(ConfigManager.getConfig("locations").getString("AreaVip.yaw")), 
           Float.parseFloat(ConfigManager.getConfig("locations").getString("AreaVip.pitch")));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	private static void setAreaNaoVip() {
		try {
        areaNaoVip = new Location(Bukkit.getServer().getWorld(
           ConfigManager.getConfig("locations").getString("AreaNaoVip.world")), 
           ConfigManager.getConfig("locations").getDouble("AreaNaoVip.x"), 
           ConfigManager.getConfig("locations").getDouble("AreaNaoVip.y"), 
           ConfigManager.getConfig("locations").getDouble("AreaNaoVip.z"), 
           Float.parseFloat(ConfigManager.getConfig("locations").getString("AreaNaoVip.yaw")), 
           Float.parseFloat(ConfigManager.getConfig("locations").getString("AreaNaoVip.pitch")));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	private static void setSpawn() {
		try {
	    spawn = new Location(Bukkit.getServer().getWorld(
	       ConfigManager.getConfig("locations").getString("Spawn.world")), 
	       ConfigManager.getConfig("locations").getDouble("Spawn.x"), 
	       ConfigManager.getConfig("locations").getDouble("Spawn.y"), 
	       ConfigManager.getConfig("locations").getDouble("Spawn.z"), 
	       Float.parseFloat(ConfigManager.getConfig("locations").getString("Spawn.yaw")), 
	       Float.parseFloat(ConfigManager.getConfig("locations").getString("Spawn.pitch")));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	private static void setSpawnVip() {
		try {
	    spawnVip = new Location(Bukkit.getServer().getWorld(
	       ConfigManager.getConfig("locations").getString("SpawnVip.world")), 
	       ConfigManager.getConfig("locations").getDouble("SpawnVip.x"), 
	       ConfigManager.getConfig("locations").getDouble("SpawnVip.y"), 
	       ConfigManager.getConfig("locations").getDouble("SpawnVip.z"), 
	       Float.parseFloat(ConfigManager.getConfig("locations").getString("SpawnVip.yaw")), 
	       Float.parseFloat(ConfigManager.getConfig("locations").getString("SpawnVip.pitch")));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	private static void setDefaultServerSpawn() {
		List<World> worlds = Bukkit.getServer().getWorlds();
		World worldSpawn = spawn.getWorld();
		if (worldSpawn == null) {
			worlds.get(0).setSpawnLocation(padrao.getBlockX(), padrao.getBlockY(), padrao.getBlockZ());
		} else {
			worldSpawn.setSpawnLocation(spawn.getBlockX(), spawn.getBlockY(), spawn.getBlockZ());
		}
	}
}
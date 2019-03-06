package rush.configuracoes;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
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

	private static void setAreaVip() {
		try {
			FileConfiguration config = ConfigManager.getConfig("locations");
			areaVip = new Location(Bukkit.getServer().getWorld(
	           config.getString("AreaVip.world")), 
	           config.getDouble("AreaVip.x"), 
	           config.getDouble("AreaVip.y"), 
	           config.getDouble("AreaVip.z"), 
	           Float.parseFloat(config.getString("AreaVip.yaw")), 
	           Float.parseFloat(config.getString("AreaVip.pitch")));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	private static void setAreaNaoVip() {
		try {
			FileConfiguration config = ConfigManager.getConfig("locations");
			areaNaoVip = new Location(Bukkit.getServer().getWorld(
	           config.getString("AreaNaoVip.world")), 
	           config.getDouble("AreaNaoVip.x"), 
	           config.getDouble("AreaNaoVip.y"), 
	           config.getDouble("AreaNaoVip.z"), 
	           Float.parseFloat(config.getString("AreaNaoVip.yaw")), 
	           Float.parseFloat(config.getString("AreaNaoVip.pitch")));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	private static void setSpawn() {
		try {
			FileConfiguration config = ConfigManager.getConfig("locations");
		    spawn = new Location(Bukkit.getServer().getWorld(
		       config.getString("Spawn.world")), 
		       config.getDouble("Spawn.x"), 
		       config.getDouble("Spawn.y"), 
		       config.getDouble("Spawn.z"), 
		       Float.parseFloat(config.getString("Spawn.yaw")), 
		       Float.parseFloat(config.getString("Spawn.pitch")));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	private static void setSpawnVip() {
		try {
			FileConfiguration config = ConfigManager.getConfig("locations");
		    spawnVip = new Location(Bukkit.getServer().getWorld(
		       config.getString("SpawnVip.world")), 
		       config.getDouble("SpawnVip.x"), 
		       config.getDouble("SpawnVip.y"), 
		       config.getDouble("SpawnVip.z"), 
		       Float.parseFloat(config.getString("SpawnVip.yaw")), 
		       Float.parseFloat(config.getString("SpawnVip.pitch")));
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
				setSpawn();
				setAreaVip();
				setSpawnVip();
				setAreaNaoVip();
				validarLocations(false);
				setDefaultServerSpawn();
			}
		}.runTaskLaterAsynchronously(Main.get(), 20L * 25L);
	}
	
}
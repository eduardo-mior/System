package rush.apis;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import rush.Main;
import rush.enums.Version;
import rush.utils.ReflectionUtils;

public class OfflinePlayerAPI {

	private static Boolean oldProfile;
	private static Boolean oldUUID;
	private static Object worldType;
	private static Method getServer;
	private static Method getWorldServer;
	private static Method getBukkitEntity;
	private static Constructor<?> gameProfileConstructor;
	private static Constructor<?> entityPlayerConstructor;
	private static Constructor<?> playerInteractManagerConstructor;
	
	@SuppressWarnings("deprecation")
	public static Player getPlayer(String playerName) {
        Player testPlayer = Bukkit.getPlayerExact(playerName);
        if (testPlayer != null) return testPlayer; // Caso ele já estiver online...
        
        if (Main.isVeryFuckingNewVersion()) return null; // Caso a versão do minecraft seja 1.14 ou acima...
        
        try {
	        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(playerName);
	        if (!offlinePlayer.hasPlayedBefore()) return null; // Caso o player não existir...
	        
	        Object uuid = oldUUID ? null : offlinePlayer.getUniqueId();
	        Object gameProfile = oldProfile ? playerName : gameProfileConstructor.newInstance(uuid, playerName);
	        Object minecraftServer = getServer.invoke(null);
	        Object worldServer = getWorldServer.invoke(minecraftServer, worldType);
	        Object playerInteractManager = playerInteractManagerConstructor.newInstance(worldServer);
	        Object entityPlayer = entityPlayerConstructor.newInstance(minecraftServer, worldServer, gameProfile, playerInteractManager);
	 
	        Player player = (Player) getBukkitEntity.invoke(entityPlayer);
	        player.loadData();
	 
	        return player;
        } catch (Throwable e) {
        	e.printStackTrace();
        	return null;
        }
	}
	
	public static Collection<? extends Player> getPlayers() {
		Collection<Player> players = new ArrayList<>();
		
		for (Player p : OnlinePlayersAPI.getOnlinePlayers()) 
			players.add(p);
		
        if (Main.isVeryFuckingNewVersion()) return players; // Caso a versão do minecraft seja 1.14 ou acima...
		
		try {
	        Object minecraftServer = getServer.invoke(null);
	        Object worldServer = getWorldServer.invoke(minecraftServer, worldType);
	        Object playerInteractManager = playerInteractManagerConstructor.newInstance(worldServer);
	        
			for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
				// Caso ele já estiver online ou for nulo...
		        if (offlinePlayer == null || offlinePlayer.isOnline()) continue;
		        
		        Object name = offlinePlayer.getName();
		        Object uuid = oldUUID ? null : offlinePlayer.getUniqueId();
		        Object gameProfile = oldProfile ? name : gameProfileConstructor.newInstance(uuid, offlinePlayer.getName());
		        Object entityPlayer = entityPlayerConstructor.newInstance(minecraftServer, worldServer, gameProfile, playerInteractManager);
		        
		        Player player = (Player) getBukkitEntity.invoke(entityPlayer);
		        player.loadData();
		        
		        players.add(player);
			}
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		return players;
	}

	static void load() {
		try 
		{
			Class<?> PlayerInteractManagerClass = ReflectionUtils.getNMSClass("PlayerInteractManager");
			Class<?> MinecraftServerClass = ReflectionUtils.getNMSClass("MinecraftServer");
			Class<?> EntityPlayerClass = ReflectionUtils.getNMSClass("EntityPlayer");
			Class<?> WorldServerClass = ReflectionUtils.getNMSClass("WorldServer");
			Class<?> WorldClass = ReflectionUtils.getNMSClass("World");
			Class<?> WorldTypeClass;
			Class<?> gameProfileClass;

			if (Main.isVeryNewVersion()) {
				WorldTypeClass = ReflectionUtils.getNMSClass("DimensionManager");
				worldType = WorldTypeClass.getField("OVERWORLD").get(null);
			} else {
				WorldTypeClass = int.class;
				worldType = 0;
			}
			
			if (Main.isVeryOldVersion()) {
				gameProfileClass = Class.forName("java.lang.String");
				gameProfileConstructor = gameProfileClass.getConstructor(String.class);
			} else if (Main.getVersion() == Version.v1_7) {
				gameProfileClass = Class.forName("net.minecraft.util.com.mojang.authlib.GameProfile");
				gameProfileConstructor = gameProfileClass.getConstructor(String.class, String.class);
			} else {
				gameProfileClass = Class.forName("com.mojang.authlib.GameProfile");
				gameProfileConstructor = gameProfileClass.getConstructor(UUID.class, String.class);
			}	
			
			if (Main.isVeryOldVersion()) {
				WorldServerClass = WorldClass;
			}
			
			oldUUID = Main.isOldVersion();
			oldProfile = Main.isVeryOldVersion();
			getServer = MinecraftServerClass.getMethod("getServer");
			getWorldServer = MinecraftServerClass.getMethod("getWorldServer", WorldTypeClass);
			getBukkitEntity = EntityPlayerClass.getMethod("getBukkitEntity");
			entityPlayerConstructor = EntityPlayerClass.getConstructor(MinecraftServerClass, WorldServerClass, gameProfileClass, PlayerInteractManagerClass);
			playerInteractManagerConstructor = PlayerInteractManagerClass.getConstructor(WorldClass);
		}
		catch (Throwable e) {}
	}
	
}
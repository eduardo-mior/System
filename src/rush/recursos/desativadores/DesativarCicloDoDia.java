package rush.recursos.desativadores;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

import rush.Main;

public class DesativarCicloDoDia {

	public static void stopDaylightCycle() {
		new BukkitRunnable() {
			@Override
			public void run() {
				for (World w : Bukkit.getWorlds()) {
					w.setGameRuleValue("doDaylightCycle", "false");
					w.setTime(6000);
				}
			}
		}.runTaskLater(Main.get(), 600L);
	}
	

	public static void stopDaylightCycleOLD() {
		new BukkitRunnable() {
			@Override
			public void run() {
				for (World w : Bukkit.getWorlds()) {
					w.setTime(6000);
				}
			}
		}.runTaskTimer(Main.get(), 600L, 600L);
	}
	
}
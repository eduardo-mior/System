package rush.addons;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.RegisteredServiceProvider;

import net.milkbowl.vault.economy.Economy;

public class Vault {

	private static Economy economy;

	public static boolean setupEconomy() {
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        economy = rsp.getProvider();
        return economy != null;
    }
	
	@SuppressWarnings("deprecation")
	public static double getBalance(OfflinePlayer off) {
		try {
			return economy.getBalance(off);
		} catch (Throwable e) {
			return economy.getBalance(off.getName());
		}
	}

}
package rush.sistemas.gerais;

import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import rush.apis.SkullAPI;
import rush.configuracoes.Settings;

public class DroparCabecaAoMorrer implements Listener {

	private static final Random rnd = new Random();
	
	@EventHandler
	public void aoMorrerDroparCabeca(PlayerDeathEvent e) {
		int aleatorio = rnd.nextInt(100);
		int chance = Settings.Chance_De_Dropar_Cabeca_Ao_Morrer;
		if (aleatorio < chance) {
			Player p = e.getEntity();
			ItemStack skull = SkullAPI.getByName(p.getName());
			p.getWorld().dropItem(p.getLocation(), skull);
		}
	}
	
}
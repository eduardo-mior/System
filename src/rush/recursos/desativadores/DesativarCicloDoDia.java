package rush.recursos.desativadores;

import org.bukkit.Bukkit;
import org.bukkit.World;

public class DesativarCicloDoDia {

    public static void stopDaylightCycle() {
        for (World worlds : Bukkit.getWorlds()) {
            worlds.setGameRuleValue("doDaylightCycle", "false");
            worlds.setTime(6000);
        }
    }
}

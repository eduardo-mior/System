package rush.recursos.antilag;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class DesativarChuva implements Listener {

	   @EventHandler
	   public void aoComecarChuva(WeatherChangeEvent e)
	   {
	     if (e.toWeatherState()) {
	       e.setCancelled(true);
	     }
	   }
	
}

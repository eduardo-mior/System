package rush.comandos;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import rush.Main;
import rush.utils.Locations;

public class ComandoSetmundovip implements Listener, CommandExecutor {

	@Override
	 public boolean onCommand(final CommandSender s, Command cmd, String lbl, String[] args) {
		 if (cmd.getName().equalsIgnoreCase("setmundovip")) {
			 if (s.hasPermission("system.setmundovip")) {
				 if (s instanceof Player) {
					 
				     if (args.length == 0) {
				          s.sendMessage(Main.aqui.getMensagens().getString("Setmundovip-Comando-Incoreto").replaceAll("&", "§"));
				          return false;
				        }
				     
				     if (args.length > 1) {
				          s.sendMessage(Main.aqui.getMensagens().getString("Setmundovip-Comando-Incoreto").replaceAll("&", "§"));
				          return false;
				        }
				     
			         if (args[0].equalsIgnoreCase("areavip")) {
			        	 Player p = (Player)s;
			             Locations.areaVip = p.getLocation();
			             saveAreaVip(Locations.areaVip);
			             s.sendMessage(Main.aqui.getMensagens().getString("Area-Vip-Definida").replaceAll("&", "§"));
			             return false;
			         }
			         
			         if (args[0].equalsIgnoreCase("areanaovip")) {
			        	 Player p = (Player)s;
			             Locations.areaNaoVip = p.getLocation();
			             saveAreaNaoVip(Locations.areaNaoVip);
			             s.sendMessage(Main.aqui.getMensagens().getString("Area-Nao-Vip-Definida").replaceAll("&", "§"));
			             return false;
			         }
				 }
				 s.sendMessage(Main.aqui.getMensagens().getString("Console-Nao-Pode").replace("&", "§"));
				 return false;
			 }
			 s.sendMessage(Main.aqui.getMensagens().getString("Sem-Permissao").replace("&", "§"));
			 return false;
		 }
		return false;
	}

    public static void saveAreaVip(Location areavip) {
    Main.aqui.getConfig().set("AreaVip", areavip);
    Main.aqui.getConfig().set("AreaVip.world", areavip.getWorld().getName());
    Main.aqui.getConfig().set("AreaVip.x", Double.valueOf(areavip.getX()));
    Main.aqui.getConfig().set("AreaVip.y", Double.valueOf(areavip.getY()));
    Main.aqui.getConfig().set("AreaVip.z", Double.valueOf(areavip.getZ()));
    Main.aqui.getConfig().set("AreaVip.yaw", Float.valueOf(areavip.getYaw()));
    Main.aqui.getConfig().set("AreaVip.pitch", Float.valueOf(areavip.getPitch()));
    Main.aqui.saveConfig(); }
    
    public static void saveAreaNaoVip(Location areanaovip) {
    Main.aqui.getConfig().set("AreaNaoVip", areanaovip);
    Main.aqui.getConfig().set("AreaNaoVip.world", areanaovip.getWorld().getName());
    Main.aqui.getConfig().set("AreaNaoVip.x", Double.valueOf(areanaovip.getX()));
    Main.aqui.getConfig().set("AreaNaoVip.y", Double.valueOf(areanaovip.getY()));
    Main.aqui.getConfig().set("AreaNaoVip.z", Double.valueOf(areanaovip.getZ()));
    Main.aqui.getConfig().set("AreaNaoVip.yaw", Float.valueOf(areanaovip.getYaw()));
    Main.aqui.getConfig().set("AreaNaoVip.pitch", Float.valueOf(areanaovip.getPitch()));
    Main.aqui.saveConfig(); }
	
}

package rush.comandos;

import java.util.Collections;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import net.minecraft.server.v1_8_R3.PacketPlayOutExplosion;
import net.minecraft.server.v1_8_R3.Vec3D;
import rush.Main;

public class ComandoCrashar implements Listener, CommandExecutor {

	@SuppressWarnings({ "deprecation", "unchecked" })
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
			if (cmd.getName().equalsIgnoreCase("crashar")) {
		         if (sender.hasPermission("system.crashar")) {
		             if (args.length == 0) {
		                 sender.sendMessage(Main.aqui.getMensagens().getString("Crashar-Comando-Incorreto").replace("&", "§"));
		                 return true;
		             }
		             
			         if (args.length >= 2) {
			             sender.sendMessage(Main.aqui.getMensagens().getString("Crashar-Comando-Incorreto").replace("&", "§"));
			             return true;
			         }
			         
			         if (args.length == 1) {
			             Player user = sender.getServer().getPlayer(args[0]);
			        	 if (user == null) {
			                 sender.sendMessage(Main.aqui.getMensagens().getString("Player-Offline").replace("&", "§"));
			        	 } else { 
			                  ((CraftPlayer)user).getHandle().playerConnection.sendPacket(new PacketPlayOutExplosion(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Float.MAX_VALUE, Collections.EMPTY_LIST, new Vec3D(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE)));
			                  sender.sendMessage(Main.aqui.getMensagens().getString("Player-Crashado").replace("%player%", args[0]).replace("&", "§"));
			                  return true;
			              }
			         }
			    }
		  sender.sendMessage(Main.aqui.getMensagens().getString("Sem-Permissao").replaceAll("&", "§")); 
		  }
		  return false;
	 }
}
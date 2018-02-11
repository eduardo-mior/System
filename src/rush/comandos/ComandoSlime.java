package rush.comandos;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import rush.Main;

public class ComandoSlime implements Listener, CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
	      if (cmd.getName().equalsIgnoreCase("slime")) {
		       if (!(sender instanceof Player)) {
		          sender.sendMessage(Main.aqui.getMensagens().getString("Console-Nao-Pode").replaceAll("&", "§"));
		          return true;}
	         }
	      Player p = (Player)sender;
	      long worldSeed = p.getPlayer().getWorld().getSeed();
	      Chunk playerChunk;
	      int xChunk;
	      int zChunk;
	      Random random = new Random(worldSeed + (xChunk = (playerChunk = p.getPlayer().getWorld().getChunkAt(p.getLocation())).getX()) * xChunk * 4987142 + xChunk * 5947611 + (zChunk = playerChunk.getZ()) * zChunk * 4392871L + zChunk * 389711 ^ 0x3AD8025F);
	      if (random.nextInt(10) == 0) {
	        p.sendMessage(Main.aqui.getMensagens().getString("SlimeChunk.Esta").replaceAll("&", "§"));
	        p.playSound(p.getLocation(), Sound.SLIME_WALK2, 1, 1);
	      } else {
	        p.sendMessage(Main.aqui.getMensagens().getString("SlimeChunk.NaoEsta").replaceAll("&", "§"));
	        p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1, 1);
	      }
		return false;
	}
	
	

}

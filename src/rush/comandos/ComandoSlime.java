package rush.comandos;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import rush.utils.ConfigManager;

public class ComandoSlime implements Listener, CommandExecutor {

	public boolean onCommand(CommandSender s, Command cmd, String lbl, String[] args) {
		if (cmd.getName().equalsIgnoreCase("slime")) {
			
			if (!(s instanceof Player)) {
				s.sendMessage(ConfigManager.getConfig("mensagens").getString("Console-Nao-Pode").replace("&", "§"));
				return false;
			}
		       
			Player p = (Player)s;
			long worldSeed = p.getPlayer().getWorld().getSeed();
			Chunk playerChunk;
			int xChunk;
			int zChunk;
			Random random = new Random(worldSeed + (xChunk = (playerChunk = p.getPlayer().getWorld().getChunkAt(p.getLocation())).getX()) * xChunk * 4987142 + xChunk * 5947611 + (zChunk = playerChunk.getZ()) * zChunk * 4392871L + zChunk * 389711 ^ 0x3AD8025F);
			if (random.nextInt(10) == 0) {
				p.sendMessage(ConfigManager.getConfig("mensagens").getString("SlimeChunk.Esta").replace("&", "§"));
				p.playSound(p.getLocation(), Sound.SLIME_WALK2, 1, 1);
			} else {
				p.sendMessage(ConfigManager.getConfig("mensagens").getString("SlimeChunk.NaoEsta").replace("&", "§"));
				p.playSound(p.getLocation(), Sound.VILLAGER_NO, 1, 1);
			}
		}
		return false;
	}
}
